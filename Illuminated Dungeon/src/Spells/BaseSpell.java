package Spells;

public class BaseSpell {
	
	//Unlocked was never used, but was intended to allow classes to learn new spells as they leveled up
	protected boolean unlocked;
	protected int attackStrength;
	protected int magicPointCost;
	protected String spellDescription;
	protected String spellName;
	
	public int getAttackStrength() {
		return attackStrength;
	}
	
	public int getMagicPointCost() {
		return magicPointCost;
	}
	
	public String getDescription() {
		return spellDescription;
	}
	
	public String getSpellName() {
		return spellName;
	}
	
	public boolean getUnlocked() {
		return unlocked;
	}
	
	public void setUnlocked(boolean spellUnlocked) {
		unlocked = spellUnlocked;
	}
	
}
