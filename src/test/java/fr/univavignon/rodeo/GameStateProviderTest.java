package fr.univavignon.rodeo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import fr.univavignon.rodeo.api.Animal;
import fr.univavignon.rodeo.api.GameState;
import fr.univavignon.rodeo.api.GameStateProvider;

public class GameStateProviderTest extends IGameStateProviderTest {
	private GameStateProvider gameStateProvider;
	private GameState gameState;

	@Before
	public void init() {
		gameStateProvider = new GameStateProvider();
		gameState = new GameState("unitTests");
	}
	
	@Test
	public void testSave() {
		assertNotNull(gameState);
		gameStateProvider.save(gameState);
	}
	
	@Test
	public void testSaveWithoutProgression() {
		assertNotNull(gameState);
		gameStateProvider.save(gameState);
		String saveFileName = gameStateProvider.getSavePath() + gameState.getName() + ".txt";
		int progression = gameState.getProgression();
		
		try(BufferedReader br = new BufferedReader(new FileReader(saveFileName))) {
			gameState = new GameState(gameState.getName());
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        sb.append(line);
		        line = br.readLine();
		    }
		    String lines = sb.toString();
			
			assertEquals(progression, Integer.parseInt(lines));
		    
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSaveWithProgression() {
		assertNotNull(gameState);
		gameState.catchAnimal(new Animal("Buff the Magic Dragon", 30, false, true, false));
		gameStateProvider.save(gameState);
		String saveFileName = gameStateProvider.getSavePath() + gameState.getName() + ".txt";
		int progression = gameState.getProgression();
		
		try(BufferedReader br = new BufferedReader(new FileReader(saveFileName))) {
			gameState = new GameState(gameState.getName());
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        sb.append(line);
		        line = br.readLine();
		    }
		    String lines = sb.toString();
			
			assertEquals(progression, Integer.parseInt(lines));
		    
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGet() {
		gameStateProvider.save(gameState);
		assertEquals(gameState, gameStateProvider.get("unitTests"));
	}
	
	@Test
	public void testGetReturnNull() {
		assertEquals(null, gameStateProvider.get("fileThatDoesntExist"));
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testGetIsNull() {
		gameStateProvider.get(null);
	}
}
