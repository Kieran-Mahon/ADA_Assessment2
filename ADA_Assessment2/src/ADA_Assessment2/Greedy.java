package ADA_Assessment2;

import java.util.ArrayList;

/*
The Greedy class will only return value for lands that are within the bounds 
of the Subdivision.landValues (see Subdivision.loadLandValues). Currently set to
6x6 at the moment of writing this comment. 

This is beacause dividing the land will cost more than the divisions are worth 
(value is less than division cost). This is how the greedy algorithm should work
so this is okay
 */
public class Greedy extends Subdivision {

    public Greedy(int width, int height) {
        super(width, height);
    }

    // Chooses the best division value with the current piece of land
    @Override
    public ArrayList<Land> calculate() {
        // Say which method got called
        System.out.println("Greedy method called!");

        // Call the divide function
        Land startLand = new Land(0, 0, this.width, this.height);
        BestDivision bestDivision = findGreedy2(startLand);

        // Assign price
        this.price = bestDivision.price;

        // Show text output
        System.out.println(textDisplay(bestDivision.list, bestDivision.price));

        // Return list
        return bestDivision.list;
    }

    private BestDivision findGreedy(Land land) {
        // Set best as the current division
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

            // Check if this divide is the best
            if ((divison.price) > best.price) {
                best = divison;
            }
        }

        // If the size of the list is NOT 1 then it is NOT the original land
        // Go down the LEAST expensive side of the division and keep the MOST
        // expensive land of the division
        if (best.list.size() != 1) {
            // Index 1 means the most expensive land which is not touched
            BestDivision tempBest = new BestDivision(best.price, best.list.get(1));

            // Index 0 means the other land which gets checked
            BestDivision divisionFound = findGreedy(best.list.get(0));

            tempBest.list.addAll(divisionFound.list);
            tempBest.price += divisionFound.price - getLandPrice(best.list.get(0));

            // Set best division
            best = tempBest;
        }

        return best;
    }

    private BestDivision findGreedy2(Land land) {
        // Set best as the current division
        BestDivision best = new BestDivision(getLandPrice(land), land);
        int bestDivideCost = 0;

        // Vertical
        for (int i = 1; i < land.width; i++) {
            //Divide the land into 2 parts (A and B)
            Land a = new Land(land.x, land.y, i, land.height);
            Land b = new Land(land.x + i, land.y, land.width - i, land.height);

            // Calculate the cost of the division
            int cost = land.height * this.divideCost;

            // Add A and B to a best division for easier calculation
            BestDivision aBD = new BestDivision(getLandPrice(a), a);
            BestDivision bBD = new BestDivision(getLandPrice(b), b);
            BestDivision divison = new BestDivision(aBD, bBD); //Add a and b together
            divison.price -= cost; //Remove cost

            // Check if this divide better than the current best
            if ((divison.price) > best.price) {
                best = divison;
                bestDivideCost = cost;
            }
        }

        // Horizontal
        for (int i = 1; i < land.height; i++) {
            //Divide the land into 2 parts (A and B)
            Land a = new Land(land.x, land.y, land.width, i);
            Land b = new Land(land.x, land.y + i, land.width, land.height - i);

            // Calculate the cost of the division
            int cost = land.width * this.divideCost;

            // Add A and B to a best division for easier calculation
            BestDivision aBD = new BestDivision(getLandPrice(a), a);
            BestDivision bBD = new BestDivision(getLandPrice(b), b);
            BestDivision divison = new BestDivision(aBD, bBD); //Add a and b together
            divison.price -= cost; //Remove cost

            // Check if this divide is the best
            if ((divison.price) > best.price) {
                best = divison;
                bestDivideCost = cost;
            }
        }

        // Modify best with the sub divisions if there is any
        if (best.list.size() != 1) {
            //Get a and bbest
            BestDivision aBest = findGreedy2(best.list.get(0));
            BestDivision bBest = findGreedy2(best.list.get(1));

            //Add both together
            best = new BestDivision(aBest, bBest);

            //Remove cost
            best.price -= bestDivideCost;
        }

        //Return the best
        return best;
    }
}
