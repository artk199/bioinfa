package bioinfa.model;

public class BestUGMAPair {
	private int firstPosition;
	private int secondPosition;
	private double difference;
	
	public int getFirstPosition() {
		return firstPosition;
	}
	public void setFirstPosition(int firstPosition) {
		this.firstPosition = firstPosition;
	}
	public int getSecondPosition() {
		return secondPosition;
	}
	public void setSecondPosition(int secondPosition) {
		this.secondPosition = secondPosition;
	}
	public double getDifference() {
		return difference;
	}
	public void setDifference(double difference) {
		this.difference = difference;
	}
	
	@Override
	public String toString(){
		return "[(" + firstPosition + ", " + secondPosition + ") -> " + difference + " ]";
	}
}
