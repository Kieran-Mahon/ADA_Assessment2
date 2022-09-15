package ADA_Assessment2;

import java.util.ArrayList;

/*
 * @author Kieran
 */
public class BruteForce extends Subdivision {
    
    public BruteForce(int width, int height) {
        super(width, height);
    }

    @Override
    public void calculate() {
        Land startLand = new Land(0, 0, this.width, this.height);
        BestDivision bestDivision = divide(startLand);
        System.out.println(textDisplay(bestDivision));
    }
    
    //Find best division then pass it up
    private BestDivision divide(Land land) {
        //Set best as the current division (no division)
        BestDivision best = new BestDivision(getLandPrice(land), land);
        
        //Vertical
        for (int i = 1; i < land.width; i++) {
            //Divide the land into 2 parts (A and B)
            Land a = new Land(land.x, land.y, i, land.height);
            Land b = new Land(land.x + i, land.y, land.width - i, land.height);
            
            //Calculate the cost of the division
            int cost = land.height * this.divideCost;
            
            //Get the best divisions from side A and side B
            BestDivision bestA = divide(a);
            BestDivision bestB = divide(b);
            
            //Check if both prices and division cost added together make it the best
            if ((bestA.price + bestB.price - cost) > best.price) {
                //Combine them and make it the new best
                best = new BestDivision(bestA, bestB);
                //Remove the cost
                best.price -= cost;
            }
        }
        
        
        //Horizontal
        for (int i = 1; i < land.height; i++) {
            //Divide the land into 2 parts (A and B)
            Land a = new Land(land.x, land.y, land.width, i);
            Land b = new Land(land.x, land.y + i, land.width, land.height - i);
            
            //Calculate the cost of the division
            int cost = land.width * this.divideCost;
            
            //Get the best divisions from side A and side B
            BestDivision bestA = divide(a);
            BestDivision bestB = divide(b);
            
            //Check if both prices and division cost added together make it the best
            if ((bestA.price + bestB.price - cost) > best.price) {
                //Combine them and make it the new best
                best = new BestDivision(bestA, bestB);
                //Remove the cost
                best.price -= cost;
            }
        }
        return best;
    }
    
    //Text version of the best division
    private String textDisplay(BestDivision bestDivision) {
        //Add list to display
        String toReturn = bestDivision.list.toString() + "\n";
        
        //Convert the land into an array format
        int[][] displayArray  = new int[width][height];
        int num = 0;
        for (Land land : bestDivision.list) {
            num++;
            for (int w = 0; w < land.width; w++) {
                for (int h = 0; h < land.height; h++) {
                    displayArray[land.x + w][land.y + h] = num;
                }
            }
        }
        
        //Convert array format into string format
        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                toReturn += displayArray[w][h] ;
                if (w != width - 1) {
                    toReturn += " ";
                }
            }
            toReturn += "\n";
        }
        
        //Add price
        toReturn += "\nPrice: $" + bestDivision.price;
        
        //Return the string
        return toReturn;
    }
    
    //Class which holds division information
    private class BestDivision {
        private int price;
        private ArrayList<Land> list = new ArrayList<>();
        
        //Constructor used for 1 piece of land
        public BestDivision(int price, Land cell) {
            this.price = price;
            this.list.add(cell);
        }
        
        //Constructor used for combining two best divisions
        public BestDivision(BestDivision aSide, BestDivision bSide) {
            this.price = aSide.price + bSide.price;
            this.list.addAll(aSide.list);
            this.list.addAll(bSide.list);
        }
    }
}