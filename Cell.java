
public class Cell{

	// a cell is 3 elements, coordinates x,y followed by a boolean. No need to return an array or make one
	// like in py. this time the object is used instead. may add neighbour info
	int x;
	int y;
	boolean state;

	Cell(int initX, int initY, boolean initState){
		this.x = initX;
		this.y = initY;
		this.state = initState;
		
	}

	// re use in thread
	void updateCell(int pointX, int pointY, boolean inState){
		this.x = pointX;
		this.y = pointY;
		this.state = inState;
	}
	void testPrint(){
		System.out.print("hi. these are my vals: ");
		System.out.println(this.x + " " + this.y + " " + this.state);
	}

	
}
