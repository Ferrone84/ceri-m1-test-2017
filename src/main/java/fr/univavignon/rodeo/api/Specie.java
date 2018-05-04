package fr.univavignon.rodeo.api;

import java.util.List;

public class Specie implements ISpecie {

	private String name;
	private int area;
	private List<IAnimal> animals;
	
	public Specie(String name, int area, List<IAnimal> animals) {
		this.name = name;
		this.area = area;
		this.animals = animals;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getArea() {
		return area;
	}

	@Override
	public List<IAnimal> getAnimals() {
		return animals;
	}

}
