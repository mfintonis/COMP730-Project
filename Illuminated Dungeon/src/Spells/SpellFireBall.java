package Spells;

public class SpellFireBall extends BaseSpell {

	public SpellFireBall(boolean spellUnlocked) {
		attackStrength = 20;
		magicPointCost = 10;
		spellDescription = "A blazing fire-based attack. Does moderate, but has a higher than average MP cost.";
		spellName = "Fire Ball";
		unlocked = spellUnlocked;
	}	
	
}
