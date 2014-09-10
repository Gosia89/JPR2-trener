package mod05.ex02;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class Kalkulator implements InvocationHandler {

	private Object target;
	private static Map<String, String> translator = new HashMap<String, String>();
	static {
		translator.put("dodaj", "add");
		translator.put("pomnoz", "multiply");
	}

	private Kalkulator() {
	}

	public static Operacje utworzKalkulator() {
		Kalkulator proxy = new Kalkulator();
		proxy.target = new Calculator();
		ClassLoader loader = proxy.target.getClass().getClassLoader();
		return (Operacje) Proxy.newProxyInstance(loader,
				new Class[] { Operacje.class }, proxy);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) {
		try {
			String sourceMethodName = method.getName();
			Class<?>[] types = method.getParameterTypes();
			String targetMethodName = translator.get(sourceMethodName);
			Method targetMethod = target.getClass().getMethod(targetMethodName,
					types);
			return targetMethod.invoke(target, args);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
