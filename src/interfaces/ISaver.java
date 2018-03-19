package interfaces;

public interface ISaver {
	
	void save (ISavable savable);
	void save (ISavable savable, String fileName);
	void save (ISavable savable, String fileName, String levelName);
	
}