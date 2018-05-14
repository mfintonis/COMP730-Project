package Entities;

import java.util.concurrent.ThreadLocalRandom;

public class Enemy extends Actor{

	public int myHP;
	public String name; 
	public int myAttack;
	public int myDefense;
	public int mySpeed;
	
	public Enemy(int x, int y, String name, int level) 
	{
		super(x, y, name, level);
		setHP(ThreadLocalRandom.current().nextInt(20, 40) * level);
		myAttack = ThreadLocalRandom.current().nextInt(10, 20) * level;
		myDefense = ThreadLocalRandom.current().nextInt(5, 10) * level;
		mySpeed = ThreadLocalRandom.current().nextInt(2, 15) * level;
		myLevel = level;
		makeName();
	}
	

	public String toString()
	{
		return "E";
	}
	
	public void setHP(int h)
	{
		myHP = h;
	}	
	
	public int getHP()
	{
		return myHP; 
	}
	
	public int getLevel()
	{
		return myLevel; 
	}
	
	public String getName()
	{
		return name; 
	}
	
	public void makeName()
	{
		 int r = (int) (Math.random() * 10) + 1;
	     String newName = "Enemy";
	     switch (r) {
	            case 1:  newName = "Ghoul";
	            break;
	            case 2:  newName = "Zombie";
	            break;
	            case 3:  newName = "Vampire";
	            break;
	            case 4:  newName = "Witch";
	            break;
	            case 5:  newName = "Serpent";
	            break;
	            case 6:  newName = "Imp";
	            break;
	            case 7:  newName = "Orc";
	            break;
	            case 8:  newName = "Wizard";
	            break;
	            case 9:  newName = "Medusa";
	            break;
	            case 10: newName = "Demon";
	            break;
	        	}
	      name = newName;
	}

	public Boolean isAc() 
	{
		return true;
	}
	
}
