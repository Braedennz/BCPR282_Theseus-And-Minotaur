package enums;

public enum Direction {
	//x, y
	UP(0, -1),
	DOWN(0, 1),
	LEFT(-1, 0),
	RIGHT(1, 0);
	
	public int x;
	public int y;
	
	Direction(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public String toString() {
		return this.name();
	}
}
