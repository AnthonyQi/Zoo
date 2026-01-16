package zoo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a zoo that manages animals and their habitats.
 */
public class Zoo {
    private List<Animal> animals;
    private Habitat habitat;

    //initializes zoo object that instantiates an array list of animals.
    public Zoo(int cols, String[] habitatTypes) {
    	if(cols < 1) {
    		throw new IllegalArgumentException("col is less than 1");
    	}
    	if(habitatTypes == null || habitatTypes.length == 0) {
    		throw new IllegalArgumentException("Cannot be null or empty.");
    	}
    	this.habitat = new Habitat(cols, habitatTypes);
    	this.animals = new ArrayList<>();
    }
    
    //adds an animal to the animals list
    public String addAnimal(Animal animal, Integer row, Integer col) {
    	//check for null
    	if(animal == null) return "Invalid Animal";
    	boolean placed;
    	//check for null row and col
    	if(row != null && col != null) {
    		placed = habitat.placeAnimalAt(animal, row, col);
    		if(placed) {
    			animals.add(animal);
    			return animal.getName() + " has been added to the zoo.";
    		} else {
    			return "Failed to place " + animal.getName()
    			+ " at row " + row + ", col " + col + ".";
    		}
    	} else {
    		placed = habitat.placeAnimal(animal);
    		if(placed) {
    			animals.add(animal);
    			return animal.getName() + " has been added to the zoo.";
    		} else {
    			return "Failed to place " + animal.getName() + 
    					" in the habitat.";
    		}
    	}
    }
    
    //adds all animals to a string that will be printed out when called
    public String showAllAnimals() {
        String animalList = "\nAnimals in the Zoo:\n";
        for(Animal a: animals) {
        	animalList += a.toString() + "\n";
        }
        return animalList;
    }
    
    //returns a list of the habitats in a zoo.
    public String showHabitat() {
    	return "Zoo Habitat:\n" + this.habitat.displayGrid();
    }
    
    //sorts all animals by name and age
    public void sortAnimals() {
    	Collections.sort(animals);
    }
    
    //searches for all animals by the class.
    public String findAnimalsByType(Class<?> type) {
    	String byType = "\nFinding Animals of Type: "
    			+ type.getSimpleName() + "\n";
    	for(Animal a: animals) {
    		if(type.isInstance(a)) {
    			byType += a + "\n";
    		}
    	}
    	return byType;
    }
    
    //finds snakes and which snakes has variable isVenomous set to true
    public Snake[] findVenomousSnakes() {
    	ArrayList<Snake> venom = new ArrayList<Snake>();
    	for(Animal a: animals) {
    		if(a instanceof Snake) {
    			Snake snake = (Snake) a;
                if (snake.isVenomous()) {
                    venom.add(snake);    		
                }
    		}
    	}
    	return venom.toArray(new Snake[venom.size()]);
    }
}