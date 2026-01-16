package zoo;

/**
 * Abstract base class representing an Animal in the zoo.
 * Provides common properties and methods for all animals.
 */
public abstract class Animal implements Comparable<Animal> {
    private String name;
    private int age;
    
    public Animal(String name, int age) {
    	if(name == null) {
    		throw new IllegalArgumentException("Name cannot be null");
    	}
    	if(age <= 0) {
    		throw new IllegalArgumentException("Age must be greater than 0.");
    	}
    	this.name = name;
    	this.age = age;

    }

    public String getName() {
    	String nameCopy = this.name;
    	return nameCopy;
    }

    public int getAge() {
    	int ageCopy = this.age;
    	return ageCopy;
    }

    public void incrementAge() {
    	this.age += 1;
    }

    public abstract boolean isCompatibleWithHabitat(String habitatType);

    @Override
    public int compareTo(Animal obj) {
    	if(obj == null) {
    		throw new NullPointerException("Cannot compare to a null animal.");
    	}
    	int compareName =
    			this.name.toLowerCase().compareTo(obj.name.toLowerCase());
    	if(compareName != 0) {
    		return compareName;
    	}
    	int compareAge = this.age - obj.age;
    	if(compareAge != 0) {
    		return compareAge;
    	}
    	return 0;
    }

    @Override
    public boolean equals(Object obj) {
    	if(this.getClass() == obj.getClass()) {
    		if(compareTo((Animal)obj) == 0) {
    			return true;
    		}
    	}
    	return false;
    }

    @Override
    public String toString() {
        return name + " (" + age + ")";
    }
}
