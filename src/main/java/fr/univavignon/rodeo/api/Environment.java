package fr.univavignon.rodeo.api;

import java.util.List;

public class Environment implements IEnvironment {

	private String name;
	private int areaNumber;
	private List<ISpecie> species;
	
	public Environment(String name, int areaNumber, List<ISpecie> species) {
		this.name = name;
		this.areaNumber = areaNumber;
		this.species = species;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getAreas() {
		return areaNumber;
	}

	@Override
	public List<ISpecie> getSpecies() {
		return species;
	}

}
