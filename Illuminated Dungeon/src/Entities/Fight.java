package Entities;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import Main.Main;
import Spells.BaseSpell;

public class Fight {

	static Enemy Mye;
	static Boolean over;
	static int seHP;
	static int spHP;

	public Fight(Enemy e) {
		Mye = e;
		over = false;
		seHP = Mye.getHP();
	}

	public boolean getOver() {
		return over;
	}

	public int fight() {
		while (!over) {
			if (Mye.getHP() <= 0) {
				int xpGained = Mye.getLevel() * ThreadLocalRandom.current().nextInt(5, 20);
				System.out.println("\t\t\t\tThe " + Mye.getName() + " was slain! You gained " + xpGained + " XP!");
				Main.getPlayer().character.currentXP += xpGained;
				Main.getPlayer().character.checkForLevelUp();
				return 1;
			}
			if (Main.getPlayer().getHP() <= 0)
				return 2;
			System.out.println("\n \t \t \t \t " + Main.getPlayer().getName() + "'s HP:" + Main.getPlayer().getHP());
			System.out.println("\t \t \t \t " + Main.getPlayer().getName() + "'s MP:" + Main.getPlayer().getMP());
			System.out.println("\n \t \t \t \t " + Mye.getName() + "'s HP:" + Mye.getHP() + "\n");
			String action = "-1";
			// loop until one of them have hp < 0
			while (action.equals("-1")) {
				System.out.print("\t \t \t \t Fight, Magic, Inventory, or run? (F/M/I/R) ");
				Scanner sc = new Scanner(System.in);
				action = sc.next().toUpperCase().substring(0, 1);
				if (action.equals("R")) {
					// run away, don't fight
					System.out.println("\t \t \t \t You ran away. \n");
					over = true;
					return 3;
				} else if(action.equals("M")) {
					List<BaseSpell> spells = Main.getPlayer().character.magicSpells;
					if(Main.getPlayer().character.magicSpells != null && !Main.getPlayer().character.magicSpells.isEmpty()) {
						String strSpells = "";
						for(int i = 0; i < spells.size(); i++) {
							strSpells += "\n\t\t\t\t" + (i + 1) + ". " + spells.get(i).getSpellName() + " - Costs " + spells.get(i).getMagicPointCost() + " MP to cast";
						}
						Scanner internalSc = new Scanner (System.in);
						int spellSelected = -1;
						
						while(spellSelected != 0) {
							System.out.println(strSpells);
							System.out.print("\n\t\t\t\tEnter a number to view description of chosen spell or enter 0 to return: ");
							
							try {
								spellSelected = Integer.parseInt(internalSc.nextLine());							
								BaseSpell spell = spells.get(spellSelected - 1);
								System.out.println("\t\t\t\t" + spell.getDescription());
								System.out.print("\t\t\t\tUse spell? (y/n): ");
								String yesno = internalSc.nextLine();
								if (yesno.toLowerCase().equals("y")) {
									if(Main.getPlayer().character.currentMP < spell.getMagicPointCost()) {
										System.out.println("\t\t\t\tYou don't have enough MP to cast this!");
									}
									else {
										Main.getPlayer().character.currentMP -= spell.getAttackStrength();
										if (Main.getPlayer().getSpeed() > Mye.mySpeed) {
											pMagicAttack(spell);
											if (Mye.getHP() <= 0) {
												int xpGained = Mye.getLevel() * ThreadLocalRandom.current().nextInt(5, 20);
												System.out.println("\t\t\t\tThe " + Mye.getName() + " was slain! You gained " + xpGained + " XP!");
												Main.getPlayer().character.currentXP += xpGained;
												Main.getPlayer().character.checkForLevelUp();
												return 1;
											}
											eAttack();
											if (Main.getPlayer().getHP() <= 0) {
												// Check for Fairy in inventory
												boolean fairyFound = false;
												for (int i = 0; i < Main.getPlayer().myInv.size(); i++) {
													if (Main.getPlayer().myInv.get(i).GetConsumableName().equals("Fairy")) {
														Main.getPlayer().setHP(Main.getPlayer().character.baseHP);
														Main.getPlayer().setMP(Main.getPlayer().character.baseMP);
														System.out.println("\t\t\t\tThe fairy following noticed your peril and fully restored you before your last breath. The fairy vanished as it used its power");
														fairyFound = true;
													}
												}
												if(!fairyFound) {
													return 2;
												}
											}
										}
										else {
											eAttack();
											if (Main.getPlayer().getHP() <= 0) {
												// Check for Fairy in inventory
												boolean fairyFound = false;
												for (int i = 0; i < Main.getPlayer().myInv.size(); i++) {
													if (Main.getPlayer().myInv.get(i).GetConsumableName().equals("Fairy")) {
														Main.getPlayer().setHP(Main.getPlayer().character.baseHP);
														Main.getPlayer().setMP(Main.getPlayer().character.baseMP);
														System.out.println("\t\t\t\tThe fairy following noticed your peril and fully restored you before your last breath. The fairy vanished as it used its power");
														fairyFound = true;
													}
												}
												if(!fairyFound) {
													return 2;
												}
											}
											pMagicAttack(spell);
											if (Mye.getHP() <= 0) {
												int xpGained = Mye.getLevel() * ThreadLocalRandom.current().nextInt(5, 20);
												System.out.println("\t\t\t\tThe " + Mye.getName() + " was slain! You gained " + xpGained + " XP!");
												Main.getPlayer().character.currentXP += xpGained;
												Main.getPlayer().character.checkForLevelUp();
												return 1;
											}
										}
										spellSelected = 0;
									}
								}
							}
							catch (Exception e) {
								System.out.println("\t\t\t\tPlease enter a valid input.");
							}
						}
					} else {
						System.out.println("\t\t\t\tYou have no magic spells!");
					}
				}
				
				else if (action.equals("F")) {
					// continue fight
					int y = (int) ((Math.random() * 2));
					if (Main.getPlayer().getSpeed() > Mye.mySpeed) {
						pAttack();
						if (Mye.getHP() <= 0) {
							int xpGained = Mye.getLevel() * ThreadLocalRandom.current().nextInt(5, 20);
							System.out.println("\t\t\t\tThe " + Mye.getName() + " was slain! You gained " + xpGained + " XP!");
							Main.getPlayer().character.currentXP += xpGained;
							Main.getPlayer().character.checkForLevelUp();
							return 1;
						}
						eAttack();
						if (Main.getPlayer().getHP() <= 0) {
							// Check for Fairy in inventory
							boolean fairyFound = false;
							for (int i = 0; i < Main.getPlayer().myInv.size(); i++) {
								if (Main.getPlayer().myInv.get(i).GetConsumableName().equals("Fairy")) {
									Main.getPlayer().setHP(Main.getPlayer().character.baseHP);
									Main.getPlayer().setMP(Main.getPlayer().character.baseMP);
									System.out.println("\t\t\t\tThe fairy following noticed your peril and fully restored you before your last breath. The fairy vanished as it used its power");
									fairyFound = true;
								}
							}
							if(!fairyFound) {
								return 2;
							}
						}
					} else {
						eAttack();
						if (Main.getPlayer().getHP() <= 0) {
							// Check for Fairy in inventory
							boolean fairyFound = false;
							for (int i = 0; i < Main.getPlayer().myInv.size(); i++) {
								if (Main.getPlayer().myInv.get(i).GetConsumableName().equals("Fairy")) {
									Main.getPlayer().setHP(Main.getPlayer().character.baseHP);
									Main.getPlayer().setMP(Main.getPlayer().character.baseMP);
									System.out.println("\t\t\t\tThe fairy following noticed your peril and fully restored you before your last breath. The fairy vanished as it used its power");
									fairyFound = true;
								}
							}
							if(!fairyFound) {
								return 2;
							}
						}
						pAttack();
						if (Mye.getHP() <= 0) {
							int xpGained = Mye.getLevel() * ThreadLocalRandom.current().nextInt(5, 20);
							System.out.println("\t\t\t\tThe " + Mye.getName() + " was slain! You gained " + xpGained + " XP!");
							Main.getPlayer().character.currentXP += xpGained;
							Main.getPlayer().character.checkForLevelUp();
							return 1;
						}
					}
				}
			}
		}
		return 3;
	}

