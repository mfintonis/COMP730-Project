
import java.util.Scanner;

public class GameController
{
	public void Run()
	{
		System.out.println("Welcome to the beginnings of a Java Dungeon Crawler!");
		
		CommandObserver observer = new CommandObserver();
		
		//Will need to close this on quit
		Scanner reader = new Scanner(System.in);		
		
		while (true)
		{
			System.out.print("Input: ");			
						
			String input = reader.next();
			
			if (!observer.NotifyObserver(input))
			{
				System.out.println("Invalid input! Use the \"help\" command for list of commands");
			}
		}
	}
}