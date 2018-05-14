package Classes;

import java.util.List;

import Spells.BaseSpell;

public abstract class Character {
	
	public List<BaseSpell> magicSpells;
	public double baseAttack;
	public double baseDefense;
	public double baseSpeed;
	public double baseMagic;
	public int baseHP;
	public int baseMP;	
	public int currentHP;
	public int currentMP;
	public int currentLevel;
	public int currentXP;
	int xpToNextLevel;
	public static String classDescription;
		
	public void checkForLevelUp() {
		if(currentXP > xpToNextLevel) {
			int oldHP = baseHP;
			int oldMP = baseMP;
			double oldAttack = baseAttack;
			double oldDefense = baseDefense;
			double oldSpeed = baseSpeed;
			double oldMagic= baseMagic;
			currentXP = currentXP - xpToNextLevel;
			currentLevel++;
			xpToNextLevel =  20 * currentLevel;
			baseHP = baseHP + (int)Math.round((oldHP + 10) * (currentLevel * .1));
			baseMP = baseMP + (int)Math.round((oldMP + 5) * (currentLevel * .1));
			baseAttack += (baseAttack + 2) + (currentLevel * .25);
			baseDefense += (baseDefense + 2) + (currentLevel * .25);
			baseSpeed += (baseSpeed + 2) + (currentLevel * .25);
			baseMagic += (baseMagic) + (currentLevel * .2);
			currentHP = baseHP;
			currentMP = baseMP;
			System.out.println("\t\t\t\tYou've leveled up to level " + currentLevel + "!");
			System.out.println("\t\t\t\tHP stat raised from " + oldHP + " to " + baseHP + ".");
			System.out.println("\t\t\t\tMP stat raised from " + oldMP + " to " + baseMP + ".");
			System.out.println("\t\t\t\tAttack stat raised from " + oldAttack + " to " + baseAttack + ".");
			System.out.println("\t\t\t\tDefense stat raised from " + oldDefense + " to " + baseDefense + ".");
			System.out.println("\t\t\t\tSpeed stat raised from " + oldSpeed + " to " + baseSpeed + ".");
			System.out.println("\t\t\t\tMagic stat raised from " + oldMagic + " to " + baseMagic + ".");
			checkForLevelUp();
		}
		else
		{
			System.out.println("\t\t\t\tYou need " + (xpToNextLevel - currentXP) + " for your next level.");
		}
	}
	
	public String getStats() {
		String strToReturn = "";
		strToReturn += "\t\t\t\tCurrent Level: " + currentLevel + "\n";
		strToReturn += "\t\t\t\tHP: " + currentHP + "/" + baseHP + "\n";
		strToReturn += "\t\t\t\tMP: " + currentMP + "/" + baseMP + "\n";
		strToReturn += "\t\t\t\tXP: " + currentXP + "/" + xpToNextLevel + "\n";
		strToReturn += "\t\t\t\tAttack: " + baseAttack + "\n";
		strToReturn += "\t\t\t\tDefense: " + baseDefense + "\n";
		strToReturn += "\t\t\t\tMagic: " + baseMagic + "\n";
		strToReturn += "\t\t\t\tSpeed: " + baseSpeed + "\n";
		
		return strToReturn;
	}
}
