package Classes;

import java.util.ArrayList;

import Spells.*;

public class WizardClass extends Character {
	
	public static String classDescription = "\t \t \t \tThe Wizard relies on their magical prowess to eleminiate enemies. Managing your Ability Points is key with this class as the melee ability is very weak.";
	
	public WizardClass() {
		magicSpells = new ArrayList<BaseSpell>();
		baseAttack = 0;
		baseDefense = 1;
		baseSpeed = 1;
		baseMagic = 2;
		baseHP = 100;
		baseMP = 150;
		currentHP = 100;
		currentMP = 150;
		currentLevel = 1;
		currentXP = 0;
		xpToNextLevel = 20;		
		
		magicSpells.add(new SpellMagicMissle(true));
		magicSpells.add(new SpellFireBall(true));
		magicSpells.add(new SpellOblivion(true));
	}
	
	public void checkForLevelUp() {
		super.checkForLevelUp();
		
		//Put logic for checking for new unlocked spell here (not enough time to implement)
	}
}
