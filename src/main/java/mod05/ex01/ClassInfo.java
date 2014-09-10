package mod05.ex01;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ClassInfo {
	private Class<?> c;

	public ClassInfo(Object obj) {
		c = obj.getClass();
	}

	public ClassInfo(String className) {
		try {
			c = Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public ClassInfo(Class<?> c) {
		this.c = c;
	}

	private void printModifiers(int modifiers) {
		String[] names = { "public", "private", "protected", "static", "final",
				"synchronized", "volatile", "transient", "native", "interface",
				"abstract", "strict" };
		for (int i = 0; i < names.length; i++) {
			if ((modifiers & 1) == 1) {
				System.out.print(names[i] + " ");
			}
			modifiers = modifiers >> 1;
		}
	}

	private void printClassNames(Class<?>[] c) {
		for (int i = 0; i < c.length; i++) {
			System.out.print((i == 0) ? "" : ", ");
			System.out.print(c[i].getCanonicalName());
		}
	}

	private void printParameters(Class<?>[] params) {
		System.out.print("(");
		printClassNames(params);
		System.out.print(")");
	}

	private void printInterfaces(Class<?>[] interfaces) {
		if (interfaces.length > 0) {
			System.out.print("\n\timplements ");
			printClassNames(interfaces);
		}
	}

	private void printSuperClass(Class<?>[] superClasses) {
		if (superClasses.length > 0) {
			System.out.print("\n\textends ");
			printClassNames(superClasses);
		}
	}

	private void printExceptions(Class<?>[] exceptions) {
		if (exceptions.length > 0) {
			System.out.print(" throws ");
			printClassNames(exceptions);
		}
	}

	private void getClassInfo() {
		System.out.println("\nCLASS:");
		printModifiers(c.getModifiers());
		System.out.print("class " + c.getName());
		Class<?> superType = c.getSuperclass();
		if (superType != null) {
			printSuperClass(new Class<?>[] { c.getSuperclass() });
		}
		printInterfaces(c.getInterfaces());
		System.out.println();
	}

	private void getConstructorsInfo() {
		System.out.println("\nCONSTRUCTORS:");
		for (Constructor<?> constructor : c.getDeclaredConstructors()) {
			constructor.setAccessible(true);
			printModifiers(constructor.getModifiers());
			System.out.print(constructor.getName() + "(");
			printParameters(constructor.getParameterTypes());
			printExceptions(constructor.getExceptionTypes());
			System.out.println();
		}
	}

	private void getMethodsInfo() {
		System.out.println("\nMETHODS:");
		for (Method method : c.getDeclaredMethods()) {
			method.setAccessible(true);
			printModifiers(method.getModifiers());
			System.out.print(method.getReturnType().getSimpleName() + " ");
			System.out.print(method.getName());
			printParameters(method.getParameterTypes());
			printExceptions(method.getExceptionTypes());
			System.out.println();
		}
	}

	private void getFieldsInfo() {
		System.out.println("\nFIELDS:");
		for (Field field : c.getDeclaredFields()) {
			field.setAccessible(true);
			printModifiers(field.getModifiers());
			System.out.print(field.getType().getCanonicalName() + " ");
			System.out.println(field.getName());
		}
	}

	public static void main(String[] args) {
//		ClassInfo info = new ClassInfo(Planet.Earth);
		ClassInfo info = new ClassInfo(String.class);
		info.getClassInfo();
		info.getConstructorsInfo();
		info.getMethodsInfo();
		info.getFieldsInfo();
	}

}
