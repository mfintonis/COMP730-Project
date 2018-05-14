package Classes;

import java.util.ArrayList;

import Spells.*;

public class GolemClass extends Character {
	
	public static String classDescription = "\t \t \t \tThe Golem's body, being made of stone, makes incoming attacks do low damage compared to the other classes. The Golem has average attack and magic stats, but very low speed due to their size.";
	
	public GolemClass() {
		magicSpells = new ArrayList<BaseSpell>();
		baseAttack = 1;
		baseDefense = 2;
		baseSpeed = 0;
		baseMagic = 1;
		baseHP = 125;
		baseMP = 75;
		currentHP = 125;
		currentMP = 75;
		currentLevel = 1;
		currentXP = 0;
		xpToNextLevel = 20;		
		
		magicSpells.add(new SpellFireBall(true));
		magicSpells.add(new SpellAvalanche(true));
	}
}
