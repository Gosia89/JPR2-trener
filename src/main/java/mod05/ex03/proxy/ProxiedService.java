package mod05.ex03.proxy;

import java.lang.reflect.Proxy;

import mod05.ex03.action.InvokeInterface;
import mod05.ex03.domain.MyService;
import mod05.ex03.domain.MyServiceBean;

public class ProxiedService {
	private static MyService proxiedInstance = new MyServiceBean();

	private ProxiedService() {
	}

	public static MyService newInstance(InvokeInterface proxyAction) {

		Class<?> c = proxiedInstance.getClass();
		return (MyService) Proxy.newProxyInstance(c.getClassLoader(), c
				.getInterfaces(),
				new ProxyHandler(proxiedInstance, proxyAction));
	}

}
