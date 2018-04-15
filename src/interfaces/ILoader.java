package interfaces;

import classes.GameController;

public interface ILoader {
	boolean loadLevel(ILoadable game, int levelNumber);
	boolean loadSave(GameController game);
}
