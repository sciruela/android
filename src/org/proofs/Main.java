package org.proofs;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.TreeMap;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;

import com.android.dx.Version;

import dalvik.system.DexClassLoader;

import android.app.Activity;
import android.os.Bundle;

public class Main extends Activity {
	
	private static final String DEX_IN_JAR_NAME = "classes.dex";
    private static final String MANIFEST_NAME = "META-INF/MANIFEST.MF";
    private static final Attributes.Name CREATED_BY = new Attributes.Name("Created-By");
    private static TreeMap<String, byte[]> outputResources;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
     
        String cwd = System.getProperty("user.dir");
          
       
        String dir = "/mnt/sdcard";
             
        System.setProperty("user.dir", dir);
             
        String Ncwd = System.getProperty("user.dir");
       
        CreateClassHelloWorldASM b=new CreateClassHelloWorldASM();        
        try{
        FileOutputStream myasm=new FileOutputStream("org/proofs/HelloWorldASM.class");
        myasm.write(b.dump());
        myasm.close();
        CreateClassHelloWorldBCEL a=new CreateClassHelloWorldBCEL();
        }catch(Exception e){e.printStackTrace();}
        
     	System.gc();
     	
        String manyDirectories="org/proofs/";
        boolean success=new File(manyDirectories).mkdirs();
        
        if(success){
        	System.out.println(System.getProperty("user.dir"));
        }
        
        DexClient dxclient=new DexClient();
	    String[] name={"org/proofs/HelloWorldBCEL.class"};	
        byte[] mybytes;
		try {
			mybytes = getBytesFromFile(new File(name[0]));
			byte[][] bytes={mybytes};
		
        
        
        
        System.setProperty("user.dir","/");
     	createJar("mnt/sdcard/HelloWorldBCEL.jar", dxclient.classesToDex(name, bytes));
     	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     	new File(name[0]).delete();
     	
        DexClassLoader classLoader=new DexClassLoader("mnt/sdcard/HelloWorldBCEL.jar","/mnt/sdcard",null,getClass().getClassLoader());
        
        
        Class<?> myClass;
		try {
			myClass = classLoader.loadClass("org.proofs.HelloWorldBCEL");
			Object myobject=myClass.newInstance();
			 for(Method mymethod:myobject.getClass().getMethods()){
		        	System.out.println(mymethod.getName());
		        }
			 for(Field myfield:myobject.getClass().getDeclaredFields()){
		        	System.out.println(myfield.getName());
		        }
		       
			 
			 try {
				Method myinvocation1=myobject.getClass().getMethod("hello", null);
				try {
					myinvocation1.invoke(myobject, null);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (SecurityException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (NoSuchMethodException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 
			 System.setProperty("user.dir", dir);	 
			 DexClient dxclient2=new DexClient();
			    String[] name2={"org/proofs/HelloWorldASM.class"};	
		        byte[] mybytes2;
				
					try {
						mybytes2= getBytesFromFile(new File(name2[0]));
						byte[][] bytes2={mybytes2};
					    System.setProperty("user.dir","/");
				     	createJar("mnt/sdcard/HelloWorldASM.jar", dxclient2.classesToDex(name2, bytes2));
				     	
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				
		        
		        
		        
		    
		     	new File(name2[0]).delete();
		     	
		        DexClassLoader classLoader2=new DexClassLoader("mnt/sdcard/HelloWorldASM.jar","/mnt/sdcard",null,getClass().getClassLoader());
		        
		        
		        Class<?> myClass2;
				
					myClass2 = classLoader2.loadClass("org.proofs.HelloWorldASM");
					Object myobject2=myClass.newInstance();
					 for(Method mymethod2:myobject2.getClass().getMethods()){
				        	System.out.println(mymethod2.getName());			       
					 }		
					 for(Field myfield2:myobject2.getClass().getDeclaredFields()){
						 System.out.println(myfield2.getName());
					 }
					 
					
					 try {
						Method myinvocation2=myobject2.getClass().getMethod("hello", null);
						try {
							myinvocation2.invoke(myobject2, null);
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} catch (SecurityException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					 
					 
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
       
        
        
        
    }
    
    
    public static byte[] getBytesFromFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);
    
        // Get the size of the file
        long length = file.length();
    
        if (length > Integer.MAX_VALUE) {
            // File is too large
        }
    
        // Create the byte array to hold the data
        byte[] bytes = new byte[(int)length];
    
        // Read in the bytes
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
               && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
            offset += numRead;
        }
    
        // Ensure all the bytes have been read in
        if (offset < bytes.length) {
            throw new IOException("Could not completely read file "+file.getName());
        }
    
        // Close the input stream and return bytes
        is.close();
        return bytes;
    }
    
    
    public static boolean createJar(String fileName, byte[] dexArray) {
        /*
         * Make or modify the manifest (as appropriate), put the dex
         * array into the resources map, and then process the entire
         * resources map in a uniform manner.
         */

        try {
        	outputResources = new TreeMap<String, byte[]>();
            Manifest manifest = makeManifest();
            OutputStream out = openOutput(fileName);
            JarOutputStream jarOut = new JarOutputStream(out, manifest);

            outputResources.put(DEX_IN_JAR_NAME, dexArray);

            try {
                for (Map.Entry<String, byte[]> e :
                         outputResources.entrySet()) {
                    String name = e.getKey();
                     byte[] contents = e.getValue();
                    JarEntry entry = new JarEntry(name);
                  
               

                    entry.setSize(contents.length);
                    jarOut.putNextEntry(entry);
                    jarOut.write(contents);
                    jarOut.closeEntry();
                }
            } finally {
                jarOut.finish();
                jarOut.flush();
                closeOutput(out);
        
            }
        } catch (Exception ex) {
  
           
            return false;
        }
     
        return true;
    }
    
    private static Manifest makeManifest() throws IOException {
        byte[] manifestBytes = outputResources.get(MANIFEST_NAME);
        Manifest manifest;
        Attributes attribs;

        if (manifestBytes == null) {
            // We need to construct an entirely new manifest.
            manifest = new Manifest();
            attribs = manifest.getMainAttributes();
            attribs.put(Attributes.Name.MANIFEST_VERSION, "1.0");
        } else {
            manifest = new Manifest(new ByteArrayInputStream(manifestBytes));
            attribs = manifest.getMainAttributes();
            outputResources.remove(MANIFEST_NAME);
        }

        String createdBy = attribs.getValue(CREATED_BY);
        if (createdBy == null) {
            createdBy = "";
        } else {
            createdBy += " + ";
        }
        createdBy += "dx " + Version.VERSION;

        attribs.put(CREATED_BY, createdBy);
        attribs.putValue("Dex-Location", DEX_IN_JAR_NAME);

        return manifest;
    }
    
    private static OutputStream openOutput(String name) throws IOException {
        if (name.equals("-") ||
                name.startsWith("-.")) {
            return System.out;
        }

        return new FileOutputStream(name);
    }
    
    private static void closeOutput(OutputStream stream) throws IOException {
        if (stream == null) {
            return;
        }

        stream.flush();

        if (stream != System.out) {
            stream.close();
        }
    }

    
    
}