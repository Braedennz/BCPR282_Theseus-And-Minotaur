package classes;

import enums.Direction;
import enums.Wall;
import interfaces.IGame;
import interfaces.ILoadable;
import interfaces.IPoint;
import interfaces.ISavable;

public class GameController implements IGame, ILoadable, ISavable {
	
	public boolean readyToPlay = false;
	
	public String gameTitle;
	public int gameLevel;
	
    protected Wall[][] topWalls;
    protected Wall[][] leftWalls;
	
	public int mazeWidthAcross;
	public int mazeDepthDown;

	public GamePoint theseusCharacter;
	public GamePoint minotaurCharacter;
	public GamePoint exitCharacter;
	
	public int amountOfMoves;
	
	/*
	 * start()
	 * Start's engine for game, e.g. map, characters
	 */
	public GameController(int levelNumber, boolean loadFromSave) {
		FileLoader xmlLoader = new FileLoader();
		
		if(loadFromSave) {
			if(xmlLoader.loadSave(this)) {
				System.out.println("\n--- Loaded " + this.gameTitle + " from save ---");
				
				this.readyToPlay = true;
				
			} else {
				System.out.println("Failed to load level from save");
			}
		} else {
			if(xmlLoader.loadLevel(this, levelNumber)) {
				System.out.println("\n--- Loaded " + this.gameTitle + " ---");
				
				this.readyToPlay = true;
			} else {
				System.out.println("Failed to load level ("+ levelNumber +"), out of bounds");
			}
		}
	}
	
	public void displayGameConfiguration() {
		System.out.println("\nTOP WALLS:");
		
		for(int i = 0; i < this.topWalls.length; i++) {
			for(int j = 0; j < this.topWalls[i].length; j++) {
				System.out.print("  " + this.topWalls[i][j].toString("top"));
			}
			System.out.println("");
		}
		
		System.out.println("-------");
		System.out.println("LEFT WALLS:");
		
		for(int i = 0; i < this.leftWalls.length; i++) {
			for(int j = 0; j < this.leftWalls[i].length; j++) {
				System.out.print("  " + this.leftWalls[i][j].toString("left"));
			}
			System.out.println("");
		}
		
		System.out.println("\n--- Loaded characters ---");
		System.out.println("\nTheseus loaded, position: " + this.theseusCharacter.toString());
		System.out.println("Minotaur loaded, position: " + this.minotaurCharacter.toString());
		System.out.println("Exit loaded, position: " + this.exitCharacter.toString());
	}
	
	/*
	 * IGame <<Interface>>
	 * @see interfaces.IGame
	 */

	@Override
	public boolean moveTheseus(Direction direction) {
		boolean canMove = this.canMove(direction, theseusCharacter);
		
		//Boolean to check if character can move
		if(canMove) {
			//Move theseus character location
			theseusCharacter.moveLocation(direction.x, direction.y);
			//Increment move counter for theseus
			this.incrementTheseusMove();
			
			System.out.println("POSITION AFTER THESEUS MOVES "+ direction.name() +":" + theseusCharacter.toString());
		} else {
			System.out.println("Can't move " + direction.toString() + ", character blocked.");
		}
		
		return canMove;
	}
	
	@Override
	public void skipTheseus() {
		this.incrementTheseusMove();
		
		System.out.println("Skipped theseus turn, position is: " + theseusCharacter.toString());
	}

	@Override
	public void resetLevel() {
		this.readyToPlay = false;

		FileLoader xmlLoader = new FileLoader();
		
		if(xmlLoader.loadLevel(this, this.gameLevel)) {
			System.out.println("\n--- Re-Loaded " + this.gameTitle + " ---");
			
			this.readyToPlay = true;
		} else {
			System.out.println("Failed to load level ("+ this.gameLevel +"), out of bounds");
		}
	}
	
	@Override
	public void incrementTheseusMove() {
		this.amountOfMoves += 1;
	}

	@Override
	public void moveMinotaur() {
		int differenceBetweenX = minotaurCharacter.getX() - theseusCharacter.getX();
		int differenceBetweenY = minotaurCharacter.getY() - theseusCharacter.getY();
		
		System.out.println("MINOTAUR before movement " + minotaurCharacter.toString());
		
		if(differenceBetweenX > 0 && this.canMove(Direction.LEFT, minotaurCharacter)) {
			minotaurCharacter.moveLocation(Direction.LEFT.x, Direction.LEFT.y);
		} else if(differenceBetweenX < 0 && this.canMove(Direction.RIGHT, minotaurCharacter)) {
			minotaurCharacter.moveLocation(Direction.RIGHT.x, Direction.RIGHT.y);
		} else if(differenceBetweenY > 0 && this.canMove(Direction.UP, minotaurCharacter)) {
			minotaurCharacter.moveLocation(Direction.UP.x, Direction.UP.y);
		} else if(differenceBetweenY < 0 && this.canMove(Direction.DOWN, minotaurCharacter)) {
			minotaurCharacter.moveLocation(Direction.DOWN.x, Direction.DOWN.y);
		} else {
			System.out.println("Can't move minotaur blocked.");
		}
		
		System.out.println("MOVED MINOTAUR " + minotaurCharacter.toString());
	}

