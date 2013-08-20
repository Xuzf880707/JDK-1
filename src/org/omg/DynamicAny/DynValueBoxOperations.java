package org.omg.DynamicAny;


/**
* org/omg/DynamicAny/DynValueBoxOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ../../../../src/share/classes/org/omg/DynamicAny/DynamicAny.idl
* Wednesday, September 15, 2004 3:46:18 AM GMT-08:00
*/


/**
    * DynValueBox objects support the manipulation of IDL boxed value types.
    * The DynValueBox interface can represent both null and non-null value types.
    * For a DynValueBox representing a non-null value type, the DynValueBox has a single component
    * of the boxed type. A DynValueBox representing a null value type has no components
    * and a current position of -1.
    */
public interface DynValueBoxOperations  extends org.omg.DynamicAny.DynValueCommonOperations
{

  /**
          * Returns the boxed value as an Any.
          *
          * @exception InvalidValue if this object represents a null value box type
          */
  org.omg.CORBA.Any get_boxed_value () throws org.omg.DynamicAny.DynAnyPackage.InvalidValue;

  /**
          * Replaces the boxed value with the specified value.
          * If the DynBoxedValue represents a null valuetype, it is converted to a non-null value.
          *
          * @exception TypeMismatch if this object represents a non-null value box type and the type
          *            of the parameter is not matching the current boxed value type.
          */
  void set_boxed_value (org.omg.CORBA.Any boxed) throws org.omg.DynamicAny.DynAnyPackage.TypeMismatch;

  /**
          * Returns the boxed value as a DynAny.
          *
          * @exception InvalidValue if this object represents a null value box type
          */
  org.omg.DynamicAny.DynAny get_boxed_value_as_dyn_any () throws org.omg.DynamicAny.DynAnyPackage.InvalidValue;

  /**
          * Replaces the boxed value with the value contained in the parameter.
          * If the DynBoxedValue represents a null valuetype, it is converted to a non-null value.
          *
          * @exception TypeMismatch if this object represents a non-null value box type and the type
          *            of the parameter is not matching the current boxed value type.
          */
  void set_boxed_value_as_dyn_any (org.omg.DynamicAny.DynAny boxed) throws org.omg.DynamicAny.DynAnyPackage.TypeMismatch;
} // interface DynValueBoxOperations
