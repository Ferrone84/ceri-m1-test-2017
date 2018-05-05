package fr.univavignon.rodeo.api;

public class Animal extends NamedObject implements IAnimal {
	
	private int xp;
	private boolean secret, endanger, boss;
	
	public Animal(String name, int xp, boolean secret, boolean endanger, boolean boss) {
		super(name);
		this.xp = xp;
		this.secret = secret;
		this.endanger = endanger;
		this.boss = boss;
	}

	@Override
	public int getXP() {
		return xp;
	}

	@Override
	public boolean isSecret() {
		return secret;
	}

	@Override
	public boolean isEndangered() {
		return endanger;
	}

	@Override
	public boolean isBoss() {
		return boss;
	}
	
	@Override
	public boolean equals(Object o) {
		Animal newObject = (Animal) o;
		
		return (name.equals(newObject.getName()) 
				&& xp == newObject.getXP()
				&& secret == newObject.isSecret()
				&& endanger == newObject.isEndangered()
				&& boss == newObject.isBoss());
	}
}
