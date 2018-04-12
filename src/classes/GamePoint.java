package classes;

import interfaces.IPoint;

public class GamePoint implements IPoint {
	/*
	 * X (horizontal axis from 0)
	 * Y (vertical axis from 0)
	 */
	protected int x;
	protected int y;

	public GamePoint(int x, int y) {
		 this.x = x;
		 this.y = y;
	}
	
	public GamePoint(IPoint characterPoint) {
		this.x = characterPoint.getX();
		this.y = characterPoint.getY();
	}

	@Override
	public int getX() {
		return this.x;
	}

	@Override
	public int getY() {
		return this.y;
	}

	@Override
	public void setLocation(int x, int y) {
		this.x = x;
        this.y = y;
	}

	@Override
	public int[] nextLocation(int x, int y) {
		int nextX = this.x;
		int nextY = this.y;
		
		nextX += x;
		nextY += y;
		
		return new int[]{nextX, nextY};
	}

	@Override
	public void moveLocation(int x, int y) {
		this.x += x;
		this.y += y;
	}

	@Override
	public boolean equals(IPoint character) {
		return this.x == character.getX() && this.y == character.getY();
	}
	
	public String toString() {
		return "X: " + this.getX() + ", Y: " + this.getY();
	}
}
