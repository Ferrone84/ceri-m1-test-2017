package fr.univavignon.rodeo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

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
		gameStateProvider.save(null);
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
	
	@Test //TODO impossible à throw ?
	public void testSaveFileNotFoundException() {
		assertNotNull(gameState);
		gameStateProvider.save(gameState);
	}
	
	@Test //TODO impossible à throw ?
	public void testSaveEncodingException() {
		assertNotNull(gameState);
		gameStateProvider.save(gameState);
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
	
	@Test
	public void testGetIOException() throws IOException {
		GameState io = new GameState("ioexception");
		assertNotNull(io);
		gameStateProvider.save(io);
		final RandomAccessFile raFile = new RandomAccessFile("resources/save_ioexception.txt", "rw");
		raFile.getChannel().lock();
		assertEquals(io, gameStateProvider.get("ioexception"));
	}
}
