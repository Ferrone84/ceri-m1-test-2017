package fr.univavignon.rodeo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import fr.univavignon.rodeo.api.IAnimal;
import fr.univavignon.rodeo.api.ISpecie;

public class ISpecieTest {

	@Mock
	private ISpecie specie;
	
	private static List<IAnimal> mockedList = IntStream.range(0,5)
			  								  .mapToObj(i -> IAnimalTest.getMock())
			  								  .collect(Collectors.toList());
	
	public static ISpecie getMock() {
		ISpecie specie = mock(ISpecie.class);
		when(specie.getArea()).thenReturn(0);
		when(specie.getAnimals()).thenReturn(mockedList);
		return specie;
	}
	
	@Before
	public void init() {
		specie = getMock();
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