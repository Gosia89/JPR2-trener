package mod01.ex04.utils;

public interface Command {
	public void execute() throws Exception;
	public void revert() throws Exception;
	public boolean isRevertable();
}
