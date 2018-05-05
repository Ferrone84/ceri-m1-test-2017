package fr.univavignon.rodeo;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import fr.univavignon.rodeo.api.IAnimal;

public class IAnimalTest {
	
	@Mock
	private IAnimal animal;

	public static IAnimal getMock() {
		IAnimal animal = mock(IAnimal.class);
		when(animal.getXP()).thenReturn(0);
		when(animal.isSecret()).thenReturn(true);
		when(animal.isEndangered()).thenReturn(true);
		when(animal.isBoss()).thenReturn(true);
		return animal;
	}
	
	@Test
	public void testGetXp() {
		animal = getMock();
		assertEquals(0, animal.getXP());
	}
	
	@Test
	public void testIsSecret() {
		animal = getMock();
		assertTrue(animal.isSecret());
	}
	
	@Test
	public void testIsEndangered() {
		animal = getMock();
		assertTrue(animal.isEndangered());
	}
	
	@Test
	public void testIsBoss() {
		animal = getMock();
		assertTrue(animal.isBoss());
	}
}