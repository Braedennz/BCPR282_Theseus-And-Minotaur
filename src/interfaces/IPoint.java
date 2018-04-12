package interfaces;

public interface IPoint {
	
	int getX();
	int getY();
	
    void setLocation(int x, int y);
	void moveLocation(int x, int y);
	int[] nextLocation(int x, int y);
	
	boolean equals(IPoint character);
}