	@Override
	public boolean theseusHasWon() {
		return theseusCharacter.equals(exitCharacter);
	}

	@Override
	public boolean minotaurHasWon() {
		return minotaurCharacter.equals(theseusCharacter);
	}

	/*
	 * TODO: Clean up case, don't need so many function calls.
	 */
	@Override
	public boolean canMove(Direction direction, GamePoint character) {
		int[] nextLocation = character.nextLocation(direction.x, direction.y);
		
		switch(direction) {
			case UP:
				if(this.whatsAbove(new GamePoint(nextLocation[0], nextLocation[1] + 1)) != Wall.SOMETHING) {
					return true;
				}
				break;
			case DOWN:
				if(this.whatsAbove(new GamePoint(nextLocation[0], nextLocation[1])) != Wall.SOMETHING) {
					return true;
				}
				break;
			case LEFT:
				if(this.whatsLeft(new GamePoint(nextLocation[0] + 1, nextLocation[1])) != Wall.SOMETHING) {
					return true;
				}
				break;
			case RIGHT:
				if(this.whatsLeft(new GamePoint(nextLocation[0], nextLocation[1])) != Wall.SOMETHING) {
					return true;
				}
				break;
		}
		
		return false;
	}
	
	/*
	 * ILoadable <<Interface>>
	 * @see interfaces.ILoadable
	 */

	@Override
	public void setGameTitle(String gameTitle) {
		this.gameTitle = gameTitle;
	}

	@Override
	public void setGameLevel(int gameLevel) {
		this.gameLevel = gameLevel;
	}

	@Override
	public void setWidthAcross(int widthAcross) {
		this.mazeWidthAcross = widthAcross;
		
		/* Call reset of grid because size has changed */
		if (this.mazeDepthDown > 0) {
            this.createGrid();
        }
	}

	@Override
	public void setDepthDown(int depthDown) {
		this.mazeDepthDown = depthDown;

		/* Call reset of grid because size has changed */
		if (this.mazeWidthAcross > 0) {
            this.createGrid();
        }
	}
	
	public void createGrid() {
		//create multi-dimensional array for left and top walls, using sizing
		this.leftWalls = new Wall[this.mazeWidthAcross][this.mazeDepthDown];
        this.topWalls = new Wall[this.mazeWidthAcross][this.mazeDepthDown];

        for (int i = 0; i < this.mazeWidthAcross; ++i){
            for (int j = 0; j < this.mazeDepthDown; j++) {
                this.topWalls[i][j] = Wall.NOTHING;
                this.leftWalls[i][j] = Wall.NOTHING;
            }
        }
	}

	@Override
	public void addWallAbove(IPoint where) {
		this.topWalls[where.getX()][where.getY()] = Wall.SOMETHING;
	}

	@Override
	public void addWallLeft(IPoint where) {
        this.leftWalls[where.getX()][where.getY()] = Wall.SOMETHING;
	}

	@Override
	public void addTheseus(IPoint characterPoint) {
		this.theseusCharacter = new GamePoint(characterPoint);
	}

	@Override
	public void addMinotaur(IPoint characterPoint) {
		this.minotaurCharacter = new GamePoint(characterPoint);
	}

	@Override
	public void addExit(IPoint characterPoint) {
		this.exitCharacter = new GamePoint(characterPoint);
	}
	
	@Override
	public int getWidthAcross() {
		return this.mazeWidthAcross;
	}

	@Override
	public int getDepthDown() {
		return this.mazeDepthDown;
	}
	
	/*
	 * ISavable <<Interface>>
	 * @see interfaces.ISavable
	 */

	/*
	 * TODO: Make a boundary for -1 values, ban em.
	 */
	@Override
	public Wall whatsAbove(IPoint where) {
		//Checks if character is in bounds
		if(where.getY() == -1 
				|| where.getY() >= this.getDepthDown()) {
			return Wall.SOMETHING;
		}
		
		return topWalls[where.getY()][where.getX()];
	}

	/*
	 * TODO: Make a boundary for -1 values, ban em.
	 */
	@Override
	public Wall whatsLeft(IPoint where) {
		//Checks if character is in bounds
		if(where.getX() == -1 
				|| where.getX() >= this.getWidthAcross() ) {
			return Wall.SOMETHING;
		}
		
		return leftWalls[where.getY()][where.getX()];
	}

	@Override
	public IPoint wheresTheseus() {
		return theseusCharacter;
	}

	@Override
	public IPoint wheresMinotaur() {
		return minotaurCharacter;
	}

	@Override
	public IPoint wheresExit() {
		return exitCharacter;
	}
	
}
