package Entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Classes.*;
import Classes.Character;
import Consumables.*;
import MapSystem.*;
import Weapons.*;

public class Player extends Actor {

	private int myX;
	private int myY;
	private boolean haveKey;
	public BaseWeapon myWeapon;
	public Character character;
	public List<BaseConsumable> myInv;
	
	public boolean seeKey() {
		return haveKey;
	}

	public void setKey(Boolean b) {
		haveKey = b;
	}

	public Player(int x, int y, String name, int level, int classOrder) {
		// init player
		super(x, y, name, level);
		// myHP = 100;
		myInv = new ArrayList<BaseConsumable>();
		haveKey = false;
		// init class
		switch (classOrder) {
		case 1:
			character = new WarriorClass();
			myWeapon = new BronzeSword();
			break;
		case 2:
			character = new WizardClass();
			myWeapon = new BronzeStaff();
			myInv.add(new ConsumableSmallMagicPotion());
			myInv.add(new ConsumableSmallMagicPotion());
			break;
		case 3:
			character = new PirateClass();
			myWeapon = new BronzeHook();
			break;
		case 4:
			character = new GolemClass();
			myWeapon = new BronzeShield();
			myInv.add(new ConsumableSmallMagicPotion());
			myInv.add(new ConsumableSmallMagicPotion());
			break;
		case 5:
			character = new NinjaClass();
			myWeapon = new BronzeShuriken();
			break;
		default:
			break;
		}

		myInv.add(new ConsumableSmallHealthPotion());
		myInv.add(new ConsumableSmallHealthPotion());
		myInv.add(new ConsumableSmallHealthPotion());
	}

	public void showInventory() {
		String inv = "";
		inv += "\n				1. Weapon - " + myWeapon.getWeaponName();
		for (int i = 0; i < myInv.size(); i++) {
			inv += "\n				" + (i + 2) + ". " + myInv.get(i).GetConsumableName();
		}

		Scanner sc = new Scanner(System.in);

		int itemSelected = -1;

		while (itemSelected != 0) {
			System.out.println(inv);
			System.out.print("\n\t\t\t\tEnter a number to view description of chosen item or enter 0 to return: ");

			try {
				itemSelected = Integer.parseInt(sc.nextLine());
				if (itemSelected == 1) {
					System.out.println("\n\t\t\t\t" + myWeapon.getWeaponDescription());
					System.out.println("\t\t\t\tWeapon Attack Boost: " + myWeapon.getWeaponAttack());
					System.out.println("\t\t\t\tWeapon Defense Boost: " + myWeapon.getWeaponDefense());
					System.out.println("\t\t\t\tWeapon Speed Boost: " + myWeapon.getWeaponSpeed());
					System.out.println("\t\t\t\tWeapon Magic Boost: " + myWeapon.getWeaponMagic());
				} else {
					BaseConsumable consumable = myInv.get(itemSelected - 2);
					if (consumable == null) {
						System.out.println("\t\t\t\tPlease enter a valid input.");
					} else {
						System.out.println("\t\t\t\t" + consumable.GetDescription());
						System.out.print("\t\t\t\tUse item? (y/n): ");
						String yesno = sc.nextLine();
						if (yesno.toLowerCase().equals("y")) {
							if (consumable.GetHealthBenefit() != 0) {
								System.out.println("\t\t\t\t" + consumable.GetHealthBenefit() + " HP recovered");
								character.currentHP += consumable.GetHealthBenefit();
								if (character.currentHP > character.baseHP) {
									character.currentHP = character.baseHP;
								}
							}
							if (consumable.GetMagicBenefit() != 0) {
								System.out.println("\t\t\t\t" + consumable.GetMagicBenefit() + " MP recovered");
								character.currentMP += consumable.GetMagicBenefit();
								if (character.currentMP > character.baseMP) {
									character.currentMP = character.baseMP;
								}
							}
						}
					}
				}
			} catch (Exception e) {
				System.out.println("\t\t\t\tPlease enter a valid input.");
			}
		}
	}

	public int getHP() {
		return character.currentHP;
	}

	public void setHP(int hp)
	{
		character.currentHP = hp;
	}
	
	public int getMP() {
		return character.currentMP;
	}

	public void setMP(int mp)
	{
		character.currentMP = mp;
	}

	public String toString() {
		return "P";
	}

	public void setX(int r) {

		myX = r;
	}

	public void setY(int c) {

		myY = c;
	}

	public int getX() {

		return myX;
	}

	public int getY() {

		return myY;
	}

	public int getInvenSize() {
		return myInv.size();
	}

	public double getAttackDamage() {
		return character.baseAttack + myWeapon.getWeaponAttack();
	}

	public double getMagicDamage() {
		return character.baseMagic + myWeapon.getWeaponMagic();
	}

	public double getDefense() {
		return character.baseDefense + myWeapon.getWeaponDefense();
	}

	public double getSpeed() {
		return character.baseSpeed + myWeapon.getWeaponSpeed();
	}

	public void setEquipedItem(BaseWeapon equipedItem) {
		myWeapon = equipedItem;
	}

}
