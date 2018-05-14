package Entities;

import java.util.concurrent.ThreadLocalRandom;

public class Actor extends BaseItem {

	public int myX;
	public int myY;
	public String myName;
	public static int myLevel;

	public Actor(int x, int y, String name, int level) {
		super(x, y);
		myLevel = ThreadLocalRandom.current().nextInt(1, level + 1);
		myName = name;
	}

	public int getLevel() {
		return myLevel;
	}

	public String getName() {
		return myName;
	}

	public Boolean isAc() {
		return false;
	}

}
