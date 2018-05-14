package Weapons;

public abstract class BaseWeapon {
	
	protected double weaponAttack;
	protected double weaponDefense;
	protected double weaponSpeed;
	protected double weaponMagic;
	protected String weaponDescription;
	protected String weaponName;
	
	public double getWeaponAttack() {
		return weaponAttack;
	}
	
	public double getWeaponDefense() {
		return weaponDefense;
	}
	
	public double getWeaponSpeed() {
		return weaponSpeed;
	}
	
	public double getWeaponMagic() {
		return weaponMagic;
	}
	
	public String getWeaponDescription() {
		return weaponDescription;
	}
	
	public String getWeaponName() {
		return weaponName;
	}
			
}
