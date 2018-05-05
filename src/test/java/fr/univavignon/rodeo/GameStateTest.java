package fr.univavignon.rodeo;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import fr.univavignon.rodeo.api.Animal;
import fr.univavignon.rodeo.api.EnvironmentProvider;
import fr.univavignon.rodeo.api.GameState;
import fr.univavignon.rodeo.api.IAnimal;
import fr.univavignon.rodeo.api.IEnvironment;
import fr.univavignon.rodeo.api.ISpecie;
import fr.univavignon.rodeo.api.SpecieLevel;

public class GameStateTest extends IGameStateTest {
	
	private GameState gameState;
	private IAnimal animal;
	private ISpecie specie;
	
	@Before
	public void init() {
		gameState = new GameState("TestGameState");
		EnvironmentProvider environmentProvider = gameState.getEnvironmentProvider();
		animal = new Animal("Buff the Magic Dragon", 30, false, true, false);
		specie = environmentProvider.getEnvironments().get(0).getSpecies().get(0); //Buffalo
	}
	
	@Test
	public void testExploreArea() {
		IEnvironment env = gameState.getCurrentEnvironment();
		int environmentAnimalsNumber = 0;
		for (ISpecie spe : env.getSpecies()) {
			for (IAnimal ani : spe.getAnimals()) {
				environmentAnimalsNumber++;
				gameState.catchAnimal(ani);
			}
		}
		assertEquals(environmentAnimalsNumber, gameState.getAnimalsCaughtNumber());
		gameState.exploreArea();
	}
	
	@Test (expected=IllegalStateException.class)
	public void testExploreAreaException() {
		gameState.exploreArea();
	}
	
	@Test
	public void testCatchAnimal() {
		assertEquals(0, gameState.getAnimalsCaughtNumber());
		gameState.catchAnimal(new Animal("Forest Buffalo", 1, false, false, false));
		assertEquals(1, gameState.getAnimalsCaughtNumber());
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testCatchAnimalIsNull() {
		gameState.catchAnimal(null);
	}
	
	@Test (expected=IllegalStateException.class)
	public void testCatchAnimalNotExist() {
		gameState.catchAnimal(new Animal("animal qui n'existe pas", 1, false, false, false));
	}
	
	@Test
	public void testGetSpecieLevel() {
		assertEquals(SpecieLevel.NOVICE, gameState.getSpecieLevel(specie));
		gameState.catchAnimal(animal);
		assertEquals(SpecieLevel.WRANGLER, gameState.getSpecieLevel(specie));
		for (int i=0; i < 4; i++)
			gameState.catchAnimal(animal);
		assertEquals(SpecieLevel.CHAMPION, gameState.getSpecieLevel(specie));
		for (int i=0; i < 15; i++)
			gameState.catchAnimal(animal);
		assertEquals(SpecieLevel.MASTER, gameState.getSpecieLevel(specie));
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testGetSpecieLevelIsNull() {
		gameState.getSpecieLevel(null);
	}
	
	@Test
	public void testGetSpecieNoviceLevel() {
		assertEquals(SpecieLevel.NOVICE, gameState.getSpecieLevel(specie));
	}
	
	@Test
	public void testGetSpecieWranglerLevel() {
		gameState.catchAnimal(animal);
		assertEquals(SpecieLevel.WRANGLER, gameState.getSpecieLevel(specie));
	}
	
	@Test
	public void testGetSpecieChampionLevel() {
		for (int i=0; i < 5; i++)
			gameState.catchAnimal(animal);
		assertEquals(SpecieLevel.CHAMPION, gameState.getSpecieLevel(specie));
	}
	
	@Test
	public void testGetSpecieMasterLevel() {
		for (int i=0; i < 20; i++)
			gameState.catchAnimal(animal);
		assertEquals(SpecieLevel.MASTER, gameState.getSpecieLevel(specie));
	}
	
	@Test
	public void testGetProgression() {
		assertEquals(0, gameState.getProgression());
		gameState.catchAnimal(animal);
		assertEquals(5, gameState.getProgression());
	}
}
