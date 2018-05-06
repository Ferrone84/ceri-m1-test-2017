package fr.univavignon.rodeo.api;

import java.util.List;

public class Environment extends NamedObject implements IEnvironment {

	private int areaNumber;
	private List<ISpecie> species;
	
	public Environment(String name, int areaNumber, List<ISpecie> species) {
		super(name);
		this.areaNumber = areaNumber;
		this.species = species;
	}

	@Override
	public int getAreas() {
		return areaNumber;
	}

	@Override
	public List<ISpecie> getSpecies() {
		return species;
	}

	@Override
	public boolean equals(Object o) {
		Environment newObject = (Environment) o;
		List<ISpecie> speciesObject = newObject.getSpecies();
		
		if (name.equals(newObject.getName()) && areaNumber == newObject.getAreas() && species.size() == speciesObject.size()) {
			for (int i=0; i < species.size(); i++) {
				if (!((Specie)species.get(i)).equals(speciesObject.get(i)))
					return false;
			}
		}
		else {
			return false;
		}
		
		return true;
	}
}
