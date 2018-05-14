package Classes;

public class NinjaClass extends Character {
	
	public static String classDescription = "\t \t \t \tThe Ninja is this games 'Glass Cannon'. Fast and powerful, but has no defesive stats to protect against attacks. One hit kill the opponenet or expect the same to happen to you.";
	
	public NinjaClass() {
		baseAttack = 2;
		baseDefense = 0;
		baseSpeed = 2;
		baseMagic = 0;
		baseHP = 75;
		baseHP = 0;
		currentHP = 75;
		currentMP = 0;
		currentLevel = 1;
		currentXP = 0;
		xpToNextLevel = 20;		
	}
}
