package tests;

import static org.junit.Assert.*;
import org.junit.Test;

import classes.GameController;

public class GameControllerTest {
	
	/*
	 * Check if theseus character exists
	 * @result The X and Y axis loaded should match my hard coded axis
	 */
	@Test
	public void getTheseusPosition() {
		
		GameController controller = new GameController(0, false);
		int expectedX = 1;
		int expectedY = 2;
		
		int actualX = controller.theseusCharacter.getX();
		int actualY = controller.theseusCharacter.getY();
		
		assertEquals( "X and Y axis did not match", expectedX + ", " + expectedY, actualX + ", " + actualY );
	}
	
}