	private void eAttack() {
		// Damage based on enemy attack, player defense, and a bit of random variance
		int damageDealt = (int) Math.round((Mye.myAttack - Main.getPlayer().getDefense()) * ThreadLocalRandom.current().nextDouble(.5, 1.5));
		if (damageDealt <= 0) {
			damageDealt = 1;
		}
		System.out.println("\n \t \t \t \t " + Mye.getName() + " dealt " + damageDealt + " damage to " + Main.getPlayer().getName());
		Main.getPlayer().setHP(Main.getPlayer().character.currentHP - damageDealt);
	}

	private void pAttack() {
		// Damage based on player attack, enemy defense, and a bit of random variance
		int damageDealt = (int) Math.round((Main.getPlayer().getAttackDamage() - Mye.myDefense) * ThreadLocalRandom.current().nextDouble(.5, 1.5));
		if (damageDealt <= 0) {
			damageDealt = 1;
		}
		System.out.println("\n \t \t \t \t " + Main.getPlayer().getName() + " dealt " + damageDealt + " damage to " + Mye.getName());
		Mye.setHP(Mye.getHP() - damageDealt);
	}

	private void pMagicAttack(BaseSpell spell) {
		// Damage based on player magic, enemy defense, and a bit of random variance
		int damageDealt = (int) Math.round((Main.getPlayer().getMagicDamage() + spell.getAttackStrength() - Mye.myDefense) * ThreadLocalRandom.current().nextDouble(.5, 1.5));
		if (damageDealt <= 0) {
			damageDealt = 1;
		}
		System.out.println("\n \t \t \t \t " + Main.getPlayer().getName() + " dealt " + damageDealt + " damage to " + Mye.getName());
		Mye.setHP(Mye.getHP() - damageDealt);
	}

}
