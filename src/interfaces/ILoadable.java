package interfaces;

public interface ILoadable {
	
	int setWidthAcross(int widthAcross);
	int setDepthDown(int depthDown );
	
	void addWallAbove(IPoint where);
	void addWallLeft(IPoint where);
	void addTheseus(IPoint where);
	void addMinotaur(IPoint where);
	void addExit(IPoint where);
	
}