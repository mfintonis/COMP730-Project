package Spells;

public class SpellOblivion extends BaseSpell {

	public SpellOblivion(boolean spellUnlocked) {
		attackStrength = 100;
		magicPointCost = 50;
		spellDescription = "A powerful magic attack, capabale of one-shotting some enemies, but has a high MP cost";
		spellName = "Oblivion";
		unlocked = spellUnlocked;
	}	
	
}
