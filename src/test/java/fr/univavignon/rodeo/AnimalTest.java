package fr.univavignon.rodeo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import fr.univavignon.rodeo.api.Animal;

public class AnimalTest extends IAnimalTest {
	
	private Animal animal;
	
	@Before
	public void init() {
		animal = new Animal("Test", 0, true, true, true);
	}
	
	@Test
	public void testName() {
		assertEquals("Test", animal.getName());
	}
	
	@Test
	public void testGetXp() {
		assertEquals(0, animal.getXP());
	}
	
	@Test
	public void testIsSecret() {
		assertTrue(animal.isSecret());
	}
	
	@Test
	public void testIsEndangered() {
		assertTrue(animal.isEndangered());
	}
	
	@Test
	public void testIsBoss() {
		assertTrue(animal.isBoss());
	}
	
	@Test
	public void testEquals() {
		Animal animal1 = new Animal("animal", 0, false, false, false);
		Animal animal2 = new Animal("animal", 0, false, false, false);
		assertEquals(animal1, animal2);
	}
	
	@Test
	public void testNotEquals() {
		Animal animal1 = new Animal("animal1", 0, false, false, false);
		Animal animal2 = new Animal("animal2", 1, true, true, true);
		assertNotEquals(animal1, animal2);
	}
	
	@Test
	public void testNotEquals2() {
		Animal animal1 = new Animal("animal", 0, false, false, false);
		Animal animal2 = new Animal("animal", 1, true, true, true);
		assertNotEquals(animal1, animal2);
	}
	
	@Test
	public void testNotEquals3() {
		Animal animal1 = new Animal("animal", 0, false, false, false);
		Animal animal2 = new Animal("animal", 0, false, true, true);
		assertNotEquals(animal1, animal2);
	}
	
	@Test
	public void testNotEquals4() {
		Animal animal1 = new Animal("animal", 0, false, false, false);
		Animal animal2 = new Animal("animal", 0, false, false, true);
		assertNotEquals(animal1, animal2);
	}
}
