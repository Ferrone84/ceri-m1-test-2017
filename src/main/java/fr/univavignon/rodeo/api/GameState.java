package fr.univavignon.rodeo.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameState implements IGameState {
	
	private String name;
	private int progression;
	private EnvironmentProvider envProvider;
	private Map<IAnimal, Boolean> animalsCaught;
	private IEnvironment currentEnvironment;
	
	public GameState(String name) {
		this.name = name;
		progression = 0;
		envProvider = new EnvironmentProvider();
		animalsCaught = new HashMap<IAnimal, Boolean>();
		currentEnvironment = envProvider.getEnvironment("Savannah");
		
		for (IEnvironment env : envProvider.getEnvironments()) {
			for (ISpecie specie : env.getSpecies()) {
				for (IAnimal animal : specie.getAnimals()) {
					animalsCaught.put(animal, false);
				}
			}
		}
	}
	
	@Override
	public String getName() {
		return name;
	}

	//Dans le jeu on a pas besoin d'avoir tout catch pour passer à la suivante :/
	@Override
	public void exploreArea() throws IllegalStateException {
		boolean allAnimalsOfCurrentAreaCaught = true;
		for (ISpecie specie : currentEnvironment.getSpecies()) {
			for (IAnimal animal : specie.getAnimals()) {
				if (!animalsCaught.get(animal)) {
					allAnimalsOfCurrentAreaCaught = false;
				}
			}
		}
		
		if (allAnimalsOfCurrentAreaCaught && envProvider.stillAreaToExplore()) {
			envProvider.unlockNextEnvironment();
			List<IEnvironment> environments = envProvider.getEnvironments();
			
			for (int i=0; i < environments.size(); i++) {
				if (environments.get(i).getName().equals(currentEnvironment.getName())) {
					currentEnvironment = environments.get(i+1);
				}
			}
		}
		else {
			throw new IllegalStateException("exploreArea() -> tous les animaux n'ont pas été attrapés ou il ne reste plus de nouvelle zone");
		}
	}

	@Override
	public void catchAnimal(IAnimal animal) throws IllegalArgumentException, IllegalStateException {
		if (animal == null) {
			throw new IllegalArgumentException("catchAnimal(IAnimal animal) -> null argument");
		}
		
		IAnimal animalFound = null;
		for (Map.Entry<IAnimal, Boolean> entry : animalsCaught.entrySet()) {
			if (entry.getKey().getName().equals(animal.getName())) {
				animalFound = entry.getKey();
			}
		}
		if (animalFound == null)  {
			throw new IllegalStateException("catchAnimal(IAnimal animal) -> argument can not be found");
		}
		
		animalsCaught.put(animalFound, true);
	}

	@Override
	public SpecieLevel getSpecieLevel(ISpecie specie) throws IllegalArgumentException {
		return null;
	}
	
	public void computeProgression() {
		double animalsNumber = 0;
		
		for (Map.Entry<IAnimal, Boolean> entry : animalsCaught.entrySet()) {
			if (entry.getValue())
				animalsNumber++;
		}
		
		this.progression = (int) Math.round((animalsNumber / animalsCaught.size())*100);
	}

	@Override
	public int getProgression() {		
		return this.progression;
	}

	public void setProgression(int progression) {
		this.progression = progression;
	}
}
