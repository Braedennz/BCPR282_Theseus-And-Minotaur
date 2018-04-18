package tests;

import static org.junit.Assert.*;
import org.junit.Test;

import classes.FileLoader;
import classes.GameController;
import classes.GamePoint;
import enums.Direction;
import interfaces.IPoint;

public class GameControllerTest {
	
	/*
	 * Check if loading from XML file works
	 * @result The expected maze title matches the loaded one
	 */
	@Test
	public void loadLevelFromXMLFileIntoGame() {
		GameController controller = new GameController(0, false);
		
		String expectedGameTitle = "Maze 1 (3 x 3)";
		String actualGameTitle = controller.gameTitle;
		
		assertEquals(expectedGameTitle, actualGameTitle);
	}
	
	/*
	 * Check if loading from XML file works with multiple different levels
	 * @result The expected maze title matches the loaded ones
	 */
	@Test
	public void loadMultipleLevelsFromXMLFileIntoGame() {
		String expectedGameTitle = "";
		String actualGameTitle = "";
		
		/* Level One */
		GameController controller = new GameController(0, false);
		
		expectedGameTitle = "Maze 1 (3 x 3)";
		actualGameTitle = controller.gameTitle;
		
		assertEquals(expectedGameTitle, actualGameTitle);
		
		/* Level Two */
		
		controller = new GameController(1, false);

		expectedGameTitle = "Maze 2 (7 x 4)";
		actualGameTitle = controller.gameTitle;
		
		assertEquals(expectedGameTitle, actualGameTitle);
		
		/* Level Three */
		
		controller = new GameController(2, false);

		expectedGameTitle = "Maze 3 (3 x 4)";
		actualGameTitle = controller.gameTitle;
		
		assertEquals(expectedGameTitle, actualGameTitle);
	}
	
	/*
	 * Load saves from XML file into game
	 * @result The expected maze title matches the loaded one -
	 * && the expected game level matches the loaded one
	 */
	@Test
	public void loadSaveFromXMLFile() {
		GameController controller = new GameController(-1, true);
		
		String expectedGameTitle = "Maze 4 (5 x 5)";
		String actualGameTitle = controller.gameTitle;
		
		assertEquals(expectedGameTitle, actualGameTitle);
		
		int expectedGameLevel = 3;
		int actualGameLevel = controller.gameLevel;
		
		assertEquals(expectedGameLevel, actualGameLevel);
	}

	
	/*
	 * Create a save for the current games state in XML file state
	 * @result theseus position saved is (4,2) and minotaurs position is (1,2)
	 */
	@Test
	public void createGameSaveAsXMLFile() {
		
		//Create game data to save
		GameController controller = new GameController(1, false);
		
		controller.moveTheseus(Direction.RIGHT);
		controller.moveMinotaur();
		controller.moveMinotaur();
		controller.moveTheseus(Direction.RIGHT);
		controller.moveMinotaur();
		controller.moveMinotaur();
		controller.moveTheseus(Direction.DOWN);
		controller.moveMinotaur();
		controller.moveMinotaur();
		
		//Save game
		FileLoader xmlLoader = new FileLoader();
		xmlLoader.save(controller);
		
		//Load saved data
		controller = new GameController(-1, true);
		
		IPoint expectedTheseusPoint = new GamePoint(4, 2);
		IPoint actualTheseusPoint = controller.wheresTheseus();
		
		assertTrue(expectedTheseusPoint.equals(actualTheseusPoint));
		
		IPoint expectedMinotaurPoint = new GamePoint(1, 2);
		IPoint actualMinotaurPoint = controller.wheresMinotaur();
		
		assertTrue(expectedMinotaurPoint.equals(actualMinotaurPoint));
	}

	/*
	 * Theseus character is able to move upwards
	 * @result theseus new game point is (2,0) on map 2
	 */
	@Test
	public void theseusCanMoveUp() {
		
		//Create game data to save
		GameController controller = new GameController(1, false);
		
		controller.moveTheseus(Direction.UP);
		
		IPoint expectedTheseusPoint = new GamePoint(2, 0);
		IPoint actualTheseusPoint = controller.wheresTheseus();
		
		assertTrue(expectedTheseusPoint.equals(actualTheseusPoint));
	}

	/*
	 * Theseus character is able to move downwards
	 * @result theseus new game point is (2,2) on map 2
	 */
	@Test
	public void theseusCanMoveDown() {
		
		//Create game data to save
		GameController controller = new GameController(1, false);
		
		controller.moveTheseus(Direction.DOWN);
		
		IPoint expectedTheseusPoint = new GamePoint(2, 2);
		IPoint actualTheseusPoint = controller.wheresTheseus();
		
		assertTrue(expectedTheseusPoint.equals(actualTheseusPoint));
	}

