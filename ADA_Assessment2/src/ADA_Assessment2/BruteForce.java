package ADA_Assessment2;

/*
 * @author Kieran
 */
public class BruteForce extends Subdivision {
    
    private int bestPrice;
    
    public BruteForce(int width, int height) {
        super(width, height);
    }

    @Override
    public void calculate() {
        //Cell root = new Cell(0, 0, width, height);
        //int cost = getCellPrice(root);
        //System.out.println(cost);
    }
    /*
    private void divideCell(Cell cell, int cost) {
        
        if ((cell.x == 1) && (cell.y == 1)) { //Cant divide
            //At end
        }
        if (cell.x != 1) { //Divide vertically
            for (int i = 0; i < cell.x; i++) {
                
            }
        }
        if (cell.y == 1) { //Divide horizontally
            
        }
    }*/
}