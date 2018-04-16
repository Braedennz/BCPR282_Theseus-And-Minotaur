package tests;

import classes.GameController;
import enums.Direction;

public class GameRunner {
	public static void main(String args[]) {
		GameController controller = new GameController(0, false);
		
		//controller.displayGameConfiguration();
		
		if(controller.readyToPlay) {
			System.out.println("\nTesting Theseus Movement:\n");

			controller.moveTheseus(Direction.LEFT);
			controller.moveMinotaur();
			controller.moveMinotaur();

			/*FileLoader xmlLoader = new FileLoader();
			xmlLoader.save(controller);*/
			
			controller.resetLevel();
		}
	}
}
