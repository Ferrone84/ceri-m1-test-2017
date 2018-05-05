package fr.univavignon.rodeo.api;

import java.util.List;

public class Specie extends NamedObject implements ISpecie {

	private int area;
	private List<IAnimal> animals;
	
	public Specie(String name, int area, List<IAnimal> animals) {
		super(name);
		this.area = area;
		this.animals = animals;
	}

	@Override
	public int getArea() {
		return area;
	}

	@Override
	public List<IAnimal> getAnimals() {
		return animals;
	}
	
	@Override
	public boolean equals(Object o) {
		Specie newObject = (Specie) o;
		List<IAnimal> animalsObject = newObject.getAnimals();
		boolean result = true;
		
		if (animals.size() == animalsObject.size() && name.equals(newObject.getName()) && area == newObject.getArea()) {
			for (int i=0; i < animals.size(); i++) {
				if (((Animal)animals.get(i)).equals(animalsObject.get(i))) {
					result = false;
				}
			}
		}
		
		return result;
	}
}
