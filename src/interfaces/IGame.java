package interfaces;

import classes.GamePoint;
import enums.Direction;

public interface IGame {
	
	void moveTheseus(Direction direction);
	void skipTheseus();
	
	void moveMinotaur();
	
	void incrementTheseusMove();
	
	boolean canMove(Direction direction, GamePoint character);
	
}