package fr.univavignon.rodeo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import fr.univavignon.rodeo.api.Animal;

public class AnimalTest extends IAnimalTest {
	
	private Animal animal;
	
	@Before
	public void init() {
		super.init();
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
}