	/*
	 * Theseus character is able to move left
	 * @result theseus new game point is (0,2) on map 1
	 */
	@Test
	public void theseusCanMoveLeft() {
		
		//Create game data to save
		GameController controller = new GameController(0, false);
		
		controller.moveTheseus(Direction.LEFT);
		
		IPoint expectedTheseusPoint = new GamePoint(0, 2);
		IPoint actualTheseusPoint = controller.wheresTheseus();
		
		assertTrue(expectedTheseusPoint.equals(actualTheseusPoint));
	}

	/*
	 * Theseus character is able to move right
	 * @result theseus new game point is (3,1) on map 2
	 */
	@Test
	public void theseusCanMoveRight() {
		
		//Create game data to save
		GameController controller = new GameController(1, false);
		
		controller.moveTheseus(Direction.RIGHT);
		
		IPoint expectedTheseusPoint = new GamePoint(3, 1);
		IPoint actualTheseusPoint = controller.wheresTheseus();
		
		assertTrue(expectedTheseusPoint.equals(actualTheseusPoint));
	}
	
	/*
	 * Minotaur character is able to move upwards
	 * @result Minotaur new game point is (2,0) on map 2
	 */
	@Test
	public void minotaurCanMoveUp() {
		
		//Create game data to save
		GameController controller = new GameController(1, false);
		
		controller.moveTheseus(Direction.UP);
		controller.moveMinotaur();
		
		IPoint expectedMinotaurPoint = new GamePoint(0, 0);
		IPoint actualMinotaurPoint = controller.wheresMinotaur();
		
		assertTrue(expectedMinotaurPoint.equals(actualMinotaurPoint));
	}
	
	/*
	 * Minotaur character is able to move downwards
	 * @result Minotaur new game point is (0,2) on map 2
	 */
	@Test
	public void minotaurCanMoveDown() {
		
		//Create game data to save
		GameController controller = new GameController(1, false);
		
		controller.moveTheseus(Direction.DOWN);
		controller.moveMinotaur();
		
		IPoint expectedMinotaurPoint = new GamePoint(0, 2);
		IPoint actualMinotaurPoint = controller.wheresMinotaur();
		
		assertTrue(expectedMinotaurPoint.equals(actualMinotaurPoint));
	}
	
	/*
	 * Minotaur character is able to move left
	 * @result Minotaur new game point is (0,0) on map 2
	 */
	@Test
	public void minotaurCanMoveLeft() {
		
		//Create game data to save
		GameController controller = new GameController(0, false);
		
		controller.moveTheseus(Direction.LEFT);
		controller.moveMinotaur();
		
		IPoint expectedMinotaurPoint = new GamePoint(0, 0);
		IPoint actualMinotaurPoint = controller.wheresMinotaur();
		
		assertTrue(expectedMinotaurPoint.equals(actualMinotaurPoint));
	}
	
	/*
	 * Minotaur character is able to move right
	 * @result Minotaur new game point is (1,1) on map 2
	 */
	@Test
	public void minotaurCanMoveRight() {
		
		//Create game data to save
		GameController controller = new GameController(0, false);
		
		controller.moveTheseus(Direction.LEFT);
		controller.moveMinotaur();
		controller.moveMinotaur();
		controller.moveTheseus(Direction.RIGHT);
		controller.moveMinotaur();
		
		IPoint expectedMinotaurPoint = new GamePoint(1, 1);
		IPoint actualMinotaurPoint = controller.wheresMinotaur();
		
		assertTrue(expectedMinotaurPoint.equals(actualMinotaurPoint));
	}
	
	/*
	 * Theseus character is able to skip turn
	 * @result Theseus new game point is (0,2) on map 1
	 */
	@Test
	public void minotaurCanSkipTurn() {
		
		//Create game data to save
		GameController controller = new GameController(0, false);
		
		controller.moveTheseus(Direction.LEFT);
		controller.moveMinotaur();
		controller.moveMinotaur();
		controller.skipTheseus();
		
		IPoint expectedTheseusSkippedPosition = new GamePoint(0, 2);
		IPoint theseusSkippedPosition = controller.wheresTheseus();
		
		assertTrue(expectedTheseusSkippedPosition.equals(theseusSkippedPosition));
	}
	
	/*
	 * Theseus character cannot exit map boundaries
	 * @result returns boolean true, false if character can move in specific direction
	 */
	@Test
	public void theseusHasBoundaries() {
		
		//Create game data to save
		GameController controller = new GameController(0, false);
		
		boolean expectedCanTheseusMove = false;
		boolean actualCanTheseusMove = controller.canMove(Direction.DOWN, controller.theseusCharacter);
		
		assertEquals(expectedCanTheseusMove, actualCanTheseusMove);
	}
	
