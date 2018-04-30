import java.util.Scanner;

public class Main {

	private static BaseItem[][] myMap;
	private static Map myDungeon;
	private static Player player; 
	private static boolean run; 
	private static String myName;
	private static int classOrder = -1;

	public static void main(String args[])
	{
		// game start
		System.out.println("\t\t\t\t\t DUNGEON CRAWLER\n");
		System.out.println("\t\t\t\t---------------------------------\n");
		System.out.println("\t\t\t\t---------------------------------\n");	
		Scanner sc = new Scanner(System.in);
		System.out.print("\t \t \t \t   Press any key to continue");
		sc.nextLine();
		System.out.print("\n \t \t \t \t   What's your name? ");
		myName = sc.next();
		while(classOrder < 0) {
			System.out.print("\n \t \t \t \t   Please choose your class ");
			classOrder = showClass();
		}
						
		gameStart();
	}
	
	
	public static void gameStart() 
	{
		
		String action = "-1"; 
		Scanner sc = new Scanner(System.in);
		player = new Player(0, 0, myName, 1, classOrder);
		myDungeon = new Map(1);
		myMap = myDungeon.getMap();
		run = true;
		// check player hp > 0 and can run
		while(player.getHP() > 0 && run)
		{
		myDungeon.Print();
		action = "-1"; 
		while(action.equals("-1"))
		{
			System.out.println(); 
			System.out.print("\t \t \t MOVE (U/R/L/D), STATS (S), INVENTORY (I), CLASS (C) or QUIT(Q)? ");
			action = sc.next().substring(0, 1);
			if(action.toUpperCase().equals("S"))
			{
				System.out.println("\n \t \t \t \t \t " + myName + "'S STATS ");  
				player.showStat();
			}
			else if(action.toUpperCase().equals("I"))
			{
				showInventory();
			} else if (action.toUpperCase().equals("Q")) {
				quit();
			}
			else if (action.toUpperCase().equals("C")) {
				showClass();
			}
			else {
				// player move up
				if(action.toUpperCase().equals("U") && player.getX() - 1 > -1 && myMap[player.getX() - 1][player.getY()].isAc())
				{
					if(myMap[player.getX() - 1][player.getY()] instanceof Door)
					{
						if(Door.ask())
						{
							// new level
							myDungeon = new Map(player.getLevel() + 1);
							player.setHP(player.getLevel() * 10);
							player.setKey(false);
							myMap = myDungeon.getMap();
						}
					} else if(myMap[player.getX() - 1][player.getY()] instanceof Enemy) {
						Fight f = new Fight(myDungeon.getEnemy(player.getX() - 1, player.getY()));
						if(f.fight() == 1)
						{
							// win enemy
							victory();
							myDungeon.setTile(player.getX(), player.getY());
							player.setX(player.getX() - 1);
							myDungeon.placePlayer(player.getX(), player.getY());
						} else if(f.fight() == 2){
							// lose and die
							gameOver();
							break;
						} else {
							break;
						}
					} 
					else {
						// move to next door
					myDungeon.setTile(player.getX(), player.getY());
					player.setX(player.getX() - 1);
					myDungeon.placePlayer(player.getX(), player.getY());
					}					
				}
				// player move right
				else if(action.toUpperCase().equals("R") && player.getY() + 1 < myMap[0].length && myMap[player.getX()][player.getY() + 1].isAc())
				{
					if(myMap[player.getX()][player.getY() + 1] instanceof Door)
					{
						if(Door.ask())
						{
							myDungeon = new Map(player.getLevel() + 1);
							player.setHP(player.getLevel() * 10); 
							player.setKey(false);
							myMap = myDungeon.getMap();
						}
					}
					else if(myMap[player.getX()][player.getY() + 1] instanceof Enemy) {
						Fight f = new Fight(myDungeon.getEnemy(player.getX(), player.getY() + 1));
						if(f.fight() == 1)
						{
							victory();
							myDungeon.setTile(player.getX(), player.getY());
							player.setY(player.getY() + 1);
							myDungeon.placePlayer(player.getX(), player.getY());
						} else if(f.fight() == 2){
							gameOver();
							break;
						} else {
							break;
						}
					} 
					else {
					myDungeon.setTile(player.getX(), player.getY());
					player.setY(player.getY() + 1);
					myDungeon.placePlayer(player.getX(), player.getY());
					}
				}
				// player move down
				else if(action.toUpperCase().equals("D") && player.getX() + 1 < myMap.length && myMap[player.getX() + 1][player.getY()].isAc())
				{
					if(myMap[player.getX() + 1][player.getY()] instanceof Door)
					{
						if(Door.ask())
						{
							myDungeon = new Map(player.getLevel() + 1);
							player.setHP(player.getLevel() * 10); 
							player.setKey(false);
							myMap = myDungeon.getMap();
						}
					} else if(myMap[player.getX() + 1][player.getY()] instanceof Enemy) {
						Fight f = new Fight(myDungeon.getEnemy(player.getX() + 1, player.getY()));
						if(f.fight() == 1)
						{
							victory();
							myDungeon.setTile(player.getX(), player.getY());
							player.setX(player.getX() + 1);
							myDungeon.placePlayer(player.getX(), player.getY());
						} else if(f.fight() == 2){
							gameOver();
							break;
						} else {
							break;
						}
					}  else {
					myDungeon.setTile(player.getX(), player.getY());
					player.setX(player.getX() + 1);
					myDungeon.placePlayer(player.getX(), player.getY());
					}
				}
				// player move left
				else if(action.toUpperCase().equals("L") && player.getY() - 1 > -1 && myMap[player.getX()][player.getY() - 1].isAc())
				{
					if(myMap[player.getX()][player.getY() - 1] instanceof Door)
					{
						if(Door.ask())
						{
							myDungeon = new Map(player.getLevel() + 1);
							player.setHP(player.getLevel() * 10); 
							player.setKey(false);
							myMap = myDungeon.getMap();	
						}
					} else if(myMap[player.getX()][player.getY() - 1] instanceof Enemy) {
						Fight f = new Fight(myDungeon.getEnemy(player.getX(), player.getY() - 1));
						if(f.fight() == 1)
						{
							victory();
							myDungeon.setTile(player.getX(), player.getY());
							player.setY(player.getY() - 1); 
							myDungeon.placePlayer(player.getX(), player.getY());
						} else if(f.fight() == 2){
							gameOver();
							break;
						} else {
							break;
						}
					}  else {
					myDungeon.setTile(player.getX(), player.getY());
					player.setY(player.getY() - 1); 
					myDungeon.placePlayer(player.getX(), player.getY());
					}
				} else {
					System.out.println("\t \t \t \t Please enter a valid move.");
					action = "-1";
				}
			}
		}
		}
	}
	
