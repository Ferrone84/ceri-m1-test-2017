package fr.univavignon.rodeo;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fr.univavignon.rodeo.api.Animal;
import fr.univavignon.rodeo.api.IAnimal;
import fr.univavignon.rodeo.api.Specie;

public class SpecieTest extends ISpecieTest {
	
	private Specie specie;
	private List<IAnimal> animals;
	
	@Before
	public void init() {
		animals = Arrays.asList(
				new Animal("Forest Buffalo", 1, false, false, false),
				new Animal("Diabuffalo", 3, true, false, false),
				new Animal("Buff the Magic Dragon", 30, false, true, false)
		);
		specie = new Specie("Buffalo", 1, animals);
	}
	
	@Test
	public void testGetName() {
		assertEquals("Buffalo", specie.getName());
	}
	
	@Test
	public void testGetArea() {
		assertEquals(1, specie.getArea());
	}
	
	@Test
	public void testGetAnimals() {
		assertEquals(animals, specie.getAnimals());
	}
}
