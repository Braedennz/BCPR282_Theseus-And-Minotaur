package interfaces;

import classes.GamePoint;
import enums.Direction;

public interface IGame {
	void moveTheseus(Direction direction);
	void incrementTheseusMove();
	boolean theseusHasWon();
	
	void moveMinotaur();
	boolean minotaurHasWon();
	
	void skipTheseus();
	
	boolean canMove(Direction direction, GamePoint character);
}