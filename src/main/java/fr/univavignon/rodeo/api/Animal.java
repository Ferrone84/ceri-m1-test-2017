package fr.univavignon.rodeo.api;

public class Animal implements IAnimal {

	private String name;
	private int xp;
	private boolean secret, endanger, boss;
	
	public Animal(String name, int xp, boolean secret, boolean endanger, boolean boss) {
		this.name = name;
		this.xp = xp;
		this.secret = secret;
		this.endanger = endanger;
		this.boss = boss;
	}
	
	@Override
	public String getName() {
		return name;
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

}
