package interfaces;

public interface IPoint {
	
	int getRow();
	int getCol();
	
    void setLocation(int row, int col);
	void moveLocation(int row, int col);
}
