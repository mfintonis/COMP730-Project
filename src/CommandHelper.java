public abstract class CommandHelper implements Commandable
{
	public boolean Update(String input)
	{
		return matchCommand(input);
	}
}
