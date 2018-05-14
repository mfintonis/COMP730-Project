package Spells;

public class SpellMagicMissle extends BaseSpell {

	public SpellMagicMissle(boolean spellUnlocked) {
		attackStrength = 10;
		magicPointCost = 5;
		spellDescription = "A basic magic attack. Does low damage, but has a low MP cost";
		spellName = "Magic Missle";
		unlocked = spellUnlocked;
	}	
	
}
