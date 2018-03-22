package interfaces;

public interface ILoadable {
	
	void setWidthAcross(int widthAcross);
	void setDepthDown(int depthDown);
	
	void addWallAbove(IPoint where);
	void addWallLeft(IPoint where);
	void addTheseus(IPoint where);
	void addMinotaur(IPoint where);
	void addExit(IPoint where);
	
	void setGameTitle(String gameTitle);
	
}