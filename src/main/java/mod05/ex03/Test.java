package mod05.ex03;

import mod05.ex03.action.InvokeAction;
import mod05.ex03.domain.MyService;
import mod05.ex03.proxy.ProxiedService;

public class Test {

	public static void main(String[] args) {
		MyService service = ProxiedService.newInstance(new InvokeAction());

		service.businessMethodA();
		service.businessMethodB();
		service.businessMethodC();
	}

}
