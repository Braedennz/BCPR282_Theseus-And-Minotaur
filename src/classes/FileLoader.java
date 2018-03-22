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

public class FileLoader implements ILoader {
	
	/*
	 * loadLevel()
	 * Load the level and convert into seperate arrays to be used by game
	 */
    
	@Override
	public void loadLevel(ILoadable gameController) {
		
		Document xmlDoc = this.getXmlFile();
		Element mazeItem = this.getMazeItems(xmlDoc);
		
		//set game title
		gameController.setGameTitle(mazeItem.getElementsByTagName("name").item(0).getTextContent());
		
		//get horizontal and vertical row of walls
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
	}
	
	/*
	 * getXmlFile()
	 * Load the XML file and return it
	 * TODO : Better way of getting directory for file
	 */

	protected Document getXmlFile() {
		//File fXmlFile = new File("E:/Dropbox/ARA/2018, February, Semester One/Java/TheseusAndMinotaur/levels.xml");
		File fXmlFile = new File("/Users/AMacADay/Dropbox/ARA/2018, February, Semester One/Java/BCPR282_Theseus-And-Minotaur/levels.xml");
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		
		try {
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			
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
	 * TODO : support for multiple levels (nList.getLength())
	 */
	
	protected Element getMazeItems(Document xmlDoc) {
		NodeList nList = xmlDoc.getElementsByTagName("maze");
		
		//Add loop with nList.getLength(), find specific maze in xml list
		Node nNode = nList.item(0);
		
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {

			Element eElement = (Element) nNode;
			
			return eElement;
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
}
