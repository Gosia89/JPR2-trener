import java.lang.invoke.MethodHandles;


public class Test2 extends A {

	public static void main(String[] args) {
		System.out.println(new Test2().s);
		System.out.println(MethodHandles.lookup().lookupClass().getName());
	}

}
class A {
//public String s = Test2.class.getName();	
	public String s = MethodHandles.lookup().lookupClass().getName();
}
