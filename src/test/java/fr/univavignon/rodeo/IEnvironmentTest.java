package fr.univavignon.rodeo;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import fr.univavignon.rodeo.api.IEnvironment;
import fr.univavignon.rodeo.api.ISpecie;

public class IEnvironmentTest {
	
	@Mock
	public IEnvironment environment;
	
	public static List<ISpecie> species = IntStream.range(0,5)
										  .mapToObj(i -> ISpecieTest.getMock())
										  .collect(Collectors.toList());
	
	public static IEnvironment getMock() {
		IEnvironment environment = mock(IEnvironment.class);
		when(environment.getAreas()).thenReturn(0);
		when(environment.getSpecies()).thenReturn(species);
		return environment;
	}
	
	@Before
	public void init() {
		environment = getMock();
	}
	
	@Test
	public void testGetAreas() {
		assertEquals(0, environment.getAreas());
	}
	
	@Test
	public void testGetSpecies() {
		assertEquals(species, environment.getSpecies());
	}
}