package classes;

import enums.Direction;
import enums.Wall;
import interfaces.IGame;
import interfaces.ILoadable;
import interfaces.IPoint;
import interfaces.ISavable;

public class GameController implements IGame, ILoadable, ISavable {
	
	public GameController() {
		
	}
	
	public void start() {
		System.out.println("Starting Theseus and Minotaur...");
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
	public int setWidthAcross(int widthAcross) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int setDepthDown(int depthDown) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void addWallAbove(IPoint where) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addWallLeft(IPoint where) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addTheseus(IPoint where) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addMinotaur(IPoint where) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addExit(IPoint where) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getWidthAcross() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getDepthDown() {
		// TODO Auto-generated method stub
		return 0;
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
