package zoo;

public class RattleSnake extends Snake {
   
	private int rattleSegments;
	
	public RattleSnake(String name, int age, int rattleSegments) {
		super(name, age, true);
		if(rattleSegments < 8 || rattleSegments > 13) {
			throw new IllegalArgumentException
			("Rattle segments must be between 8 and 13 inclusive.");
		}
		this.rattleSegments = rattleSegments;
	}
	
	public int getRattleSegments() {
		int rattleSegmentsCopy = this.rattleSegments;
		return rattleSegmentsCopy;
	}
	
	public String toString() {
		return super.toString() + " [Rattle Segments: " + rattleSegments + "]";
	}
}
