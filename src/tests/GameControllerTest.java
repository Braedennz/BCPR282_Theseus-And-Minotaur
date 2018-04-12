package tests;

import static org.junit.Assert.*;
import org.junit.Test;

import classes.GameController;

public class GameControllerTest {
	
	/*
	 * Check theseus position against correct position
	 * @result The X and Y axis loaded should match my hard coded axis
	 */
	@Test
	public void getTheseusPosition() {
		
		GameController controller = new GameController(0);
		int expectedX = 1;
		int expectedY = 2;
		
		int actualX = controller.theseusCharacter.getX();
		int actualY = controller.theseusCharacter.getY();
		
		assertEquals( "X and Y axis did not match", expectedX + ", " + expectedY, actualX + ", " + actualY );
	}
	
	/*
	 * Check minotaur position against correct position
	 * @result The X and Y axis loaded should match my hard coded axis
	 */
	@Test
	public void getMinotaurPosition() {
		
		GameController controller = new GameController(0);
		int expectedX = 1;
		int expectedY = 0;
		
		int actualX = controller.minotaurCharacter.getX();
		int actualY = controller.minotaurCharacter.getY();
		
		assertEquals( "X and Y axis did not match", expectedX + ", " + expectedY, actualX + ", " + actualY );
	}
	
	/*
	 * Check exit position against correct position
	 * @result The X and Y axis loaded should match my hard coded axis
	 */
	@Test
	public void getExitPosition() {
		
		GameController controller = new GameController(0);
		int expectedX = 3;
		int expectedY = 1;
		
		int actualX = controller.exitCharacter.getX();
		int actualY = controller.exitCharacter.getY();
		
		assertEquals( "X and Y axis did not match", expectedX + ", " + expectedY, actualX + ", " + actualY );
	}
	
}

