import java.util.ArrayList;

public class Grid{
	// Builds the grid, checks its values uses Cell class.
	static int width;
	static int height;
	ArrayList<Object> cellGrid;
	ArrayList<Object> baseGrid;
	ArrayList<Object> mousePattern = new ArrayList<Object>();

	Neighbours check;

	Grid(int w, int h){
		this.width = w;
		this.height = h;
		check = new Neighbours(w, h);
		cellGrid = new ArrayList<Object>();
		//buildNewGrid(); build after final mouse clicks, (enter pressed)
	}	
	// build pattern 
	void buildPattern(int x, int y){
		
		mousePattern.add(new Cell(x, y, true)); 
		//System.out.println(mousePattern.size());
	}
	// build grid 
	void buildNewGrid(){
		
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++){
				cellGrid.add( new Cell(x, y, false) );
			}
		}
		// fill mouse true vals
		//System.out.println("adding mouse vals");
		for (int a = 0; a < mousePattern.size(); a++) {
			Cell mtest = (Cell)mousePattern.get(a); 
			for (int b = 0; b < cellGrid.size(); b++) {
				Cell ctest = (Cell)cellGrid.get(b); 
				if (ctest.x == mtest.x && ctest.y == mtest.y){
					cellGrid.set(b, mtest);
					//System.out.println("replaced cell");
				}
			}
		}
		
	}
	void buildBaseGrid(){
		baseGrid = new ArrayList<Object>(); // 
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++){
				baseGrid.add( new Cell(x, y, false) );
			}
		}
	}
	void printGrid(){
		// accessing 
		for (int a = 0; a < cellGrid.size(); a++) {
			Cell cellTest = (Cell)cellGrid.get(a); // cast to object type to access its stuff
			//lol.state = true; //test
			System.out.print(a); cellTest.testPrint();
		}
	}
	
	//same as in py version checkalive cells
	void checkCellState(){
		//System.out.println("now in checkCellState...");
		int alive_neighbours = 0;
		//#
		buildBaseGrid();
		for (int z = 0; z < cellGrid.size(); z++) {
			Cell test = (Cell)cellGrid.get(z); // test should be recently updated cellgrid
			Cell base = (Cell)baseGrid.get(z);
			if(test.state == false){
				alive_neighbours = check.checkNeighbours(cellGrid, test, z); // now a cell method
				if (alive_neighbours == 3){
                	//test.state = true; 
					base.state = true;
				}	
			}
			if(test.state == true){
				alive_neighbours = check.checkNeighbours(cellGrid, test, z);
				// over population or isolation
				if (alive_neighbours > 3 || alive_neighbours < 2){
                	//test.state = false; 
					base.state = false;
				}
				// comfy
				if(alive_neighbours == 2 || alive_neighbours == 3){
					//test.state = true; 
					base.state = true;
				}	
			}
		}
		cellGrid = baseGrid;
		//System.out.println("exiting checkCellState... neighbours: " + alive_neighbours);
	} 
	

	
}
