
public abstract class BaseItem {

	private int myX;
	private int myY;
	private Boolean isAc;
	
	public BaseItem(int x, int y)
	{
		myX = x;
		myY = y;
	}
	
	public int getX()
	{
		return myX;
	}
	
	public int getY()
	{
		return myY;
	}
	
	abstract Boolean isAc();
}
