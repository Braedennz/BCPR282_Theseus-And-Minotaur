package classes;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import enums.Wall;
import interfaces.ILoadable;
import interfaces.ILoader;
import interfaces.ISavable;
import interfaces.ISaver;

public class FileLoader implements ILoader, ISaver {
	
	private static final String FILE_DIR = "E:/Dropbox/ARA/2018, February, Semester One/Java/BCPR282_Theseus-And-Minotaur/";
	//private static final String FILE_DIR = "\"/Users/AMacADay/Dropbox/ARA/2018, February, Semester One/Java/BCPR282_Theseus-And-Minotaur/";

	private static final String LEVELS_FILE = "levels.xml";
	private static final String SAVES_FILE = "save.xml";
	
	/*
	 * loadLevel()
	 * Load the level and convert into seperate arrays to be used by game
	 */
    
	@Override
	public boolean loadLevel(ILoadable gameController, int levelNumber) {
		Document levelDoc = this.getXmlFile(FILE_DIR + LEVELS_FILE);
		Element mazeItem = this.getMazeItems(levelDoc, levelNumber);
		
		if(mazeItem != null) {
			
			//set game title
			gameController.setGameTitle(mazeItem.getElementsByTagName("name").item(0).getTextContent());
			
			//get horizontal and vertical row of walls from xml
			NodeList horizontalWalls = this.getMazeWalls(mazeItem, "horizontal");
	        NodeList verticalWalls = this.getMazeWalls(mazeItem, "vertical");
			
	        //Set the amount of horizontal rows maze will have
			gameController.setWidthAcross(horizontalWalls.getLength());
			
	        //Set the amount of vertical rows maze will have
			gameController.setDepthDown(verticalWalls.item(0).getTextContent().length());
			
			//Add all the walls from NodeList to multi level array by wall per row
			this.addWallsToGame(gameController, "verticalAdd", verticalWalls);
			this.addWallsToGame(gameController, "horizontalAdd", horizontalWalls);
			
			//Create character position
			gameController.addTheseus(this.addCharacterToGame("theseus", mazeItem));
			gameController.addMinotaur(this.addCharacterToGame("minotaur", mazeItem));
			gameController.addExit(this.addCharacterToGame("exit", mazeItem));
			
			return true;
		}
		return false;
	}
	
	/*
	 * getXmlFile()
	 * Load the XML file and return it
	 * TODO : Better way of getting directory for file
	 */

	protected Document getXmlFile(String fileUrl) {
		File xmlFile = new File(fileUrl);
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		
		try {
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			
            doc.getDocumentElement().normalize();
            
            return doc;
		} 
        catch (Exception e) {
            e.printStackTrace();
        }
		return null;
	}
	
	/*
	 * getMazeItems(Document xmlDoc)
	 * Get the single maze item and convert it into a readable element
	 */
	
	protected Element getMazeItems(Document xmlDoc, int levelNumber) {
		NodeList mazeList = xmlDoc.getElementsByTagName("maze");
		
		Node mazeItem = mazeList.item(levelNumber);
		
		if(mazeItem != null) {
			if (mazeItem.getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) mazeItem;
				
				return eElement;
			}
		}
		
		return null;
	}
	
	/*
	 * getMazeWalls(Element mazeItem, String direction (horizontal or vertical))
	 * Get's horizontal and vertical list of walls from Element
	 */
	
	protected NodeList getMazeWalls(Element mazeItem, String direction) {
		Element walls = (Element) mazeItem.getElementsByTagName("walls").item(0);
		Element directedWalls = (Element) walls.getElementsByTagName(direction).item(0);
        
        return directedWalls.getElementsByTagName("row");
	}
	
	/*
	 * addWallsToGame(ILoadable gameController, String addDirection (horizontalAdd or verticalAdd), NodeList wallItems)
	 * Adds the walls to array in GameController to be later rendered into game
	 */
	protected void addWallsToGame(ILoadable gameController, String addDirection, NodeList wallItems) {
		for(int i = 0; i < wallItems.getLength(); i++) {
			String item = wallItems.item(i).getTextContent();
			
			 for (int f = 0; f < item.length(); f++) {
				 if (item.charAt(f) == Wall.SOMETHING.getValue()) {
					 switch(addDirection) {
					 	case "horizontalAdd":
					 		gameController.addWallAbove(new GamePoint(i, f));
					 		break;
					 	case "verticalAdd":
					 		gameController.addWallLeft(new GamePoint(i, f));
					 		break;
					 }
				 }
			 }
		}
	}

	/*
	 * addCharacterToGame(ILoadable gameController, String characterName (theseus or minotaur), Element mazeItem)
	 * Returns coordinates for character from XML Doc
	 * Return: GamePoint(x,y)
	 */
	protected GamePoint addCharacterToGame(String characterName, Element mazeItem) {
		Element characterItems = (Element) mazeItem.getElementsByTagName("positions").item(0);
		Element actualCharacterItem = (Element) characterItems.getElementsByTagName(characterName).item(0);
		
		int x =  Integer.valueOf(actualCharacterItem.getElementsByTagName("x").item(0).getTextContent());
		int y =  Integer.valueOf(actualCharacterItem.getElementsByTagName("y").item(0).getTextContent());
		
		return new GamePoint(x, y);
	}

	/*
	 * addCharacterToGame(GameController game)
	 * Loads a xml file that contains save configuration for previous game, 
	 * sets game config according to this e.g. theseus, minotaur current position.
	 * Return: true, false
	 */
	
	@Override
	public boolean loadSave(GameController game) {
		Document saveDoc = this.getXmlFile(FILE_DIR + SAVES_FILE);

		Element saveNode = (Element) saveDoc.getElementsByTagName("save").item(0);
		
        Element characterLevel = (Element) saveNode.getElementsByTagName("level").item(0);
        Element characterPositions = (Element) saveNode.getElementsByTagName("positions").item(0);
        
        Element theseusPositions = (Element) characterPositions.getElementsByTagName("theseus").item(0);
        Element minotaurPositions = (Element) saveNode.getElementsByTagName("minotaur").item(0);
        
        Element theseusPositionsX = (Element) theseusPositions.getElementsByTagName("x").item(0);
        Element theseusPositionsY = (Element) theseusPositions.getElementsByTagName("y").item(0);
        
        Element minotaurPositionsX = (Element) minotaurPositions.getElementsByTagName("x").item(0);
        Element minotaurPositionsY = (Element) minotaurPositions.getElementsByTagName("y").item(0);
        
        if(this.loadLevel(game, Integer.parseInt(characterLevel.getTextContent()))) {
        	game.theseusCharacter.setLocation(Integer.parseInt(theseusPositionsX.getTextContent()), Integer.parseInt(theseusPositionsY.getTextContent()));
        	game.minotaurCharacter.setLocation(Integer.parseInt(minotaurPositionsX.getTextContent()), Integer.parseInt(minotaurPositionsY.getTextContent()));
        }
        
        return true;
	}

	@Override
	public void save(ISavable savable) {
		
	}
}
