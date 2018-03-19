import java.util.ArrayList;
import java.util.List;

public class CommandObserver implements ICommandObserver
{
	private List<CommandHelper> observerList;
	
	public CommandObserver()
	{
		observerList = new ArrayList<CommandHelper>();
		
		AddObserver(new HelpCommand(this));
		AddObserver(new QuitCommand(this));
	}
	
	public void AddObserver(CommandHelper obs)
	{
		observerList.add(obs);
	}
	
	public void RemoveObserver(CommandHelper obs)
	{
		observerList.remove(obs);
	}
	
	public boolean NotifyObserver(String value)
	{
		for (CommandHelper obs : observerList)
		{
			if (obs.Update(value))
			{
				return true;
			}
		}
		
		return false;
	}
}
