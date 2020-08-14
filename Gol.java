// wayne w 2015
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Event;

public class Gol extends java.applet.Applet implements Runnable{
	// calls the helper classes
	int time = 1000;
	Thread runner;
	Grid grid;
	boolean enterPressed = false;
	boolean checkCells = false;
	boolean drawSelected = true;
	boolean buildNew = true;
	// temporary sqrs to show user pattern, remove when enterPressed
	final int MAXSQUARES = 10;
	int xsqr[] = new int[MAXSQUARES];
	int ysqr[] = new int[MAXSQUARES];
	int currentSqrs = 0;

	// ##### all Drawing and thread methods ######
	public void init() {
		setBackground(Color.white);
		int width = 59; // not graphics width height, grid will be scaled up (300,300 = 29,29)
		int height = 39; // so this is html width/height divided by 10then -1
		grid = new Grid(width, height);


	}
	public void start() {
		if (runner == null) {
			runner = new Thread(this);
			runner.start();
		}
	}
	public void stop() {
		if (runner != null) {
			runner.stop();
			runner = null;
		}
	}
	public void run() {
		
		while (true) {
			// when grid is made after mouse clicks are done: grid.checkCellState();
			//System.out.println("cool");
			if (enterPressed == true){
				if(buildNew == true){
					grid.buildNewGrid();
					buildNew = false;
				}
				//grid.printGrid();
				checkCells = true;
				drawSelected = false; // now draw checked cells
				
			}
			if (checkCells == true){ // check cell neighbours now cells have been chosen
				grid.checkCellState();
			}
			repaint();
			try { Thread.sleep(300); }
			catch (InterruptedException e) { }
		}
		
	}
	public boolean keyDown(Event evt, int key) {
		switch (key) {
		case Event.ENTER:		
		//case (10):
			enterPressed = true;
			//System.out.println("Enter pressed");
			break;
		case Event.UP:
			System.out.println("Up pressed");
			break;
		default:
			System.out.println("Something else pressed");
		}
		repaint();
		return true;
	}
	// coordinates are scaled down for first grid build
	public boolean mouseDown(Event evt, int x, int y) {
		//System.out.println(x+" "+y);
		// Integer Div by 10, call update grid
		//System.out.println((x/10) + " " + (y/10) );
		if (enterPressed == false){
			
			if (currentSqrs < MAXSQUARES){
				grid.buildPattern((x/10), (y/10)); //scaled down for grid
				addSqr((x/10) * 10, (y/10) *10 );// normal scale for screen rounded
			}	
			
		}
					    
		
		
		return true;
	}
	void addSqr(int x,int y) {
		xsqr[currentSqrs] = x;
		ysqr[currentSqrs] = y;
		currentSqrs++;
		repaint();
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.black);

		if(drawSelected == true){
			for (int i = 0; i < currentSqrs; i++) {
				g.fillRect(xsqr[i] , ysqr[i] ,10,10);
			}
		}
		if(drawSelected == false){
			for (int z = 0; z < grid.cellGrid.size(); z++) {
				Cell cellDraw = (Cell)grid.cellGrid.get(z); 
				if(cellDraw.state == true){
					g.fillRect(cellDraw.x * 10, cellDraw.y * 10, 10,10);
				}
			}
		}
	}

}
