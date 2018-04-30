package Dungeon;

import java.util.ArrayList;
import java.util.Scanner; 
public class Player extends Actor {

	private static int myX;
	private static int myY; 
	private static int myHP;
	private static int myAttack;
	private static int myDefence;
	private static ArrayList<PlayerItem> myInv; 
	private PlayerItem equipedItem;
	private static boolean haveKey;
	private static Character character;
	
	public static boolean seeKey()
	{
		return haveKey; 
	}
	
	public void setKey(Boolean b)
	{
		haveKey = b;
	}
	
	public Player(int x, int y, String name, int level, int classOrder)
	{
		super(x, y, name, level);
		myHP = 100;
		myInv = new ArrayList<PlayerItem>();
		haveKey = false;
		switch(classOrder) {
			case 1:
				character = Character.Warrior;
				break;
			case 2: 
				character = Character.Thief;
				break;
			case 3:
				character = Character.Wizard;
				break;
			case 4:
				character = Character.Pirates;
				break;
			case 5: 
				character = Character.Angel;
				break;
			default:
				break;
		}
		this.addItem(new PlayerItem());
	}
	
	public void addItem(PlayerItem i)
	{
		myInv.add(i);
	}
	
	public String showInventory() {
		String str = "";
		for (int i = 0; i < myInv.size(); i++) {
			str += (i+1) + "   "+  myInv.get(i).getName() + "\n\t\t\t\t";
		}
		return str;
	}
	
	public void showInventory(int index) {
		System.out.println(" \t \t \t \t" + myInv.get(index).toString());
		
		System.out.println("\n \t \t \t \t Would you like to equip this item? (Y/N)");
		Scanner sc = new Scanner(System.in);
		String result = "-1"; 
		result = sc.next().substring(0, 1);
		if (result.toUpperCase().equals("Y")) {
			this.setEquipedItem(myInv.get(index));
		} else if (result.toUpperCase().equals("N")) {
			return;
		} else {
			System.out.println("\n \t \t \t \t Please input y or n character");
			return;
		}
		
	}
	
	public void setHP(int i)
	{
		myHP = i; 
	}
	
	public int getHP()
	{
		return myHP; 
	}
		
	public String toString()
	{
		return "P"; 
	}

	public void setX(int r) {
	
		myX = r;
	}
	
	public void setY(int c) {
	
		myY = c;
	}
	
	public int getX() {
	
		return myX;
	}
	
	public int getY() {
		
		return myY;
	}
	
	public int getInvenSize() {
		return myInv.size();
	}

	public PlayerItem getEquipedItem() {
		return equipedItem;
	}

	public void setEquipedItem(PlayerItem equipedItem) {
		this.equipedItem = equipedItem;
	}
	
	public void showStat() {
		System.out.println("\t \t \t \t \t HP: " + getHP());
		if (this.equipedItem !=  null) {
			System.out.println("\t \t \t \t \t HP: " + getEquipedItem().toString());
		}
	}
}
