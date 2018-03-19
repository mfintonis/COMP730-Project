
public class HelpCommand extends CommandHelper
{
	public HelpCommand(CommandObserver commandObserver)
	{
		commandObserver.AddObserver(this);
	}
	
	public boolean matchCommand(String input)
	{
		if (!input.toLowerCase().equals("help"))
		{
			return false;
		}
		
		doCommand();
		
		return true;
	}
	
	private void doCommand()
	{
		System.out.println("Commands\n--------------\nQuit\nHelp");
	}
	
	public String GetDescription()
	{
		return "Shows the help menu";
	}
}
