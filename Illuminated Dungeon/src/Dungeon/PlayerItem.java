package Dungeon;


public class PlayerItem {
	private String description;
	private int attack;
	private int defense;
	private String name;
	
	public PlayerItem()
	{
		makeInfo();
	}
	
	private void makeInfo() {
		// random attack and defense		
		int y = (int) ((Math.random() * 3));
		if (y == 0) {
			setName("Sword");
			attack = (int) ((Math.random() * 10)) + 1;
			defense = 0;
		} else if (y == 1) {
			setName("Bow");
			attack = (int) ((Math.random() * 8)) + 1;
			defense = 0;
		} else {
			setName("Shield");
			attack = (int) ((Math.random() * 3)) + 1;
			defense = (int) ((Math.random() * 7)) + 1;
		}
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}	
	
	public String toString() {
		return name + " " + "attack: " + this.getAttack() + " defense: " + this.getDefense() ;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
