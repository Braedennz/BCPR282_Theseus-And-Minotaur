package tests;

import classes.GameController;
import enums.Direction;

public class GameRunner {
	public static void main(String args[]) {
		GameController controller = new GameController(0);
		
		//controller.displayGameConfiguration();
		
		if(controller.readyToPlay) {
			System.out.println("\nTesting Theseus Movement:\n");

			controller.moveTheseus(Direction.LEFT);
			controller.moveMinotaur();
			controller.moveMinotaur();
			controller.moveTheseus(Direction.RIGHT);
			controller.moveMinotaur();
			controller.moveMinotaur();
			controller.moveTheseus(Direction.RIGHT);
			controller.moveMinotaur();
			controller.moveMinotaur();
			controller.moveTheseus(Direction.UP);
			
			String minotaurHasWon = controller.minotaurHasWon() ? 
					"Minotaur won the game" : "Minotaur has not won yet.";
			System.out.println(minotaurHasWon);
			
			String theseusHasWon = controller.theseusHasWon() ? 
					"Theseus won the game in " + controller.amountOfMoves + " moves." : "Theseus has not won yet.";
			System.out.println(theseusHasWon);
		}
	}
}
