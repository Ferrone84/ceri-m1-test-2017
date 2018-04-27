package fr.univavignon.rodeo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import fr.univavignon.rodeo.api.IAnimal;
import fr.univavignon.rodeo.api.ISpecie;

public class ISpecieTest {

	@Mock
	private IAnimal animal;

	@Mock
	private ISpecie specie;
	
	private LinkedList<IAnimal> mockedList;
	
	@Before
	public void init() {		
		animal = mock(IAnimal.class);
		specie = mock(ISpecie.class);

		mockedList = new LinkedList<IAnimal>();
		mockedList.add(animal);
		
		when(specie.getArea()).thenReturn(0);
		when(specie.getAnimals()).thenReturn(mockedList);
	}
	
	@Test
	public void testGetArea() {
		assertEquals(0, specie.getArea());
	}
	
	@Test
	public void testGetAnimals() {
		assertEquals(mockedList, specie.getAnimals());
	}
}