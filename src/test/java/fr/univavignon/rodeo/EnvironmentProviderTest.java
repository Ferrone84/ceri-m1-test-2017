package fr.univavignon.rodeo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fr.univavignon.rodeo.api.Animal;
import fr.univavignon.rodeo.api.Environment;
import fr.univavignon.rodeo.api.EnvironmentProvider;
import fr.univavignon.rodeo.api.IEnvironment;
import fr.univavignon.rodeo.api.Specie;

public class EnvironmentProviderTest extends IEnvironmentProviderTest {
	
	private EnvironmentProvider environmentProvider;
	private List<IEnvironment> environments;
	
	@Before
	public void init() {
		environments = Arrays.asList(
			new Environment("Savannah", 1, Arrays.asList(
				new Specie("Buffalo", 1, Arrays.asList(
					new Animal("Forest Buffalo", 1, false, false, false), 		//normal
					new Animal("Diabuffalo", 3, true, false, false), 			//secret
					new Animal("Buff the Magic Dragon", 30, false, true, false) //endangered
				)),
				new Specie("Zebras", 1, Arrays.asList(
					new Animal("Zebra", 0, false, false, false),				//normal
					new Animal("Trojan Zebra", 25, true, false, false),			//secret
					new Animal("Zegasus", 30, false, true, false)				//endangered
				)),
				new Specie("Elephants", 1, Arrays.asList(
					new Animal("African Elephant", 0, false, false, false), 	//normal
					new Animal("Skelephant", 30, false, false, true) 			//boss
				)),
				new Specie("Ostriches", 2, Arrays.asList(
					new Animal("Ostrich", 0, false, false, false),				//normal
					new Animal("Bosstrich", 30, false, false, true)				//boss
				)),
				new Specie("Giraffes", 3, Arrays.asList(
					new Animal("Giraffe", 0, false, false, false),				//normal
					new Animal("UFG", 30, false, false, true)					//boss
				)),
				new Specie("Vultures", 4, Arrays.asList(
					new Animal("Egyptian Vulture", 1, false, false, false), 	//normal
					new Animal("Vulture-on", 5, true, false, false), 			//secret
					new Animal("Volture", 30, false, true, false) 				//endangered
				)),
				new Specie("Lions", 5, Arrays.asList(
					new Animal("Dande Lion", 1, false, false, false), 			//normal
					new Animal("Le'Ion Chef", 5, true, false, false), 			//secret
					new Animal("Nian Monster ", 30, false, true, false) 		//endangered
				))
			)),
			new Environment("Jungle", 2, Arrays.asList( //pour tester
				new Specie("Boar", 1, Arrays.asList(
					new Animal("Boar1", 1, false, false, false), 		//normal
					new Animal("Boar2", 3, true, false, false), 		//secret
					new Animal("Boar3", 30, false, true, false) 		//endangered
				))
			))
		);
		environmentProvider = new EnvironmentProvider();
	}
	
	@Test
	public void testGetAvailableEnvironments() {
		assertEquals(Arrays.asList("Savannah"), environmentProvider.getAvailableEnvironments());
	}
	
	@Test
	public void testGetEnvironments() {
		assertEquals(environments, environmentProvider.getEnvironments());
		List<IEnvironment> env = Arrays.asList(
			new Environment("Jungle", 2, Arrays.asList(
				new Specie("Boar", 1, Arrays.asList(
						new Animal("Boar1", 1, false, false, false),
						new Animal("Boar2", 3, true, false, false),
						new Animal("Boar3", 30, false, true, false)
				))
			))
		);
		assertNotEquals(env, environmentProvider.getEnvironments());
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testGetEnvironmentIsNull() {
		environmentProvider.getEnvironment(null);
	}
	
	@Test
	public void testGetEnvironment() {
		assertEquals(environments.get(0), environmentProvider.getEnvironment("Savannah"));
	}
	
	@Test
	public void testGetEnvironmentNull() {
		assertEquals(null, environmentProvider.getEnvironment("lel"));
	}
	
	@Test
	public void testStillAreaToExplore() {
		assertTrue(environmentProvider.stillAreaToExplore());
		environmentProvider.unlockNextEnvironment();
		assertFalse(environmentProvider.stillAreaToExplore());
		assertEquals(Arrays.asList("Savannah", "Jungle"), environmentProvider.getAvailableEnvironments());
	}
}
