package Consumables;

public abstract class BaseConsumable {
	
	protected int healthBenefit;
	protected int magicBenefit;
	protected String consumableDescription;
	protected String consumableName;
	
	public int GetHealthBenefit() {
		return healthBenefit;
	};
	
	public int GetMagicBenefit() {
		return magicBenefit;
	};
	
	public String GetDescription() {
		return consumableDescription;
	}
	
	public String GetConsumableName() {
		return consumableName;
	}
}
