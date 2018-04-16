package interfaces;

import classes.GamePoint;
import enums.Direction;

public interface IGame {
	boolean moveTheseus(Direction direction);
	void incrementTheseusMove();
	boolean theseusHasWon();
	
	void moveMinotaur();
	boolean minotaurHasWon();
	
	void skipTheseus();
	void resetLevel();
	
	boolean canMove(Direction direction, GamePoint character);
}