	private static void victory()
	{
		// drop key to next level
		if(myDungeon.getEnemies() <= 1 && !(player.seeKey())) 
		{
			player.setKey(true);
			System.out.println("\t \t \t \t You won. It dropped a key. \n");
		} else {
			// got item
			int y = (int) ((Math.random() * 5));
			if(y == 1)
			{
				System.out.println("\t \t \t \t You won. You got an item! \n");
				player.addItem(new PlayerItem());
			}
		}
	}
	
	private static void quit() 
	{
		// quit
		System.out.println("\n \t \t \t \t Are you sure? (Y/N)");
		Scanner sc = new Scanner(System.in);
		String result = "-1"; 
		result = sc.next().substring(0, 1);
		if (result.toUpperCase().equals("Y")) {
			System.exit(0);
		} else if (result.toUpperCase().equals("N")) {
			return;
		} else {
			System.out.println("\n \t \t \t \t Please input y or n character");
			quit();
		}

	}
	
	private static void gameOver()
	{
		run = false;
		System.out.println("\n \t \t \t \tGAME OVER: YOU DIED" + "\n \t \t \t \t" + "LVL: " + player.getLevel());
		
		Scanner sc = new Scanner(System.in);
		String act = "-1";
		while(act.equals("-1")){
			System.out.print("\t \t \t \tPLAY AGAIN? (Y/N) ");
			act = sc.next();
			if(act.substring(0,1).toUpperCase().equals("Y"))
			{
				gameStart();
			} else {
				return; 
			}
		}
	
	}

	private static void showInventory() {
		
		System.out.println("\n \t \t \t \t INVENTORY");
		System.out.println(" \t \t \t \t" + player.showInventory());
		System.out.println("\n \t \t \t \t Please input item order");

		Scanner sc = new Scanner(System.in);
		int item = Integer.parseInt(sc.next());
		if (item <= 0) {
			return;
		} else if (item > player.getInvenSize()) {
			return;
		} else {
			player.showInventory(item);
		}
	}
 
	public static Player getPlayer() {
		// TODO Auto-generated method stub
		return player;
	}
	

	public static Map getDungeon() {
		// TODO Auto-generated method stub
		return myDungeon;
	}
	
	private static int showClass() {
		// show class
		System.out.println("\n \t \t \t \t CLASS");
		System.out.println("\t \t \t \t 1   Warrior" );
		System.out.println("\t \t \t \t 2   Thief" );
		System.out.println("\t \t \t \t 3   Wizard" );
		System.out.println("\t \t \t \t 4   Pirates" );		
		System.out.println("\t \t \t \t 5   Angel" );
		
		System.out.println("\n \t \t \t \t Please choose class");

		Scanner sc = new Scanner(System.in);
		int classOrder = Integer.parseInt(sc.next());
		if (classOrder < 1) {
			return -1;
		} else if (classOrder > 5) {
			return -1;
		} else {
			switch (classOrder) {
				case 1:
					System.out.println("\t \t \t \t Warrior detail" );
				break;
				case 2:
					System.out.println("\t \t \t \t Thief detail" );
					break;
				case 3:
					System.out.println("\t \t \t \t Wizard detail" );
					break;
				case 4:
					System.out.println("\t \t \t \t Pirates detail" );
					break;
				case 5:
					System.out.println("\t \t \t \t Angel detail" );
					break;
			}
			return classOrder;
		}
	}

	
	
}
