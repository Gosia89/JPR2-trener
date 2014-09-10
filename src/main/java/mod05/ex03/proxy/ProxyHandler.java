package mod05.ex03.proxy;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import mod05.ex03.action.InvokeInterface;
import mod05.ex03.annotation.Repeat;


public class ProxyHandler implements InvocationHandler {
	private InvokeInterface proxyAction;
	private Object proxiedInstance;

	protected ProxyHandler(Object proxiedInstance, InvokeInterface proxyAction) {
		this.proxiedInstance = proxiedInstance;
		this.proxyAction = proxyAction;
	}

	@Override
	public Object invoke(Object proxy, Method proxiedMethod, Object[] args) throws Throwable {
		int repeats = getRepeats(proxiedMethod);
		String methodName = proxiedMethod.getName();
		for (int i = 0; i < repeats; i++) {
			proxyAction.beforeInvoke(methodName);
			proxiedMethod.invoke(proxiedInstance, args);
			proxyAction.afterInvoke(methodName);
		}
		return null;
	}

	private int getRepeats(Method proxiedMethod) {
		for (Annotation annotation : proxiedMethod.getAnnotations()) {
			if (annotation instanceof Repeat) {
				return ((Repeat) annotation).value();
			}
		}
		return 1;
	}

}
