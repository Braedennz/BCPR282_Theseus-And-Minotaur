package interfaces;

import enums.Wall;

public interface ISavable {
	int getWidthAcross();
	int getDepthDown();
	
	Wall whatsAbove(IPoint where);
	Wall whatsLeft(IPoint where);
	
	IPoint wheresTheseus();
	IPoint wheresMinotaur();
	IPoint wheresExit();
}