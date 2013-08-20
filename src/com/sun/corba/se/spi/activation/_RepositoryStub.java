package com.sun.corba.se.spi.activation;


/**
* com/sun/corba/se/spi/activation/_RepositoryStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ../../../../src/share/classes/com/sun/corba/se/spi/activation/activation.idl
* Wednesday, September 15, 2004 5:08:59 AM PDT
*/

public class _RepositoryStub extends org.omg.CORBA.portable.ObjectImpl implements com.sun.corba.se.spi.activation.Repository
{


  // always uninstalled.
  public int registerServer (com.sun.corba.se.spi.activation.RepositoryPackage.ServerDef serverDef) throws com.sun.corba.se.spi.activation.ServerAlreadyRegistered, com.sun.corba.se.spi.activation.BadServerDefinition
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("registerServer", true);
                com.sun.corba.se.spi.activation.RepositoryPackage.ServerDefHelper.write ($out, serverDef);
                $in = _invoke ($out);
                int $result = com.sun.corba.se.spi.activation.ServerIdHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:activation/ServerAlreadyRegistered:1.0"))
                    throw com.sun.corba.se.spi.activation.ServerAlreadyRegisteredHelper.read ($in);
                else if (_id.equals ("IDL:activation/BadServerDefinition:1.0"))
                    throw com.sun.corba.se.spi.activation.BadServerDefinitionHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return registerServer (serverDef        );
            } finally {
                _releaseReply ($in);
            }
  } // registerServer


  // unregister server definition
  public void unregisterServer (int serverId) throws com.sun.corba.se.spi.activation.ServerNotRegistered
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("unregisterServer", true);
                com.sun.corba.se.spi.activation.ServerIdHelper.write ($out, serverId);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:activation/ServerNotRegistered:1.0"))
                    throw com.sun.corba.se.spi.activation.ServerNotRegisteredHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                unregisterServer (serverId        );
            } finally {
                _releaseReply ($in);
            }
  } // unregisterServer


  // get server definition
  public com.sun.corba.se.spi.activation.RepositoryPackage.ServerDef getServer (int serverId) throws com.sun.corba.se.spi.activation.ServerNotRegistered
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getServer", true);
                com.sun.corba.se.spi.activation.ServerIdHelper.write ($out, serverId);
                $in = _invoke ($out);
                com.sun.corba.se.spi.activation.RepositoryPackage.ServerDef $result = com.sun.corba.se.spi.activation.RepositoryPackage.ServerDefHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:activation/ServerNotRegistered:1.0"))
                    throw com.sun.corba.se.spi.activation.ServerNotRegisteredHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getServer (serverId        );
            } finally {
                _releaseReply ($in);
            }
  } // getServer


  // Return whether the server has been installed
  public boolean isInstalled (int serverId) throws com.sun.corba.se.spi.activation.ServerNotRegistered
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("isInstalled", true);
                com.sun.corba.se.spi.activation.ServerIdHelper.write ($out, serverId);
                $in = _invoke ($out);
                boolean $result = $in.read_boolean ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:activation/ServerNotRegistered:1.0"))
                    throw com.sun.corba.se.spi.activation.ServerNotRegisteredHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return isInstalled (serverId        );
            } finally {
                _releaseReply ($in);
            }
  } // isInstalled


  // if the server is currently marked as installed.
  public void install (int serverId) throws com.sun.corba.se.spi.activation.ServerNotRegistered, com.sun.corba.se.spi.activation.ServerAlreadyInstalled
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("install", true);
                com.sun.corba.se.spi.activation.ServerIdHelper.write ($out, serverId);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:activation/ServerNotRegistered:1.0"))
                    throw com.sun.corba.se.spi.activation.ServerNotRegisteredHelper.read ($in);
                else if (_id.equals ("IDL:activation/ServerAlreadyInstalled:1.0"))
                    throw com.sun.corba.se.spi.activation.ServerAlreadyInstalledHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                install (serverId        );
            } finally {
                _releaseReply ($in);
            }
  } // install


  // if the server is currently marked as uninstalled.
  public void uninstall (int serverId) throws com.sun.corba.se.spi.activation.ServerNotRegistered, com.sun.corba.se.spi.activation.ServerAlreadyUninstalled
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("uninstall", true);
                com.sun.corba.se.spi.activation.ServerIdHelper.write ($out, serverId);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:activation/ServerNotRegistered:1.0"))
                    throw com.sun.corba.se.spi.activation.ServerNotRegisteredHelper.read ($in);
                else if (_id.equals ("IDL:activation/ServerAlreadyUninstalled:1.0"))
                    throw com.sun.corba.se.spi.activation.ServerAlreadyUninstalledHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                uninstall (serverId        );
            } finally {
                _releaseReply ($in);
            }
  } // uninstall


  // list registered servers
  public int[] listRegisteredServers ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("listRegisteredServers", true);
                $in = _invoke ($out);
                int $result[] = com.sun.corba.se.spi.activation.ServerIdsHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return listRegisteredServers (        );
            } finally {
                _releaseReply ($in);
            }
  } // listRegisteredServers


  // servers.
  public String[] getApplicationNames ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getApplicationNames", true);
                $in = _invoke ($out);
                String $result[] = com.sun.corba.se.spi.activation.RepositoryPackage.StringSeqHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getApplicationNames (        );
            } finally {
                _releaseReply ($in);
            }
  } // getApplicationNames


  // Find the ServerID associated with the given application name.
  public int getServerID (String applicationName) throws com.sun.corba.se.spi.activation.ServerNotRegistered
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getServerID", true);
                $out.write_string (applicationName);
                $in = _invoke ($out);
                int $result = com.sun.corba.se.spi.activation.ServerIdHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:activation/ServerNotRegistered:1.0"))
                    throw com.sun.corba.se.spi.activation.ServerNotRegisteredHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getServerID (applicationName        );
            } finally {
                _releaseReply ($in);
            }
  } // getServerID

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:activation/Repository:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }

  private void readObject (java.io.ObjectInputStream s) throws java.io.IOException
  {
     String str = s.readUTF ();
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.Object obj = org.omg.CORBA.ORB.init (args, props).string_to_object (str);
     org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate ();
     _set_delegate (delegate);
  }

  private void writeObject (java.io.ObjectOutputStream s) throws java.io.IOException
  {
     String[] args = null;
     java.util.Properties props = null;
     String str = org.omg.CORBA.ORB.init (args, props).object_to_string (this);
     s.writeUTF (str);
  }
} // class _RepositoryStub
