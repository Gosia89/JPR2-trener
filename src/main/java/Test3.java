import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class Test3 {

	public String a(String s1, String s2) {
		return "a";
	}

	public static void main(String[] args) {
		MethodHandles.Lookup lookup = MethodHandles.lookup();
		MethodType mt = MethodType.methodType(String.class, String.class, String.class);
		try {
			MethodHandle mh = lookup.findVirtual(Test3.class, "a", mt);
			String t = (String)mh.invoke(new Test3(),"1","2");
			System.out.println(t);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
