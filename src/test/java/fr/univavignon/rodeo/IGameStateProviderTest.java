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
	private static IGameState gameState;
	
	public static IGameStateProvider getMock() {
		IGameStateProvider gameStateProvider = mock(IGameStateProvider.class);

		gameState = IGameStateTest.getMock();
		when(gameStateProvider.get("")).thenReturn(null);
		when(gameStateProvider.get("MySave")).thenReturn(gameState);
		doThrow(IllegalArgumentException.class).when(gameStateProvider).get(null);
		return gameStateProvider;
	}

	@Before
	public void init() {
		gameStateProvider = getMock();
	}
	
	@Test
	public void testSave() {
		assertNotNull(gameState);
		gameStateProvider.save(gameState);
	}
	
	@Test
	public void testGet() {
		assertEquals(gameState, gameStateProvider.get("MySave"));
	}
	
	@Test
	public void testGetReturnNull() {
		assertEquals(null, gameStateProvider.get(""));
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testGetIsNull() {
		gameStateProvider.get(null);
	}
}
