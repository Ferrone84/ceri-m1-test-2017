package fr.univavignon.rodeo;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import fr.univavignon.rodeo.api.IGameState;
import fr.univavignon.rodeo.api.IGameStateProvider;

public class IGameStateProviderTest {

	@Mock
	private IGameStateProvider gameStateProvider;
	
	@Mock
	private IGameState gameState;

	@Before
	public void init() {
		gameStateProvider = mock(IGameStateProvider.class);
		gameState = mock(IGameState.class);
		when(gameStateProvider.get("")).thenReturn(null);
		when(gameStateProvider.get("MySave")).thenReturn(gameState);
	}
	
	@Test
	public void testSave() {
		//TODO comment tester ce truc ?
		gameStateProvider.save(gameState);
	}
	
	@Test
	public void testGet() {
		assertEquals(null, gameStateProvider.get(""));
		assertEquals(gameState, gameStateProvider.get("MySave"));
	}
}
