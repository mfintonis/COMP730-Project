package Spells;

public class SpellAvalanche extends BaseSpell {

	public SpellAvalanche(boolean spellUnlocked) {
		attackStrength = 300;
		magicPointCost = 75;
		spellDescription = "The Golem summons an avalanche of rocks to fall on their opponent. Very high damage and high MP cost.";
		spellName = "Avalanche";
		unlocked = spellUnlocked;
	}	
	
}
