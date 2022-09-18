package ADA_Assessment2;

import java.util.ArrayList;

public class Greedy extends Subdivision {

    public Greedy(int width, int height) {
        super(width, height);
    }

    // Chooses the best division value with the current piece of land
    @Override
    public ArrayList<Land> calculate() {
        // Call the divide function
        Land startLand = new Land(0, 0, this.width, this.height);
        BestDivision bestDivision = findGreedy(startLand);

        // Assign price
        this.price = bestDivision.price;

        // Show text output
        System.out.println(textDisplay(bestDivision.list, bestDivision.price));

        // Return list
        return bestDivision.list;
    }

    public BestDivision findGreedy(Land startLand) {
        BestDivision bestDivisions = new BestDivision(getLandPrice(startLand), startLand);

        if (startLand.x < this.width && startLand.y < this.height) {
            int best = 0;
            int xTrack = startLand.x;
            int yTrack = startLand.y;

            int valueRangeI = (this.landValues.length > startLand.height
                    ? startLand.height : this.landValues.length);

            int valueRangeJ = (this.landValues[0].length > startLand.width
                    ? startLand.width : this.landValues.length);

            for (int i = 0; i < valueRangeI; i++) {
                for (int j = 0; j < valueRangeJ; j++) {
                    if (this.landValues[i][j] > best) {
                        best = this.landValues[i][j];
                        yTrack = i + 1;
                        xTrack = j + 1;
                    }
                }
            }

            if (xTrack == this.width && yTrack == this.height) {
                return bestDivisions;
            }

            xTrack += startLand.x;
            yTrack += startLand.y;

            int downWidth = 6 > this.width - xTrack ? this.width - xTrack : 6;
            
            Land right = new Land(xTrack, startLand.y, startLand.width - xTrack, startLand.height);
            Land down = new Land(startLand.x, yTrack, downWidth, startLand.height - yTrack);

            //int cost = startLand.height * this.divideCost + xTrack * this.divideCost;

            // Get the best divisions from side A and side B
            BestDivision bestRight = findGreedy(right);
            BestDivision bestDown = findGreedy(down);

            bestDivisions = new BestDivision(bestRight, bestDown);
            
            //bestDivisions.price -= cost;
        }

        return bestDivisions;
    }

    private class BestDivision {

        private int price;
        private ArrayList<Land> list = new ArrayList<>();

        // Constructor used for 1 piece of land
        public BestDivision(int price, Land cell) {
            this.price = price;
            this.list.add(cell);
        }

        // Constructor used for combining two best divisions
        public BestDivision(BestDivision aSide, BestDivision bSide) {
            this.price = aSide.price + bSide.price;
            this.list.addAll(aSide.list);
            this.list.addAll(bSide.list);
        }
    }
}
