package tests;

import classes.GameController;
import enums.Direction;

public class GameRunner {
	public static void main(String args[]) {
		GameController controller = new GameController(0);
		
		if(controller.readyToPlay) {
			System.out.println("\nTesting Theseus Movement:\n");

			controller.moveTheseus(Direction.RIGHT);
			controller.moveTheseus(Direction.UP);
			controller.moveTheseus(Direction.RIGHT);
			
			controller.skipTheseus();
		}
	}
}
