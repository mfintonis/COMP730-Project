
public class QuitCommand extends CommandHelper
{
	public QuitCommand(CommandObserver commandObserver)
	{
		commandObserver.AddObserver(this);
	}
	
	public boolean matchCommand(String input)
	{
		if (!input.toLowerCase().equals("quit"))
		{
			return false;
		}
		
		doCommand();
		
		return true;
	}
	
	private void doCommand()
	{
		System.exit(0);
	}
	
	public String GetDescription()
	{
		
		return "Quits the program";
	}
}
