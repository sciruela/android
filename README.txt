This example use ASM and BCEL to generete two classes on-devide.
The classes are created into SD Card memory and then they are loaded into Android operating system dynamically.

public class HelloWorld {
	public static void hello(){
		int a=0xabcd;
		int b=0xaaaa;
		int c=a-b;
		String s=Integer.toHexString(c);
		System.out.println(s);
	}

}