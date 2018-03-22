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
	
	public String toString() {
		String returnValue = null;
		
		/* 
		 * switch is for testing purposes
		 * replace with return this.id;
		 */
		switch(this.id) {
			case 'o':
				returnValue = "EMPTY";
				break;
			case '-':
				returnValue = "LINE";
				break;
		}
		
		return returnValue;
	}
}
