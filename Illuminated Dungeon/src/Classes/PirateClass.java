package Classes;

public class PirateClass extends Character {
	
	public static String classDescription = "\t \t \t \tThe pirate has normal attack and defense, but has excellent speed letting him be the first to attack in most encounters. The Priate also has the extra passive of having a higher chance to get loot at the end of a battle.";
	
	public PirateClass() {
		baseAttack = 1;
		baseDefense = 1;
		baseSpeed = 2;
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
