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

    private BestDivision findGreedy(Land land) {
        // Set best as the current division (no division)
        BestDivision best = new BestDivision(getLandPrice(land), land);

        // Vertical
        for (int i = 1; i < land.width; i++) {
            //Divide the land into 2 parts (A and B)
            Land a = new Land(land.x, land.y, i, land.height);
            Land b = new Land(land.x + i, land.y, land.width - i, land.height);

            // Calculate the cost of the division
            int cost = land.height * this.divideCost;

            // Figure out which one costs the most
            Land mostExpensiveLand;
            Land otherLand;
            if (getLandPrice(a) > getLandPrice(b)) {
                // A costs more
                mostExpensiveLand = a;
                otherLand = b;
            }
            else {
                // B costs more (or is equal)
                mostExpensiveLand = b;
                otherLand = a;
            }

            // Add A and B to a best division for easier calculation
            // Add the other land and its price
            BestDivision divison = new BestDivision(getLandPrice(otherLand), otherLand);
            divison.list.add(mostExpensiveLand); //Add other land
            divison.price += getLandPrice(mostExpensiveLand); //Add most expensive price
            divison.price -= cost; //Remove cost

            // Check if this divide better than the current best
            if ((divison.price) > best.price) {
                best = divison;
            }
        }

        // Horizontal
        for (int i = 1; i < land.height; i++) {
            //Divide the land into 2 parts (A and B)
            Land a = new Land(land.x, land.y, land.width, i);
            Land b = new Land(land.x, land.y + i, land.width, land.height - i);

            // Calculate the cost of the division
            int cost = land.width * this.divideCost;

            // Figure out which one costs the most
            Land mostExpensiveLand;
            Land otherLand;

            if (getLandPrice(a) > getLandPrice(b)) {
                //A costs more
                mostExpensiveLand = a;
                otherLand = b;
            }
            else {
                //B costs more (or is equal)
                mostExpensiveLand = b;
                otherLand = a;
            }

            // Add A and B to a best division for easier calculation
            // Add the other land and its price
            BestDivision divison = new BestDivision(getLandPrice(otherLand), otherLand);
            divison.list.add(mostExpensiveLand); //Add other land
            divison.price += getLandPrice(mostExpensiveLand); //Add most expensive price
            divison.price -= cost; //Remove cost

            // Check if this divide is the best
            if ((divison.price) > best.price) {
                best = divison;
            }
        }

        // If the size of the list is NOT 1 then it is NOT the original land
        // Go down the most expensive division
        if (best.list.size() != 1) {
            // Index 0 means the OTHER land which is not touched
            BestDivision tempBest = new BestDivision(best.price, best.list.get(0));

            // Index 1 means the most expensive land which gets checked
            BestDivision divisionFound = findGreedy(best.list.get(1));

            tempBest.list.addAll(divisionFound.list);
            tempBest.price += divisionFound.price - getLandPrice(best.list.get(1));

            // Set best division
            best = tempBest;
        }

        return best;
    }

//    public BestDivision findGreedy(Land startLand) {
//        BestDivision bestDivisions = new BestDivision(getLandPrice(startLand), startLand);
//
//        if (startLand.x < this.width && startLand.y < this.height) {
//            int best = 0;
//            int xTrack = startLand.x;
//            int yTrack = startLand.y;
//
//            int valueRangeI = (this.landValues.length > startLand.height
//                    ? startLand.height : this.landValues.length);
//
//            int valueRangeJ = (this.landValues[0].length > startLand.width
//                    ? startLand.width : this.landValues.length);
//
//            for (int i = 0; i < valueRangeI; i++) {
//                for (int j = 0; j < valueRangeJ; j++) {
//                    if (this.landValues[i][j] > best) {
//                        best = this.landValues[i][j];
//                        yTrack = i + 1;
//                        xTrack = j + 1;
//                    }
//                }
//            }
//
//            if (xTrack == this.width && yTrack == this.height) {
//                return bestDivisions;
//            }
//
//            xTrack += startLand.x;
//            yTrack += startLand.y;
//
//            int downWidth = 6 > this.width - xTrack ? this.width - xTrack : 6;
//            
//            Land right = new Land(xTrack, startLand.y, startLand.width - xTrack, startLand.height);
//            Land down = new Land(startLand.x, yTrack, downWidth, startLand.height - yTrack);
//
//            //int cost = startLand.height * this.divideCost + xTrack * this.divideCost;
//
//            // Get the best divisions from side A and side B
//            BestDivision bestRight = findGreedy(right);
//            BestDivision bestDown = findGreedyDown(down);
//
//            bestDivisions = new BestDivision(bestRight, bestDown);
//            
//            //bestDivisions.price -= cost;
//        }
//
//        return bestDivisions;
//    }
//
//    private BestDivision findGreedyDown(Land startLand) {
//        BestDivision bestDivisions = new BestDivision(getLandPrice(startLand), startLand);
//
//        if (startLand.x < this.width && startLand.y < this.height) {
//            int best = 0;
//            int xTrack = startLand.x;
//            int yTrack = startLand.y;
//
//            int valueRangeI = (this.landValues.length > startLand.height
//                    ? startLand.height : this.landValues.length);
//
//            int valueRangeJ = (this.landValues[0].length > startLand.width
//                    ? startLand.width : this.landValues.length);
//
//            for (int i = 0; i < valueRangeI; i++) {
//                for (int j = 0; j < valueRangeJ; j++) {
//                    if (this.landValues[i][j] > best) {
//                        best = this.landValues[i][j];
//                        yTrack = i + 1;
//                        xTrack = j + 1;
//                    }
//                }
//            }
//
//            if (xTrack == this.width && yTrack == this.height) {
//                return bestDivisions;
//            }
//
//            xTrack += startLand.x;
//            yTrack += startLand.y;
//
//            int downWidth = 6 > this.width - xTrack ? this.width - xTrack : 6;
//            
//
//            Land down = new Land(startLand.x, yTrack, downWidth, startLand.height - yTrack);
//
//            //int cost = startLand.height * this.divideCost + xTrack * this.divideCost;
//
//            // Continue down
//            BestDivision bestDown = findGreedyDown(down);
//
//            bestDivisions = new BestDivision(bestDivisions, bestDown);
//            
//            //bestDivisions.price -= cost;
//        }
//
//        return bestDivisions;
//    }
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