	/*
	 * Can reset game back to original state
	 * @result 
	 */
	@Test
	public void gameConfigurationCanBeReset() {
		
		//Create game data to save
		GameController controller = new GameController(0, false);
		
		controller.moveTheseus(Direction.LEFT);
		controller.moveMinotaur();
		controller.moveMinotaur();
		controller.resetLevel();
		
		IPoint expectedTheseusPoint = new GamePoint(1, 2);
		IPoint actualTheseusPoint = controller.wheresTheseus();
		
		assertTrue(expectedTheseusPoint.equals(actualTheseusPoint));
		
		IPoint expectedMinotaurPoint = new GamePoint(1, 0);
		IPoint actualMinotaurPoint = controller.wheresMinotaur();
		
		assertTrue(expectedMinotaurPoint.equals(actualMinotaurPoint));
	}
	
	//16, 17
	
	/*
	 * Game has a exit character with position on map
	 * @result boolean checking if the theseus character has won.
	 */
	@Test
	public void gameHasExitCharacter() {
		
		//Create game data to save
		GameController controller = new GameController(0, false);
		
		controller.displayGameConfiguration();
		
		IPoint expectedExitPoint = new GamePoint(3, 1);
		IPoint actualExitPoint = controller.wheresExit();
		
		assertTrue(expectedExitPoint.equals(actualExitPoint));
		
	}
	
	/*
	 * Theseus character is able to win the game
	 * @result 
	 */
	@Test
	public void theseusCanWinGame() {
		
		//Create game data to save
		GameController controller = new GameController(0, false);

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
		
		boolean expectedHasTheseusWon = true;
		boolean actualHasTheseusWon = controller.theseusHasWon();
		
		assertEquals(expectedHasTheseusWon, actualHasTheseusWon);
	}
	
	/*
	 * Minotaur character is able to win the game
	 * @result 
	 */
	@Test
	public void minotaurCanWinGame() {
		
		//Create game data to save
		GameController controller = new GameController(0, false);

		controller.moveTheseus(Direction.LEFT);
		controller.moveMinotaur();
		controller.moveMinotaur();
		controller.skipTheseus();
		controller.moveMinotaur();
		
		boolean expectedHasMinotaurWon = true;
		boolean actualHasMinotaurWon = controller.minotaurHasWon();
		
		assertEquals(expectedHasMinotaurWon, actualHasMinotaurWon);
	}
	
	/*
	 * Theseus character is able to lose the game
	 * @result 
	 */
	@Test
	public void theseusCanLoseGame() {
		
		//Create game data
		GameController controller = new GameController(0, false);

		controller.moveTheseus(Direction.LEFT);
		controller.moveMinotaur();
		controller.moveMinotaur();
		controller.skipTheseus();
		controller.moveMinotaur();
		
		boolean expectedHasTheseusLost = true;
		boolean actualHasTheseusLost = controller.minotaurHasWon();
		
		assertEquals(expectedHasTheseusLost, actualHasTheseusLost);
	}
	
	/*
	 * Theseus character's moves are counted
	 * @result 
	 */
	@Test
	public void theseusMovesAreCounted() {
		
		//Create game data
		GameController controller = new GameController(0, false);

		controller.moveTheseus(Direction.LEFT);
		controller.moveTheseus(Direction.RIGHT);
		controller.moveTheseus(Direction.RIGHT);
		controller.moveTheseus(Direction.UP);
		controller.moveTheseus(Direction.UP);
		
		int expectedTheseusMovesCount = 5;
		int actualTheseusMovesCount = controller.amountOfMoves;
		
		assertEquals(expectedTheseusMovesCount, actualTheseusMovesCount);
	}
	
	/*
	 * Minotaur moves twice to every one theseus move
	 * @result 
	 */
	@Test
	public void minotaurMovesTwice() {
		
		//Create game data
		GameController controller = new GameController(0, false);

		controller.moveTheseus(Direction.LEFT);
		controller.moveMinotaur();
		controller.moveMinotaur();

		IPoint expectedMiontaurPoint = new GamePoint(0, 1);
		IPoint actualMinotaurPoint = controller.wheresMinotaur();
		
		assertTrue(expectedMiontaurPoint.equals(actualMinotaurPoint));
	}
	
	/*
	 * Minotaur prioritizes horizontal movement over vertical
	 * @result 
	 */
	@Test
	public void minotaurChoosesHorizontalOverVertical() {
		
		//Create game data
		GameController controller = new GameController(0, false);

		controller.moveTheseus(Direction.LEFT);
		controller.moveMinotaur();
		controller.moveMinotaur();
		controller.moveTheseus(Direction.RIGHT);
		controller.moveMinotaur();

		IPoint expectedMiontaurPoint = new GamePoint(1, 1);
		IPoint actualMinotaurPoint = controller.wheresMinotaur();
		
		assertTrue(expectedMiontaurPoint.equals(actualMinotaurPoint));
	}
	
	/* Load level 1, 10. */
	
}

