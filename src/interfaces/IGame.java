package interfaces;

import classes.GamePoint;
import enums.Direction;

public interface IGame {
	
	void moveTheseus(Direction direction);
	void moveMinotaur();
	boolean canMove(Direction direction, GamePoint character);
	
}