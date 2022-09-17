package ADA_Assessment2;

import java.util.ArrayList;

public class Greedy extends Subdivision {

    public Greedy(int width, int height) {
        super(width, height);
    }

    // chooses the best division value with the current piece of land
    @Override
    public ArrayList<Land> calculate() {
        //Call the divide function
        Land startLand = new Land(0, 0, this.width, this.height);
        BestDivision bestDivision = findGreedy(startLand);
        
        //Assign price
        this.price = bestDivision.price;
        
        //Show text output
        System.out.println(textDisplay(bestDivision.list, bestDivision.price));
        
        //Return list
        return bestDivision.list;
    }
    
    public BestDivision findGreedy(Land startLand) {
        BestDivision bestDivision = new BestDivision(getLandPrice(startLand), startLand);
        int best = 0;
        int xTrack = 0;
        int yTrack = 0;
        
        for (int i = 0; i < this.landValues.length; i++)
            for (int j = 0; j < this.landValues[0].length; j++)
                if (this.landValues[i][j] > best) {
                    best = this.landValues[i][j];
                    xTrack = i;
                    yTrack = j;
                }
            
            Land a = new Land(0, 0, xTrack, yTrack);
            Land b = new Land(xTrack, yTrack, startLand.width-xTrack, startLand.height-yTrack);
        
            //Get the best divisions from side A and side B
            BestDivision bestA = findGreedy(a);
            BestDivision bestB = findGreedy(b);
        
        int cost = startLand.height * this.divideCost;
    }

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