package classes;

import interfaces.IPoint;

public class GamePoint implements IPoint {
	
	protected int row;
	protected int col;

	public GamePoint(int row, int col) {
		 this.row = row;
		 this.col = col;
	}

	@Override
	public int getRow() {
		return this.row;
	}

	@Override
	public int getCol() {
		return this.col;
	}

	@Override
	public void setLocation(int row, int col) {
		this.row = row;
        this.col = col;
	}

	@Override
	public void moveLocation(int row, int col) {
		this.row += row;
		this.col += col;
	}
}
