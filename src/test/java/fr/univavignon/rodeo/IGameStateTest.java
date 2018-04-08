package fr.univavignon.rodeo;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import fr.univavignon.rodeo.api.IAnimal;
import fr.univavignon.rodeo.api.IGameState;
import fr.univavignon.rodeo.api.ISpecie;
import fr.univavignon.rodeo.api.SpecieLevel;

public class IGameStateTest {

	@Mock
	private IGameState gameState;
	
	@Mock
	private ISpecie specie;
	
	@Mock
	private IAnimal animal;
	
	private SpecieLevel noviceLevel, wranglerLevel, championLevel, masterLevel;

	@Before
	public void init() {
		noviceLevel = SpecieLevel.NOVICE;
		wranglerLevel = SpecieLevel.WRANGLER;
		championLevel = SpecieLevel.CHAMPION;
		masterLevel = SpecieLevel.MASTER;
		
		gameState = mock(IGameState.class);
		specie = mock(ISpecie.class);
		animal = mock(IAnimal.class);
		
		when(gameState.getProgression()).thenReturn(0);
	}
	
	@Test
	public void testExploreArea() {
		//TODO faire ça
	}
	
	@Test
	public void testCatchAnimal() {
		//TODO faire ça
	}
	
	@Test
	public void testNoviceLevel() {
		when(gameState.getSpecieLevel(specie)).thenReturn(noviceLevel);
		assertEquals(noviceLevel, gameState.getSpecieLevel(specie));
	}
	
	@Test
	public void testWranglerLevel() {
		when(gameState.getSpecieLevel(specie)).thenReturn(wranglerLevel);
		assertEquals(wranglerLevel, gameState.getSpecieLevel(specie));
	}
	
	@Test
	public void testChampionLevel() {
		when(gameState.getSpecieLevel(specie)).thenReturn(championLevel);
		assertEquals(championLevel, gameState.getSpecieLevel(specie));
	}
	
	@Test
	public void testMasterLevel() {
		when(gameState.getSpecieLevel(specie)).thenReturn(masterLevel);
		assertEquals(masterLevel, gameState.getSpecieLevel(specie));
	}
	
	@Test
	public void testGetProgression() {
		assertEquals(0, gameState.getProgression());
	}
}
