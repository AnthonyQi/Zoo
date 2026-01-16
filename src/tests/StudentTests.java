package tests;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import zoo.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class StudentTests {
	
	//Testing Habitat class
	@Test
	public void testingHabitat() {
		String[] test1 = {"Desert", "Mountain"};
		//testing for <1 col
		String error1 = "cols should be 1 or more";
		int col = 1;
		try {
			Habitat h = new Habitat(col, test1);
		} catch(Exception e) {
			assertEquals(error1, e.getMessage());
		}
		//testing for null
		String[] test2 = null;
		String error2 = "Habitat types cannot be null or empty.";
		int col2 = 2;
		try {
			
		} catch(Exception e) {
			assertEquals(error2, e.getMessage());
		}
		//testing for empty
		String[] test3 = {};
		String error3 = "Habitat types cannot be null or empty.";
		int col3 = 2;
		try {
			
		} catch(Exception e) {
			assertEquals(error3, e.getMessage());
		}
	}
	
	@Test
	public void testingPlaceAnimal() {
		//Habitat setup
		String[] habitatList = {"Mountain", "Desert", "Forest", "Savannah"};
		Habitat h = new Habitat(4, habitatList);
		//Testing null animal
		Animal a = null;
		assertFalse(h.placeAnimal(a));
		//Testing for placing in grid and already in the grid
		Animal l = new Lion("HA", 1, true);
		assertTrue(h.placeAnimal(l));
		assertFalse(h.placeAnimal(l));
		//Testing for full list
		String[] habitatList2 = {"Desert"};
		Habitat h2 = new Habitat(1, habitatList2);
		Animal s1 = new Snake("HA", 1, true);
		Animal s2 = new Snake("HAHA", 2, false);
		h2.placeAnimal(s1);
		assertFalse(h2.placeAnimal(s2));
	}
	@Test
	public void testingPlaceAnimalAt() {
		String[] habitatList = {"Mountain", "Desert", "Forest", "Savannah"};
		Habitat h = new Habitat(4, habitatList);
		//testing for true
		Animal a = new Lion("Skibidi", 4, true);
		assertTrue(h.placeAnimalAt(a, 3, 0));		
		//testing for null
		Animal n = null;
		assertFalse(h.placeAnimalAt(n, 0, 0));
		//testing for out of bounds
		Animal b = new Snake("Tua", 5, false);
		assertFalse(h.placeAnimalAt(n, -1, 0));
		assertFalse(h.placeAnimalAt(n, 5, 0));
	}
	@Test
	public void testingRemoveAnimal() {
		String[] habitatList = {"Mountain", "Desert", "Forest", "Savannah"};
		Habitat h = new Habitat(4, habitatList);
		//testing for null
		Animal a = null;
		h.placeAnimal(a);
		assertFalse(h.removeAnimal(a));
		//testing if it removes correctly
		Animal b = new Lion("RAAHH", 5, true);
		h.placeAnimal(b);
		assertTrue(h.removeAnimal(b));
	}
	@Test
	public void testingGetAnimal() {
		String[] habitatList = {"Mountain", "Desert", "Forest", "Savannah"};
		Habitat h = new Habitat(4, habitatList);
		//testing for getting a null space
		assertEquals(null, h.getAnimal(0, 0));
		//checking for out of bounds
		assertEquals(null, h.getAnimal(-1, 0));
		//testing if it actually gets an animal
		Animal mufasa = new Lion("I always wanted a brotha", 3, false);
		h.placeAnimal(mufasa);
		String result = h.getAnimal(3, 0).toString();
		String answer = "I always wanted a brotha (3)";
		assertEquals(result, answer);
		
	}
	@Test
	public void testDisplayGrid() {
		//just testing to see if it displays correctly
		String[] habitatList = {"Mountain", "Savannah"};
	    Habitat habitat = new Habitat(1, habitatList);
	    Animal lion = new Lion("Lion", 5, true);
	    Animal eagle = new Eagle("Eagle", 7, 2.5);
	    habitat.placeAnimal(lion);
	    habitat.placeAnimal(eagle);
	    String result = habitat.displayGrid();
	    String answer = "[Eagle (7) [Wingspan: 2.5 meters]]\n"
	    		+"[Lion (5) (Alpha)]\n";
	    assertEquals(result, answer);
	  }
	
	//Testing Zoo class
	@Test
	public void testingZoo() {
		String[] habitatList = {"Mountain", "Desert", "Forest", "Savannah"};
		String[] empty = {};
		//testing when col < 1
		try {
			Zoo test1 = new Zoo(0, habitatList);
		} catch(Exception e) {
			assertEquals("col is less than 1", e.getMessage());
		}
		//testing for null and empty
		try {
			Zoo test2 = null;
			Zoo test3 = new Zoo(1, empty);
		} catch(Exception e) {
			assertEquals("Cannot be null or empty.", e.getMessage());
			assertEquals("Cannot be null or empty.", e.getMessage());
		}
	}
	
	@Test
	public void testingAddAnimal() {
		String[] habitatList = {"Mountain", "Desert", "Forest"};
		Zoo z = new Zoo(3, habitatList);
		//testing for null
		Animal n = null;
		assertEquals("Invalid Animal", z.addAnimal(n, 2, 2));
		Animal e1 = new Eagle("Mountain", 2, 3.0);
		Animal l1 = new Lion("Simba", 2, true);
		Animal e2 = new Eagle("Mountain", 2, 3.0);
		Animal l2 = new Lion("Simba", 2, true);
		//testing without row and col
		assertEquals(z.addAnimal(e1, 0, 0),
				"Mountain has been added to the zoo.");
		assertEquals(z.addAnimal(l1, 1, 1), "Failed to place Simba" + 
				" at row 1, col 1.");
		//testing with row and col
		assertEquals(z.addAnimal(e2, null, null),
				"Mountain has been added to the zoo.");
		assertEquals(z.addAnimal(l2, null, null), 
				"Failed to place " + l2.getName() + " in the habitat.");	
	}
	
	@Test
	public void testingShowAllAnimals() {
		String[] habitatList = {"Mountain", "Desert"};
		Zoo z = new Zoo(2, habitatList);
		Animal a1 = new Snake("1", 2, false);
		Animal a2 = new Snake("2", 3, false);
		Animal a3 = new Eagle("3", 4, 2.0);
		z.addAnimal(a1, null, null);
		z.addAnimal(a2, null, null);
		z.addAnimal(a3, null, null);
		String answer = "\nAnimals in the Zoo:\n" +
				"1 (2) (Non-venomous)\n" +
				"2 (3) (Non-venomous)\n" +
				"3 (4) [Wingspan: 2.0 meters]\n";
		assertEquals(z.showAllAnimals(), answer);
	}
	
	@Test
	public void testingShowHabitat() {
		//pretty much the same as displayGrid
		String[] habitatList = {"Mountain", "Savannah"};
	    Zoo z = new Zoo(2, habitatList);
	    String result = z.showHabitat();
	    String answer = "Zoo Habitat:\n"
	    		+ "[null, null]\n"
	    		+ "[null, null]\n";
	    assertEquals(result, answer);
	}
	
	@Test
    public void testSortAnimals() {
        Zoo zoo = new Zoo(3, new String[]{"Savannah", "Desert", "Forest"});
        Animal l1 = new Lion("John", 5, true);
        Animal l2 = new Lion("Pork", 4, false);
        Animal s1 = new Snake("Jake", 5, true);
        Animal s2 = new Snake("Adam", 4, false);
        Animal e = new Eagle("Porco", 5,2.5);
        Animal s3 = new Snake("Rosso", 3, true);
        Animal s4 = new Snake("A Whisker Away", 3, true);
        zoo.addAnimal(l1, null, null);
        zoo.addAnimal(l2, null, null);
        zoo.addAnimal(s1, null, null);
        zoo.addAnimal(s2, null, null);
        zoo.addAnimal(e, null, null);
        zoo.addAnimal(s3, null, null);
        zoo.addAnimal(s4, null, null);
        zoo.sortAnimals();
        String result =  zoo.showAllAnimals();
        String answer = "\n"
        		+ "Animals in the Zoo:\n"
        		+ "A Whisker Away (3) (Venomous)\n"
        		+ "Adam (4) (Non-venomous)\n"
        		+ "Jake (5) (Venomous)\n"
        		+ "John (5) (Alpha)\n"
        		+ "Porco (5) [Wingspan: 2.5 meters]\n"
        		+ "Pork (4)\n"
        		+ "Rosso (3) (Venomous)\n";
        assertEquals(answer, result);
    }
	
	@Test
	public void testingFindByType() {
        Zoo z = new Zoo(3, new String[]{"Savannah", "Desert", "Forest"});
        Animal a1 = new Lion("Albert", 2, true);
        Animal a2 = new Lion("Escanor", 3, true);
        Animal a3 = new Snake("Meliodas", 10000, true);
        z.addAnimal(a1, null, null);
        z.addAnimal(a2, null, null);
        z.addAnimal(a3, null, null);
        //finding one animal
        String result1 = z.findAnimalsByType(Snake.class);
		String answer1 = "\n"
				+ "Finding Animals of Type: Snake\n" +			
				"Meliodas (10000) (Venomous)\n";
		assertEquals(result1, answer1);
		//finding multiple
        String result2 = z.findAnimalsByType(Lion.class);
        String answer2 = "\n"
        		+ "Finding Animals of Type: Lion\n"
        		+ "Albert (2) (Alpha)\n"
        		+ "Escanor (3) (Alpha)\n";
        assertEquals(result2, answer2);
	}
	
	@Test
	public void testingFindVenomous() {
		Zoo z = new Zoo(3, new String[]{"Savannah", "Desert", "Forest"});
        Animal a1 = new Lion("Albert", 2, true);
        Animal a2 = new Snake("Servine", 3, true);
        Animal a3 = new Snake("Meliodas", 10000, true);
        Animal a4 = new Snake("Zeldris", 9000, false);
        Animal a5 = new Snake("Serperior", 24, true);
        //testing for empty
        assertEquals("[]", Arrays.toString(z.findVenomousSnakes()));
        z.addAnimal(a1, null, null);
        z.addAnimal(a2, null, null);
        z.addAnimal(a3, null, null);
        z.addAnimal(a4, null, null);
        z.addAnimal(a5, null, null);
        String result = Arrays.toString(z.findVenomousSnakes());
        String answer = "[Servine (3) (Venomous), "
        		+ "Meliodas (10000) (Venomous), "
        		+ "Serperior (24) (Venomous)]";
        //testing for full
        assertEquals(result, answer);
	}
	
	//Testing Eagle Class
	@Test
	public void testingEagle() {
		//testing within bounds
		Eagle e1 = new Eagle("Hawk", 3, 2.5);
		//testing out of bounds
		try {
			Animal e2 = new Eagle("Tuah", 3, 1.0);
		} catch(Exception e) {
			assertEquals("Wingspan must be between"
					+ " 1.5 and 3 meters inclusive.", e.getMessage());
		}
		//getting name
		assertEquals(e1.getName(), "Hawk");
		//getting age
		assertEquals(e1.getAge(), 3);
		//getting wing span
		double d = e1.getWingspan();
		//double needs a margin of error.
		assertEquals(2.5, d, 0.01);
		
	}
	
	@Test
	public void testingIsCompatibleWithHabitatEagle() {
		String[] habitatList = {"Forest", null, "mOuNTain", "River"};
		Eagle e1 = new Eagle("Captain Falcon", 3, 2.5);
		//normal test
		assertTrue(e1.isCompatibleWithHabitat(habitatList[0]));
		//testing for null
		assertFalse(e1.isCompatibleWithHabitat(habitatList[1]));
		//testing for case
		assertTrue(e1.isCompatibleWithHabitat(habitatList[2]));
		//testing for non-compatible cases
		assertFalse(e1.isCompatibleWithHabitat(habitatList[3]));
	}
	
	@Test
	public void testingToStringEagle() {
		Eagle e = new Eagle("e", 2, 2);
		String result = e.toString();
		//not many edge cases to be testing
		//so I'm testing only if it even matches the req
		String answer = "e (2) [Wingspan: 2.0 meters]";
		assertEquals(result, answer);
	}
	
	//Testing Lion Class
	@Test
	public void testingLion() {
		Lion l = new Lion("RAH", 2, true);		
		//getting name
		assertEquals("RAH", l.getName());
		//getting age
		assertEquals(2, l.getAge());
		//getting isAlpha
		assertEquals(true, l.isAlpha());
	}
	
	@Test
	public void testingIsCompatibleWithHabitatLion() {
		String[] habitatList = {"Forest", null, "Savannah", "savANNAH"};
		Lion l = new Lion("RAH", 2, true);		
		//normal test
		assertFalse(l.isCompatibleWithHabitat(habitatList[0]));
		//testing for null
		assertFalse(l.isCompatibleWithHabitat(habitatList[1]));
		//testing for case
		assertTrue(l.isCompatibleWithHabitat(habitatList[2]));
		//testing for non-compatible cases
		assertTrue(l.isCompatibleWithHabitat(habitatList[3]));
	}
	
	@Test
	public void testingToStringLion() {
		Lion l = new Lion("Sim", 2, true);
		Lion l2 = new Lion("Mis", 5, false);
		//isAlpha
		String result1 = l.toString();
		String answer1 = "Sim (2) (Alpha)";
		assertEquals(result1, answer1);
		//not isAlpha
		String result2 = l2.toString();
		String answer2 = "Mis (5)";
		assertEquals(result2, answer2);
	}
	
	//Testing Snake Class	
	@Test
	public void testingSnake() {
		Snake s1 = new Snake("RAH", 2, true);		
		//getting name
		assertEquals("RAH", s1.getName());
		//getting age
		assertEquals(2, s1.getAge());
		//getting isVenomous
		assertEquals(true, s1.isVenomous());
	}
	
	@Test
	public void testingIsCompatibleWithHabitatSnake() {
		String[] habitatList = {"Savannah", null, "desert", "DeSerT"};
		Snake s1 = new Snake("RAH", 2, true);
		//incorrect habitat
		assertFalse(s1.isCompatibleWithHabitat(habitatList[0]));
		//testing for null
		assertFalse(s1.isCompatibleWithHabitat(habitatList[1]));
		//testing for normal
		assertTrue(s1.isCompatibleWithHabitat(habitatList[2]));
		//testing for random upper case
		assertTrue(s1.isCompatibleWithHabitat(habitatList[3]));
	}
	
	@Test
	public void testingToStringSnake() {
		Snake s1 = new Snake("Sim", 2, true);
		Snake s2 = new Snake("Mis", 5, false);
		//isAlpha
		String result1 = s1.toString();
		String answer1 = "Sim (2) (Venomous)";
		assertEquals(result1, answer1);
		//not isAlpha
		String result2 = s2.toString();
		String answer2 = "Mis (5) (Non-venomous)";
		assertEquals(result2, answer2);
	}
	
	//Testing Rattlesnake Class
	@Test
	public void testingRattleSnake() {
		try {
			RattleSnake rs = new RattleSnake("RS", 1, 8);
		} catch(Exception e) {
			assertEquals("Rattle segments must be between 8 and 13 inclusive.",
					e.getMessage());
		}
		RattleSnake rs = new RattleSnake("RS", 1, 9);
		//testing getting name
		assertEquals("RS", rs.getName());
		//testing getting age
		assertEquals(1, rs.getAge());
		//testing venomous
		assertEquals(true, rs.isVenomous());
		//testing getRattleSegments
		assertEquals(9, rs.getRattleSegments());
	}
}



