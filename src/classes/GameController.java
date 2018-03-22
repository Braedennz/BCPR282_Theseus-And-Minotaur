package classes;

import enums.Direction;
import enums.Wall;
import interfaces.IGame;
import interfaces.ILoadable;
import interfaces.IPoint;
import interfaces.ISavable;

public class GameController implements IGame, ILoadable, ISavable {
	
	public String gameTitle;
	
    protected Wall[][] topWalls;
    protected Wall[][] leftWalls;
	
	public int mazeWidthAcross;
	public int mazeDepthDown;

	public GamePoint theseusCharacter;
	public GamePoint minotaurCharacter;
	
	/*
	 * start()
	 * Start's engine for game, e.g. map, characters
	 */
	public void start() {
		System.out.println("Starting Theseus and Minotaur...");
		
		System.out.println("Loading maze level...");
		
		FileLoader xmlLoader = new FileLoader();
		xmlLoader.loadLevel(this);
		
		System.out.println("\n--- Loaded " + this.gameTitle + " ---");

		System.out.println("\n--- Loaded Map ---");
		
		System.out.println("\nTOP WALLS:");
		
		for(int i = 0; i < this.topWalls.length; i++) {
			for(int j = 0; j < this.topWalls[i].length; j++) {
				System.out.print("  " + this.topWalls[i][j].toString() + ",");
			}
			System.out.println("");
		}
		
		System.out.println("-------");
		System.out.println("LEFT WALLS:");
		
		for(int i = 0; i < this.leftWalls.length; i++) {
			for(int j = 0; j < this.leftWalls[i].length; j++) {
				System.out.print("  " + this.leftWalls[i][j].toString() + ",");
			}
			System.out.println("");
		}
		
		System.out.println("\n--- Loading characters ---");
	}
	
	public void stop() {
		System.out.println("Stopping Theseus and Minotaur...");
	}
	
	/*
	 * IGame <<Interface>>
	 * @see interfaces.IGame
	 */

	@Override
	public void moveTheseus(Direction direction) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveMinotaur() {
		// TODO Auto-generated method stub
		
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
	public void setWidthAcross(int widthAcross) {
		this.mazeWidthAcross = widthAcross;
		
		if (this.mazeDepthDown > 0) {
            this.createGrid();
        }
	}

	@Override
	public void setDepthDown(int depthDown) {
		this.mazeDepthDown = depthDown;
		
		if (this.mazeWidthAcross > 0) {
            this.createGrid();
        }
	}
	
	public void createGrid() {
		this.leftWalls = new Wall[this.mazeDepthDown][this.mazeWidthAcross];
        this.topWalls = new Wall[this.mazeDepthDown][this.mazeWidthAcross];

        for (int i = 0; i < this.mazeDepthDown; ++i){
            for (int j = 0; j < this.mazeWidthAcross; j++) {
                this.topWalls[i][j] = Wall.NOTHING;
                this.leftWalls[i][j] = Wall.NOTHING;
            }
        }
	}

	@Override
	public void addWallAbove(IPoint where) {
		this.topWalls[where.getRow()][where.getCol()] = Wall.SOMETHING;
	}

	@Override
	public void addWallLeft(IPoint where) {
        this.leftWalls[where.getRow()][where.getCol()] = Wall.SOMETHING;
	}

	@Override
	public void addTheseus(IPoint characterPoint) {
	}

	@Override
	public void addMinotaur(IPoint characterPoint) {
	}

	@Override
	public void addExit(IPoint where) {
		// TODO Auto-generated method stub
		
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

	@Override
	public Wall whatsAbove(IPoint where) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Wall whatsLeft(IPoint where) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IPoint wheresTheseus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IPoint wheresMinotaur() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IPoint wheresExit() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
