package Main;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import Classes.GolemClass;
import Classes.NinjaClass;
import Classes.PirateClass;
import Classes.WarriorClass;
import Classes.WizardClass;
import Consumables.*;
import Entities.BaseItem;
import Entities.Enemy;
import Entities.Fight;
import Entities.Player;
import MapSystem.Door;
import MapSystem.Map;
import Weapons.*;

public class Main {

	private static BaseItem[][] myMap;
	private static Map myDungeon;
	private static Player player;
	private static boolean run;
	private static String myName;
	private static int currentRoom = 1;
	private static int classOrder = -1;

	public static void main(String args[]) {
		// game start
		System.out.println("\t\t\t\t     Illuminated Dungeons\n");
		System.out.println("\t\t\t\t---------------------------------\n");
		System.out.println("\t\t\t\t---------------------------------\n");
		Scanner sc = new Scanner(System.in);
		System.out.print("\t \t \t \t   Press any key to continue");
		sc.nextLine();
		System.out.print("\n \t \t \t \t   What's your name? ");
		myName = sc.next();
		while (classOrder < 0) {
			System.out.print("\n \t \t \t \t   Please choose your class ");
			classOrder = showClass();
		}

		gameStart();
	}

	public static void gameStart() {
		String action = "-1";
		Scanner sc = new Scanner(System.in);
		player = new Player(0, 0, myName, 1, classOrder);
		myDungeon = new Map(currentRoom);
		myMap = myDungeon.getMap();
		run = true;
		// check player hp > 0 and can run
		while (player.getHP() > 0 && run) {
			myDungeon.Print();
			action = "-1";
			while (action.equals("-1")) {
				System.out.println();
				System.out.print("\t \t \t Move (U/R/L/D), Stats (S), Inventory (I) or Quit(Q)? ");
				action = sc.next().substring(0, 1);
				if (action.toUpperCase().equals("S")) {
					System.out.println("\n \t \t \t \t \t " + myName + "'s Stats ");
					System.out.println(player.character.getStats());
				} else if (action.toUpperCase().equals("I")) {
					showInventory();
				} else if (action.toUpperCase().equals("Q")) {
					quit();
				} else {
					// player move up
					if (action.toUpperCase().equals("U") && player.getX() - 1 > -1
							&& myMap[player.getX() - 1][player.getY()].isAc()) {
						if (myMap[player.getX() - 1][player.getY()] instanceof Door) {
							if (Door.ask()) {
								// new level
								player.setKey(false);
								currentRoom++;
								myDungeon = new Map(currentRoom);
								myMap = myDungeon.getMap();
							}
						} else if (myMap[player.getX() - 1][player.getY()] instanceof Enemy) {
							Fight f = new Fight(myDungeon.getEnemy(player.getX() - 1, player.getY()));
							if (f.fight() == 1) {
								// win enemy
								victory();
								myDungeon.setTile(player.getX(), player.getY());
								player.setX(player.getX() - 1);
								myDungeon.placePlayer(player.getX(), player.getY());
							} else if (f.fight() == 2) {
								// lose and die
								gameOver();
								break;
							} else {
								break;
							}
						} else {
							// move to next door
							myDungeon.setTile(player.getX(), player.getY());
							player.setX(player.getX() - 1);
							myDungeon.placePlayer(player.getX(), player.getY());
						}
					}
					// player move right
					else if (action.toUpperCase().equals("R") && player.getY() + 1 < myMap[0].length
							&& myMap[player.getX()][player.getY() + 1].isAc()) {
						if (myMap[player.getX()][player.getY() + 1] instanceof Door) {
							if (Door.ask()) {
								// new level
								player.setKey(false);
								currentRoom++;
								myDungeon = new Map(currentRoom);
								myMap = myDungeon.getMap();
							}
						} else if (myMap[player.getX()][player.getY() + 1] instanceof Enemy) {
							Fight f = new Fight(myDungeon.getEnemy(player.getX(), player.getY() + 1));
							if (f.fight() == 1) {
								victory();
								myDungeon.setTile(player.getX(), player.getY());
								player.setY(player.getY() + 1);
								myDungeon.placePlayer(player.getX(), player.getY());
							} else if (f.fight() == 2) {
								gameOver();
								break;
							} else {
								break;
							}
						} else {
							myDungeon.setTile(player.getX(), player.getY());
							player.setY(player.getY() + 1);
							myDungeon.placePlayer(player.getX(), player.getY());
						}
					}
					// player move down
					else if (action.toUpperCase().equals("D") && player.getX() + 1 < myMap.length
							&& myMap[player.getX() + 1][player.getY()].isAc()) {
						if (myMap[player.getX() + 1][player.getY()] instanceof Door) {
							if (Door.ask()) {
								// new level
								player.setKey(false);
								currentRoom++;
								myDungeon = new Map(currentRoom);
								myMap = myDungeon.getMap();
							}
						} else if (myMap[player.getX() + 1][player.getY()] instanceof Enemy) {
							Fight f = new Fight(myDungeon.getEnemy(player.getX() + 1, player.getY()));
							if (f.fight() == 1) {
								victory();
								myDungeon.setTile(player.getX(), player.getY());
								player.setX(player.getX() + 1);
								myDungeon.placePlayer(player.getX(), player.getY());
							} else if (f.fight() == 2) {
								gameOver();
								break;
							} else {
								break;
							}
						} else {
							myDungeon.setTile(player.getX(), player.getY());
							player.setX(player.getX() + 1);
							myDungeon.placePlayer(player.getX(), player.getY());
						}
					}
					// player move left
					else if (action.toUpperCase().equals("L") && player.getY() - 1 > -1
							&& myMap[player.getX()][player.getY() - 1].isAc()) {
						if (myMap[player.getX()][player.getY() - 1] instanceof Door) {
							if (Door.ask()) {
								// new level
								player.setKey(false);
								currentRoom++;
								myDungeon = new Map(currentRoom);
								myMap = myDungeon.getMap();
							}
						} else if (myMap[player.getX()][player.getY() - 1] instanceof Enemy) {
							Fight f = new Fight(myDungeon.getEnemy(player.getX(), player.getY() - 1));
							if (f.fight() == 1) {
								victory();
								myDungeon.setTile(player.getX(), player.getY());
								player.setY(player.getY() - 1);
								myDungeon.placePlayer(player.getX(), player.getY());
							} else if (f.fight() == 2) {
								gameOver();
								break;
							} else {
								break;
							}
						} else {
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

	private static void victory() {
		//10% chance to get an item after winning a battle (nextInt() does not include max values so in this case 0 to 9 will be returned)
		if(ThreadLocalRandom.current().nextInt(0, 9) == 0) {
			BaseWeapon weapon = getWeapon();
			String oldStats = player.character.getStats();
			System.out.println("\t\t\t\tThe enemy dropped a weapon! Here's how the stats compare to what you have:\n");
			boolean validResponse = false;
			Scanner internalSc = new Scanner(System.in);
			while(!validResponse) {
				System.out.println("\t\t\t" + player.myWeapon.getWeaponName() + "\t" + weapon.getWeaponName());
				System.out.println("\t\t\tAttack: " + player.myWeapon.getWeaponAttack() + "\t" + weapon.getWeaponAttack());
				System.out.println("\t\t\tDefense: " + player.myWeapon.getWeaponDefense() + "\t" + weapon.getWeaponDefense());
				System.out.println("\t\t\tSpeed: " + player.myWeapon.getWeaponSpeed() + "\t" + weapon.getWeaponSpeed());
				System.out.println("\t\t\tMagic: " + player.myWeapon.getWeaponMagic() + "\t" + weapon.getWeaponMagic()+ "\n");
				
				System.out.print("\t\t\t\tDo you want to swap your weapon? (y/n): ");
				String response = internalSc.nextLine();
				if(response.toLowerCase().equals("y")) {
					System.out.println("\t\t\t\tWeapons Swapped. OLD STATS: ");
					System.out.println(oldStats);
					System.out.println("\t\t\t\tNEW STATS: ");
					System.out.println(player.character.getStats());
					validResponse = true;
				}
				else if(response.toLowerCase().equals("n")) {
					System.out.println("\t\t\t\tYou tossed the new weapon aside.");
					validResponse = true;
				}
				else
				{
					System.out.println("\t\t\t\tPlease enter a valid response\n.");
				}
			}
		}
		
		//33% chance to get an item after winning a battle (nextInt() does not include max values so in this case 0 to 2 will be returned)
		if(ThreadLocalRandom.current().nextInt(0, 3) == 0) {
			BaseConsumable itemToAdd = getConsumable();
			System.out.println("\t\t\t\tThe enemy dropped an item! A " + itemToAdd.GetConsumableName() + " was added to your inventory.\n");
			player.myInv.add(itemToAdd);
		}
		
		if (myDungeon.getEnemies() <= 3 && !(player.seeKey())) {
			player.setKey(true);
			System.out.println("\t \t \t \t The enemy dropped a key. You can now move on to the next level. \n");
		} 

	}
	
	private static BaseConsumable getConsumable() {
		int consumableDeterminer = ThreadLocalRandom.current().nextInt(0, 775);
		
		if(consumableDeterminer < 200) {
			return new ConsumableSmallHealthPotion();
		}
		else if(consumableDeterminer >= 200 && consumableDeterminer < 400) {
			return new ConsumableSmallMagicPotion();
		}
		else if(consumableDeterminer >= 400 && consumableDeterminer < 500) {
			return new ConsumableSmallElixer();
		}
		else if(consumableDeterminer >= 500 && consumableDeterminer < 600) {
			return new ConsumableLargeHealthPotion();
		}
		else if(consumableDeterminer >= 600 && consumableDeterminer < 700) {
			return new ConsumableLargeMagicPotion();
		}
		else if(consumableDeterminer >= 700 && consumableDeterminer < 750) {
			return new ConsumableLargeElixer();
		}
		else {
			return new ConsumableFairy();
		}
	}
	
	private static BaseWeapon getWeapon() {
		int tierDeterminer = ThreadLocalRandom.current().nextInt(0, 100);
		
		if(tierDeterminer < 80) {
			int weaponDeterminer = ThreadLocalRandom.current().nextInt(0, 5);
			switch (weaponDeterminer) {
				case 0:
					return new IronHook();
				case 1:
					return new IronShield();
				case 2:
					return new IronShuriken();
				case 3:
					return new IronStaff();
				case 4:
					return new IronSword();
			}
		}
		else {
			int weaponDeterminer = ThreadLocalRandom.current().nextInt(0, 5);
			switch (weaponDeterminer) {
				case 0:
					return new DiamondHook();
				case 1:
					return new DiamondShield();
				case 2:
					return new DiamondShuriken();
				case 3:
					return new DiamondStaff();
				case 4:
					return new DiamondSword();
			}
		}
		
		//This should never be reached, but is required to compile
		return null;
	}

	private static void quit() {
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

	private static void gameOver() {
		run = false;
		System.out.println("\n \t \t \t \tGAME OVER: YOU DIED" + "\n \t \t \t \t" + "LVL: " + player.character.currentLevel);

		Scanner sc = new Scanner(System.in);
		String act = "-1";
		while (act.equals("-1")) {
			System.out.print("\t \t \t \tPLAY AGAIN? (Y/N) ");
			act = sc.next();
			if (act.substring(0, 1).toUpperCase().equals("Y")) {
				// play again
				gameStart();
			} else {
				return;
			}
		}

	}

	private static void showInventory() {

		System.out.println("\n \t \t \t \t INVENTORY");
		player.showInventory();
	}

	public static Player getPlayer() {
		return player;
	}

	public static Map getDungeon() {
		return myDungeon;
	}

	private static int showClass() {
		// show class
		System.out.println("\n \t \t \t \t CLASS");
		System.out.println("\t \t \t \t 1. Warrior");
		System.out.println("\t \t \t \t 2. Wizard");
		System.out.println("\t \t \t \t 3. Pirate");
		System.out.println("\t \t \t \t 4. Golem");
		System.out.println("\t \t \t \t 5. Ninja");

		System.out.print("\n \t \t \t \t Please choose class: ");

		Scanner sc = new Scanner(System.in);
		int classOrder = sc.nextInt();
		if (classOrder < 1) {
			return -1;
		} 
		else if (classOrder > 5) {
			return -1;
		} 
		else {
			switch (classOrder) {
			case 1:
				System.out.println(WarriorClass.classDescription);
				break;
			case 2:
				System.out.println(WizardClass.classDescription);
				break;
			case 3:
				System.out.println(PirateClass.classDescription);
				break;
			case 4:
				System.out.println(GolemClass.classDescription);
				break;
			case 5:
				System.out.println(NinjaClass.classDescription);
				break;
			}
			System.out.print("\t\t\t\tDo you want to play as this class? (y/n): ");
			String response = sc.next();
			if (response.toLowerCase().equals("y")) {
				return classOrder;
			} 
			else {
				return showClass();
			}
		}
	}

}
