package enums;

public enum Wall {
	
	/* 
	 * MAP LAYOUT
	 * ooo- (top-line,top-line,top-line,empty)
	 * o-o- (empty,top-line,empty,top-line)
	 */
	
	NOTHING('o'), //empty
	SOMETHING('-'); //top-line
	
	private final char id;
	
	Wall(char id) {
		this.id = id;
	}
	
	public char getValue() { 
		return id; 
	}
	
	public String toString(String leftOrTop) {
		String returnValue = null;
		
		/* 
		 * switch is for testing purposes
		 * replace with return this.id;
		 */
		switch(this.id) {
			case 'o':
				returnValue = "o";
				break;
			case '-':
				if(leftOrTop == "top") {
					returnValue = "-";
				} else {

					returnValue = "|";
				}
				break;
		}
		
		return returnValue;
	}
}
