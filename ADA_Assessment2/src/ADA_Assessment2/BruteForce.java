package ADA_Assessment2;

import java.util.ArrayList;

/*
 * @author Kieran
 */
public class BruteForce extends Subdivision {
    
    
    //ERROR WHEN COMBINING THEM?
    
    
    
    
    public BruteForce(int width, int height) {
        super(width, height);
    }

    // Run the class methods for finding the best division and print results?
    @Override
    public void calculate() {
        Cell root = new Cell(0, 0, width, height);
        BestDivision bd = divide(root);
        System.out.println("");
        System.out.println(bd.list.toString());
        System.out.println(display(bd));
        System.out.println(bd.price);
    }
    
    // Find the best division by comparing each comnbination across the cells
    private BestDivision divide(Cell cell) {
        //Current division
        System.out.println(cell);
        BestDivision best = new BestDivision(getCellPrice(cell), cell);
        
        //Find best division
        
        //Vertical
        for (int i = 1; i < cell.width; i++) {
            Cell a = new Cell(cell.x, cell.y, i, cell.height);
            Cell b = new Cell(i, cell.y, cell.width - i, cell.height);
            System.out.println(a + " - " + b);
            int cost = cell.height * divideCost;
            
            //Get side A and side B bests
            BestDivision bestA = divide(a);
            BestDivision bestB = divide(b);
            
            //Check if both prices and division cost added together make it the best
            if ((bestA.price + bestB.price - cost) > best.price) {
                //Combine them and make it the best
                best = new BestDivision(bestA, bestB);
                best.price -= cost;
                System.out.println("new best - " + best);
            }
        }
        
        //Horizontal
        for (int i = 1; i < cell.height; i++) {
            Cell a = new Cell(cell.x, cell.y, cell.width, i);
            Cell b = new Cell(cell.x, i, cell.width, cell.height - 1);
            int cost = cell.width * divideCost;
            
            //Get side A and side B bests
            BestDivision bestA = divide(a);
            BestDivision bestB = divide(b);
            
            //Check if both prices and division cost added together make it the best
            if ((bestA.price + bestB.price - cost) > best.price) {
                //Combine them and make it the best
                best = new BestDivision(bestA, bestB);
                best.price -= cost;
            }
        }
        return best;
    }
    
    // Display the best division? Or every divisio
    private String display(BestDivision bd) {
        int[][] displayArray  = new int[width][height];
        
        int num = 0;
        for (Cell cell : bd.list) {
            num++;
            for (int w = 0; w < cell.width; w++) {
                for (int h = 0; h < cell.height; h++) {
                    displayArray[cell.x + w][cell.y + h] = num;
                }
            }
        }
        
        String toReturn = "";
        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                toReturn += displayArray[w][h] ;
                if (w != width - 1) {
                    toReturn += " ";
                }
            }
            toReturn += "\n";
        }
        return toReturn;
    }
    
    private class BestDivision {
        private int price;
        private ArrayList<Cell> list = new ArrayList<>();

        public BestDivision(int price, Cell cell) {
            this.price = price;
            this.list.add(cell);
        }
        
        public BestDivision(BestDivision aSide, BestDivision bSide) {
            this.price = aSide.price + bSide.price;
            this.list.addAll(aSide.list);
            this.list.addAll(bSide.list);
        }
    }
}