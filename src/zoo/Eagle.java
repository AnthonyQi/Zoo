package zoo;

public class Eagle extends Animal {
    private double wingspan;
    
    public Eagle(String name, int age, double wingspan) {
    	super(name, age);
    	if(wingspan >= 1.5 && wingspan <= 3.0) {
    		this.wingspan = wingspan;
    	}
    	else {
    		throw new IllegalArgumentException
    		("Wingspan must be between 1.5 and 3 meters inclusive.");
    	}
    }
    public double getWingspan() {
    	double wingspanCopy = this.wingspan;
    	return wingspanCopy;
    }
    public boolean isCompatibleWithHabitat(String habitatType) {
    	if(habitatType == null) {
    		return false;
    	}
    	return (habitatType.equalsIgnoreCase("Mountain"))||
    			(habitatType.equalsIgnoreCase("Forest"));
    }
    public String toString() {
    	return super.toString() + " [Wingspan: " + wingspan + " meters]";
    }
}
