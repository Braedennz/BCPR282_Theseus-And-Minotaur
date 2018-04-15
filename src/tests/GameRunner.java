package tests;

import classes.FileLoader;
import classes.GameController;
import enums.Direction;

public class GameRunner {
	public static void main(String args[]) {
		GameController controller = new GameController(-1, true);
		
		//controller.displayGameConfiguration();
		
		if(controller.readyToPlay) {
			/*System.out.println("\nTesting Theseus Movement:\n");

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
			controller.moveMinotaur();
			controller.moveMinotaur();
			controller.moveTheseus(Direction.RIGHT);
			
			String minotaurHasWon = controller.minotaurHasWon() ? 
					"Minotaur won the game" : "Minotaur has not won yet.";
			System.out.println(minotaurHasWon);
			
			String theseusHasWon = controller.theseusHasWon() ? 
					"Theseus won the game in " + controller.amountOfMoves + " moves." : "Theseus has not won yet.";
			System.out.println(theseusHasWon);*/

			FileLoader xmlLoader = new FileLoader();
			//xmlLoader.save(controller);
		}
	}
}
