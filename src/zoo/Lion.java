package zoo;

public class Lion extends Animal {
    private boolean isAlpha;
    
    public Lion(String name, int age, boolean isAlpha) {
    	super(name, age);
    	this.isAlpha = isAlpha;
    }
    
    public boolean isAlpha() {
    	return this.isAlpha == true;
    }
    
    public boolean isCompatibleWithHabitat(String habitatType) {
    	if(habitatType == null) {
    		return false;
    	}
    	return habitatType.equalsIgnoreCase("Savannah");
    }
    
    public String toString() {
    	String alpha = "";
    	if(isAlpha) {
    		alpha = " (Alpha)";
    	}
    	return super.toString() + alpha;
    }

}

