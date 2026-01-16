package zoo;

public class Snake extends Animal {
    private boolean isVenomous;
    
    public Snake(String name, int age, boolean isVenomous) {
    	super(name, age);
    	this.isVenomous = isVenomous;
    }
    
    public boolean isVenomous() {
    	return isVenomous == true;
    }
    
    public boolean isCompatibleWithHabitat(String habitatType) {
    	if(habitatType == null) {
    		return false;
    	}
    	return habitatType.equalsIgnoreCase("Forest") ||
    			habitatType.equalsIgnoreCase("Desert");
    }

    public String toString() {
    	String venom = "";
    	if(isVenomous) venom = " (Venomous)";
    	else venom = " (Non-venomous)";
    	return super.toString() + venom;
    }
}

