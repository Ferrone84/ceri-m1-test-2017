package fr.univavignon.rodeo.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameState extends NamedObject implements IGameState {
	
	private int progression;
	private EnvironmentProvider envProvider;
	private Map<ISpecie, Integer> speciesXp;
	private Map<IAnimal, Boolean> animalsCaught;
	private IEnvironment currentEnvironment;
	
	public GameState(String name) {
		super(name);
		progression = 0;
		envProvider = new EnvironmentProvider();
		speciesXp = new HashMap<ISpecie, Integer>();
		animalsCaught = new HashMap<IAnimal, Boolean>();
		currentEnvironment = envProvider.getEnvironment("Savannah");
		
		for (IEnvironment env : envProvider.getEnvironments()) {
			for (ISpecie specie : env.getSpecies()) {
				speciesXp.put(specie, 0);
				for (IAnimal animal : specie.getAnimals()) {
					animalsCaught.put(animal, false);
				}
			}
		}
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
		
		if (envProvider.stillAreaToExplore() && allAnimalsOfCurrentAreaCaught) {
			envProvider.unlockNextEnvironment();
			List<IEnvironment> environments = envProvider.getEnvironments();
			
			for (int i=0; i < environments.size()-1; i++) {
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
			if (entry.getKey().equals(animal)) {
				animalFound = entry.getKey();
				break;
			}
		}
		if (animalFound == null)  {
			throw new IllegalStateException("catchAnimal(IAnimal animal) -> this animal doesn't exists");
		}
		
		animalsCaught.put(animalFound, true);
		
		ISpecie specie = getSpecie(animalFound);
		/*if (specie == null)  {
			throw new IllegalStateException("catchAnimal(IAnimal animal) -> intern error (an animal must belong to a specie)");
		}*/
		Integer currentXp = speciesXp.get(specie);
		
		speciesXp.put(specie, currentXp + animalFound.getXP());
	}

	@Override
	public SpecieLevel getSpecieLevel(ISpecie specie) throws IllegalArgumentException {
		if (specie == null)
			throw new IllegalArgumentException("getSpecieLevel(ISpecie specie) -> null argument");
		
		Integer xp = speciesXp.get(specie);
		SpecieLevel result = SpecieLevel.NOVICE;
		
		if (xp >= SpecieLevel.WRANGLER.getRequiredXP() && xp < SpecieLevel.CHAMPION.getRequiredXP())
			result = SpecieLevel.WRANGLER;
		else if (xp >= SpecieLevel.CHAMPION.getRequiredXP() && xp < SpecieLevel.MASTER.getRequiredXP())
			result = SpecieLevel.CHAMPION;
		else if (xp != SpecieLevel.NOVICE.getRequiredXP())
			result = SpecieLevel.MASTER;
		
		return result;
	}
	
	private void computeProgression() {
		double animalsNumber = getAnimalsCaughtNumber();
		this.progression = (int) Math.round((animalsNumber / animalsCaught.size())*100);
	}

	@Override
	public int getProgression() {
		computeProgression();
		return this.progression;
	}

	public void setProgression(int progression) {
		this.progression = progression;
	}
	
	private ISpecie getSpecie(IAnimal animal) {
		ISpecie result = null;
		for (Map.Entry<ISpecie, Integer> entry : speciesXp.entrySet()) {
			for (IAnimal currentAnimal : entry.getKey().getAnimals()) {
				if (currentAnimal.equals(animal)) {
					result = entry.getKey();
					break;
				}
			}
		}
		return result;
	}
	
	public int getAnimalsCaughtNumber() {
		int animalsNumber = 0;
		for (Map.Entry<IAnimal, Boolean> entry : animalsCaught.entrySet()) {
			if (entry.getValue())
				animalsNumber++;
		}
		return animalsNumber;
	}

	public EnvironmentProvider getEnvironmentProvider() {
		return envProvider;
	}

	public IEnvironment getCurrentEnvironment() {
		return currentEnvironment;
	}
	
	@Override
	public boolean equals(Object o) {
		GameState newObject = (GameState) o;
		
		return (progression == newObject.getProgression());
	}
}
