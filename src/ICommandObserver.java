
public interface ICommandObserver 
{
	public void AddObserver(CommandHelper obs);
	public void RemoveObserver(CommandHelper obs);
	public boolean NotifyObserver(String value);
}
