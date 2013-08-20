/*
 * Copyright 2001-2004 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/*
 * $Id: Variable.java,v 1.27 2004/02/24 02:58:42 zongaro Exp $
 */

package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.bcel.internal.classfile.Field;
import com.sun.org.apache.bcel.internal.generic.ACONST_NULL;
import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.DCONST;
import com.sun.org.apache.bcel.internal.generic.ICONST;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.bcel.internal.generic.PUTFIELD;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.BooleanType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.IntType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.NodeType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.RealType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;

/**
 * @author Jacek Ambroziak
 * @author Santiago Pericas-Geertsen
 * @author Morten Jorgensen
 * @author Erwin Bolwidt <ejb@klomp.org>
 * @author John Howard <JohnH@schemasoft.com>
 */
final class Variable extends VariableBase {

    public int getIndex() {
	return (_local != null) ? _local.getIndex() : -1;
    }

    /**
     * Parse the contents of the variable
     */
    public void parseContents(Parser parser) {
	// Parse 'name' and 'select' attributes plus parameter contents
	super.parseContents(parser);

	// Add a ref to this var to its enclosing construct
	SyntaxTreeNode parent = getParent();
	if (parent instanceof Stylesheet) {
	    // Mark this as a global variable
	    _isLocal = false;
	    // Check if a global variable with this name already exists...
	    Variable var = parser.getSymbolTable().lookupVariable(_name);
	    // ...and if it does we need to check import precedence
	    if (var != null) {
		final int us = this.getImportPrecedence();
		final int them = var.getImportPrecedence();
		// It is an error if the two have the same import precedence
		if (us == them) {
		    final String name = _name.toString();
		    reportError(this, parser, ErrorMsg.VARIABLE_REDEF_ERR,name);
		}
		// Ignore this if previous definition has higher precedence
		else if (them > us) {
		    _ignore = true;
		    return;
		}
		else {
		    var.disable();
		}
		// Add this variable if we have higher precedence
	    }
	    ((Stylesheet)parent).addVariable(this);
	    parser.getSymbolTable().addVariable(this);
	}
	else {
	    _isLocal = true;
	}
    }

    /**
     * Runs a type check on either the variable element body or the
     * expression in the 'select' attribute
     */
    public Type typeCheck(SymbolTable stable) throws TypeCheckError {

	// Type check the 'select' expression if present
	if (_select != null) {
	    _type = _select.typeCheck(stable);
	}
	// Type check the element contents otherwise
	else if (hasContents()) {
	    typeCheckContents(stable);
	    _type = Type.ResultTree;
	}
	else {
	    _type = Type.Reference;
	}
	// The return type is void as the variable element does not leave
	// anything on the JVM's stack. The '_type' global will be returned
	// by the references to this variable, and not by the variable itself.
	return Type.Void;
    }

    /**
     * This method is part of a little trick that is needed to use local
     * variables inside nested for-each loops. See the initializeVariables()
     * method in the ForEach class for an explanation
     */
    public void initialize(ClassGenerator classGen, MethodGenerator methodGen) {
	final ConstantPoolGen cpg = classGen.getConstantPool();
	final InstructionList il = methodGen.getInstructionList();

	// This is only done for local variables that are actually used
	if (isLocal() && !_refs.isEmpty()) {
	    // Create a variable slot if none is allocated
	    if (_local == null) {
		_local = methodGen.addLocalVariable2(getEscapedName(),
						     _type.toJCType(),
						     il.getEnd());
	    }
	    // Push the default value on the JVM's stack
	    if ((_type instanceof IntType) ||
		(_type instanceof NodeType) ||
		(_type instanceof BooleanType))
		il.append(new ICONST(0)); // 0 for node-id, integer and boolean
	    else if (_type instanceof RealType)
		il.append(new DCONST(0)); // 0.0 for floating point numbers
	    else
		il.append(new ACONST_NULL()); // and 'null' for anything else
	    il.append(_type.STORE(_local.getIndex()));
	}
    }

    public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
	final ConstantPoolGen cpg = classGen.getConstantPool();
	final InstructionList il = methodGen.getInstructionList();

	final String name = getEscapedName();

	// Make sure that a variable instance is only compiled once
	if (_ignore) return;
	_ignore = true;

	if (isLocal()) {
	    // Compile variable value computation
	    translateValue(classGen, methodGen);

	    // Add a new local variable and store value
	    if (_refs.isEmpty()) { // Remove it if nobody uses the value
		il.append(_type.POP());
		_local = null;
	    }
	    else {		   // Store in local var slot if referenced
		if (_local == null) mapRegister(methodGen);
		il.append(_type.STORE(_local.getIndex()));
	    }
	}
	else {
	    String signature = _type.toSignature();

	    // Global variables are store in class fields
	    if (classGen.containsField(name) == null) {
		classGen.addField(new Field(ACC_PUBLIC, 
					    cpg.addUtf8(name),
					    cpg.addUtf8(signature),
					    null, cpg.getConstantPool()));

		// Push a reference to "this" for putfield
		il.append(classGen.loadTranslet());
		// Compile variable value computation
		translateValue(classGen, methodGen);
		// Store the variable in the allocated field
		il.append(new PUTFIELD(cpg.addFieldref(classGen.getClassName(),
						       name, signature)));
	    }
	}
    }
}
