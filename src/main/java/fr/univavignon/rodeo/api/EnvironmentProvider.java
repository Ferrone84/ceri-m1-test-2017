package fr.univavignon.rodeo.api;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class EnvironmentProvider implements IEnvironmentProvider {

	//Bon la j'avais le choix entre web crawler ou truc moche mais vite fait ... xDD
	private List<IEnvironment> environments = Arrays.asList(
			new Environment("Savannah", 1, Arrays.asList(
				new Specie("Buffalo", 1, Arrays.asList(
					new Animal("Forest Buffalo", 1, false, false, false), 		//normal
					new Animal("Diabuffalo", 3, true, false, false), 			//secret
					new Animal("Buff the Magic Dragon", 30, false, true, false) //endangered
				)),
				new Specie("Zebras", 1, Arrays.asList(
					new Animal("Zebra", 0, false, false, false),				//normal
					new Animal("Trojan Zebra", 25, true, false, false),			//secret
					new Animal("Zegasus", 30, false, true, false)				//endangered
				)),
				new Specie("Elephants", 1, Arrays.asList(
					new Animal("African Elephant", 0, false, false, false), 	//normal
					new Animal("Skelephant", 30, false, false, true) 			//boss
				)),
				new Specie("Ostriches", 2, Arrays.asList(
					new Animal("Ostrich", 0, false, false, false),				//normal
					new Animal("Bosstrich", 30, false, false, true)				//boss
				)),
				new Specie("Giraffes", 3, Arrays.asList(
					new Animal("Giraffe", 0, false, false, false),				//normal
					new Animal("UFG", 30, false, false, true)					//boss
				)),
				new Specie("Vultures", 4, Arrays.asList(
					new Animal("Egyptian Vulture", 1, false, false, false), 	//normal
					new Animal("Vulture-on", 5, true, false, false), 			//secret
					new Animal("Volture", 30, false, true, false) 				//endangered
				)),
				new Specie("Lions", 5, Arrays.asList(
					new Animal("Dande Lion", 1, false, false, false), 			//normal
					new Animal("Le'Ion Chef", 5, true, false, false), 			//secret
					new Animal("Nian Monster ", 30, false, true, false) 		//endangered
				))
			))
			/*,
			new Environment("Jungle", 2, Arrays.asList(
				"Boar",
				"Gorrila",
				"Alligator",
				"Hippo",
				"Rhino",
				"Tiger",
				"Toucan"
			)),
			new Environment("Mountains", 3, Arrays.asList(
				"Goat",
				"Llama",
				"Bear",
				"Wolf",
				"Yak",
				"Eagle",
				"Moose"
			)),
			new Environment("Outback", 4, Arrays.asList(
				"Sheep",
				"Kangaroos",
				"Drop Bears",
				"Wombats",
				"Emus",
				"Camels",
				"Flying Foxes"
			)),			
			new Environment("Tundra", 5, Arrays.asList(
				"Penguins",
				"Seals",
				"Walruses",
				"Rabbits",
				"Owls",
				"Foxes",
				"Polar Bears"
			))*/
	);
	
	private Map<String, Boolean> availableEnvironments;
	
	public EnvironmentProvider() {
		availableEnvironments = new HashMap<String, Boolean>();
		int count = 0;
		for (IEnvironment env : environments) {
			boolean available = false;
			if (count++ == 0)
				available = true;
			availableEnvironments.put(env.getName(), available);
		}
	}
	
	@Override
	public List<String> getAvailableEnvironments() {
		Vector<String> result = new Vector<String>();
		for (Map.Entry<String, Boolean> entry : availableEnvironments.entrySet()) {
			if (entry.getValue())
				result.add(entry.getKey());
		}
		
		return result;
	}

	@Override
	public IEnvironment getEnvironment(String name) throws IllegalArgumentException {
		if (name == null)
			throw new IllegalArgumentException("getEnvironment(String name) -> null argument");
		
		for (IEnvironment env : environments) {
			if (env.getName().equals(name))
				return env;
		}
		
		return null;
	}
	
	public List<IEnvironment> getEnvironments() {
		return this.environments;
	}
	
	public boolean stillAreaToExplore() {
		if (this.getAvailableEnvironments().size() == environments.size()) {
			System.out.println("Tout est déjà unlock.");
			return false;
		}
		return true;
	}
	
	public void unlockNextEnvironment() {
		for (Map.Entry<String, Boolean> entry : availableEnvironments.entrySet()) {
			if (!entry.getValue()) {
				entry.setValue(true);
				break;
			}
		}
	}
}
