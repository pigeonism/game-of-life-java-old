import java.util.ArrayList;

public class Neighbours{
	int width;
	int height;
	Neighbours(int w, int h){
		width = w;
		height = h;
	}
	int checkNeighbours(ArrayList<Object> checkCells, Cell testCell, int index){
		int aliveN = 0;
		// width-1 because 10 is 0-9. need to stop at 8
		if(testCell.x > 0 && testCell.x < width-1 && testCell.y > 0 && testCell.y < height-1){ 
			//System.out.println("now:"+testCell.x + " : " + testCell.y); // we are special , inside a border
			Cell right = (Cell)checkCells.get(index+1);
			Cell left = (Cell)checkCells.get(index-1);
			Cell above = (Cell)checkCells.get(index-width);
			Cell aboveLeft = (Cell)checkCells.get(index-width-1);
			Cell aboveRight = (Cell)checkCells.get(index-width+1);
			Cell below = (Cell)checkCells.get(index+width);
			Cell belowRight = (Cell)checkCells.get(index+width+1);
			Cell belowLeft = (Cell)checkCells.get(index+width-1);
			/*if(testCell.x == 2 && testCell.y == 4){ //test1
				if(testCell.state  == left.state){
					System.out.println("match");
				}
			}*/
			// weeeeee
			if (right.state == true){
				aliveN+=1;
				//System.out.println("alive");
			}
			if (left.state == true){
				aliveN+=1;
				//System.out.println("alive");
			}
			if (above.state == true){
				aliveN+=1;
				//System.out.println("alive");
			}
			if (aboveLeft.state == true){
				aliveN+=1;
				//System.out.println("alive");
			}
			if (aboveRight.state == true){
				aliveN+=1;
				//System.out.println("alive");
			}
			if (below.state == true){
				aliveN+=1;
				//System.out.println("alive");
			}
			if (belowRight.state == true){
				aliveN+=1;
				//System.out.println("alive");
			}
			if (belowLeft.state == true){
				aliveN+=1;
				//System.out.println("alive");
			}
		}
		//System.out.println("alive cells in Neighbours: " + aliveN);
		return aliveN; 
	}


}
