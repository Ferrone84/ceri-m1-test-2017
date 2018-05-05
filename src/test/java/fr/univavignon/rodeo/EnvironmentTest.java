package fr.univavignon.rodeo;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fr.univavignon.rodeo.api.Animal;
import fr.univavignon.rodeo.api.Environment;
import fr.univavignon.rodeo.api.ISpecie;
import fr.univavignon.rodeo.api.Specie;

public class EnvironmentTest extends IEnvironmentTest {
	
	private Environment environment;
	private List<ISpecie> species;
	
	@Before
	public void init() {
		species = Arrays.asList(
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
				);
		environment = new Environment("Savannah", 1, species);
	}
	
	@Test
	public void testGetAreas() {
		assertEquals(1, environment.getAreas());
	}
	
	@Test
	public void testGetSpecies() {
		assertEquals(species, environment.getSpecies());
	}
}
