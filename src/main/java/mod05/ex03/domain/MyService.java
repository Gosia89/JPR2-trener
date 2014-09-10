package mod05.ex03.domain;

import mod05.ex03.annotation.Repeat;

public interface MyService {

	@Repeat(2)
	public void businessMethodA();

	@Repeat(0)
	public void businessMethodB();

	public void businessMethodC();
}