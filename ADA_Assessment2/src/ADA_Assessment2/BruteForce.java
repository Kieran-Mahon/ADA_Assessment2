package ADA_Assessment2;

import java.util.ArrayList;

public class BruteForce extends Subdivision {

    public BruteForce(int width, int height) {
        super(width, height);
    }

    @Override
    public ArrayList<Land> calculate() {
        //Call the divide function
        Land startLand = new Land(0, 0, this.width, this.height);
        BestDivision bestDivision = divide(startLand);

        //Assign price
        this.price = bestDivision.price;

        //Show text output
        System.out.println(textDisplay(bestDivision.list, bestDivision.price));

        //Return list
        return bestDivision.list;
    }

    //Find best division then pass it up
    private BestDivision divide(Land land) {
        //Set best as the current division
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
