This example use ASM and BCEL to generete two classes on-device.
The classes are created into SD Card memory and then they are loaded into Android operating system dynamically.

The following class is the template of the example:

public class HelloWorld {
	public static void hello(){
		int a=0xabcd;
		int b=0xaaaa;
		int c=a-b;
		String s=Integer.toHexString(c);
		System.out.println(s);
	}

}


Firstly I have used BCEL or ASM to create a new ad-hoc class in SD Card.
Secondly I have converted the Java Class to a Dex Class with the Dxclient utiliy in SD Card.
Finally I have created a jar file and then I have loaded this package into the device from SD Card

DXClient reference

https://github.com/headius/dexclient/blob/master/src/DexClient.java