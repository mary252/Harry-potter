package harrypotter.model.magic;

public abstract class Spell {

	private String name;
	private int cost;
	private int defaultCooldown;
	private int coolDown;

	public Spell(String name, int cost, int defaultCoolDown) {

		this.name = name;
		this.cost = cost;
		this.defaultCooldown = defaultCoolDown;

	}

	public String getName() {
		return name;
	}

	public int getCost() {
		return cost;
	}

	public int getCoolDown() {
		return coolDown;
	}

	public void setCoolDown(int cooldown) {
		this.coolDown = cooldown;
	}

	public int getDefaultCooldown() {
		return defaultCooldown;
	}
	
	public String toString(){
		String s="Name: "+name+'\n'+
			     " Cost: "+cost+'\n'+
				 " Cooldown: "+defaultCooldown+'\n';
		if(coolDown!=0){
			s=s+" Remaining turns: "+coolDown+'\n';
		}
		if(this instanceof DamagingSpell){
			s=s+" Damage amount: "+((DamagingSpell)this).getDamageAmount()+'\n';
		}
		if(this instanceof HealingSpell){
			s=s+" Healing amount: "+((HealingSpell)this).getHealingAmount()+'\n';
		}
		if(this instanceof RelocatingSpell){
			s=s+" Relocation range: "+((RelocatingSpell)this).getRange()+'\n';
		}
		return s;
	}

}
