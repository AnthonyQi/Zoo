package zoo;
import java.util.Arrays;

public class Habitat {
    private Animal[][] habitatGrid; // 2D grid to store animals
    private String[] rowHabitatTypes; // Types for each row of habitatGrid
    
    public Habitat(int cols, String [] types) {
    	//checks if col is less than 1
    	if(cols < 1) {
    		throw new IllegalArgumentException("cols should be 1 or more");
    	}
    	//checks if variable is null or empty
    	if(types == null || types.length == 0) {
    		throw new IllegalArgumentException
    		("Habitat types cannot be null or empty.");
    	}
    	this.rowHabitatTypes = types;
        this.habitatGrid = new Animal[types.length][cols];
    }
    
    public boolean placeAnimal(Animal animal) {
    	//checks if animal is null
    	if(animal == null) {
    		return false;
    	}
    	//nested for loops to see if other instances of animal show up
    	for(int i = 0; i < habitatGrid.length; i++) {
    		for(int j = 0; j < habitatGrid[i].length; j++) {
    			if(habitatGrid[i][j] == animal) {
    				return false;
    			}
    		}
    	}
    	//searches for empty grid
    	for (int i = 0; i < habitatGrid.length; i++) {
            String habitatType = rowHabitatTypes[i];
            if (animal.isCompatibleWithHabitat(habitatType)) {
                for (int j = 0; j < habitatGrid[i].length; j++) {
                    if (habitatGrid[i][j] == null) { 
                        habitatGrid[i][j] = animal;
                        return true;
                    }
                }
            }
    	}
    	return false;
    }
    
    public boolean placeAnimalAt(Animal animal, int row, int col) {
    	//checks for null
        if (animal == null) {
            return false;
        }
        //checks for out of bounds indices
        if (row < 0 || row >= habitatGrid.length ||
        		col < 0 || col >= habitatGrid[row].length) {
            return false;
        }
        //checks for instances of the same animal
        for (int i = 0; i < habitatGrid.length; i++) {
            for (int j = 0; j < habitatGrid[i].length; j++) {
                if (habitatGrid[i][j] == animal) {
                    return false;
                }
            }
        }
        //checks if the grid of the habitat grid contains an animal
        if (habitatGrid[row][col] != null) {
            return false;
        }
        //checks for habitat compatibility
        String habitatType = rowHabitatTypes[row];
        if (!animal.isCompatibleWithHabitat(habitatType)) {
            return false;
        }
        //sets habitatGrid at row and col as animal, returns true.
        habitatGrid[row][col] = animal;
        return true;
    }
    
    //removes animal from the habitatGrid 2D array
    public boolean removeAnimal(Animal animal) {
    	//checks for null
        if (animal == null) {
            return false; 
        }
        //nested for loop to check for instance of animal and then remove
        for (int i = 0; i < habitatGrid.length; i++) {
            for (int j = 0; j < habitatGrid[i].length; j++) {
                if (habitatGrid[i][j] == animal) {
                    habitatGrid[i][j] = null;
                    return true;
                }
            }
        }
        return false;
    }
    //returns an animal specified by the row and column
    public Animal getAnimal(int row, int col) {
    	//check out of bounds
        if (row < 0 || row >= habitatGrid.length 
        		|| col < 0 || col >= habitatGrid[row].length) {
            return null;
        }
        return habitatGrid[row][col];
    }
    
    //turns habitatGrid into a string and returns it
    public String displayGrid() {
        String display = "";
        for (Animal[] row : habitatGrid) {
            display += Arrays.toString(row) + "\n";
        }
        return display;
    }
}
