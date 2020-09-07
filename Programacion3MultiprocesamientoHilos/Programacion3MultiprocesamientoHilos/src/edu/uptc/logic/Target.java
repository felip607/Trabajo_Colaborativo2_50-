package edu.uptc.logic;

public class Target {
	
	private String target;
	private int distance;
	private int value;
	
	public Target(String target, int distance, int value) {
		super();
		this.target = target;
		this.distance = distance;
		this.value = value;
	}
	
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "Target [target=" + target + ", distance=" + distance + ", value=" + value + "]";
	}
}
