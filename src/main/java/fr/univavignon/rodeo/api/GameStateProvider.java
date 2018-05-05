package fr.univavignon.rodeo.api;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

//normalement pour cette classe il faudrait tout save (tous les animaux et la progression totale)
//mais un peu la flemme de tout détailler, l'idée principale est la (je save le % de progression)
public class GameStateProvider implements IGameStateProvider {
	
	private final String SAVEPATH = "resources/save_";

	@Override
	public void save(IGameState gameState) {
		if (gameState == null) {
			System.out.println("Jpeux pas save un truc null");
			return;
		}
		
		String saveFileName = SAVEPATH + gameState.getName() + ".txt";
		try {
			PrintWriter writer = new PrintWriter(saveFileName, "UTF-8");
			writer.print(gameState.getProgression());
			writer.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public IGameState get(String name) throws IllegalArgumentException {
		if (name == null)
			throw new IllegalArgumentException("get(String name) -> null argument");
		
		GameState gameState = null;
		String saveFileName = SAVEPATH + name + ".txt";
		
		try(BufferedReader br = new BufferedReader(new FileReader(saveFileName))) {
			gameState = new GameState(name);
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        sb.append(line);
		        line = br.readLine();
		    }
		    String lines = sb.toString();
		    
		    int progression = Integer.parseInt(lines);
		    gameState.setProgression(progression);
		    
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		return gameState;
	}
	
	public String getSavePath() {
		return this.SAVEPATH;
	}
}
