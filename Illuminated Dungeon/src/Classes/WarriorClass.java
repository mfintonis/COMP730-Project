package Classes;

public class WarriorClass extends Character {
	
	public static String classDescription = "\t \t \t \tThe Warrior has high attack damage, but no magical abilities. The class is simple and a good choice for beginner players.";
	
	public WarriorClass() {
		baseAttack = 2;
		baseDefense = 1;
		baseSpeed = 1;
		baseMagic = 0;
		baseHP = 100;
		baseMP = 0;
		currentHP = 100;
		currentMP = 0;
		currentLevel = 1;
		currentXP = 0;
		xpToNextLevel = 20;		
	}
}
