package ADA_Assessment2;

import java.util.ArrayList;

public class Exact extends Subdivision {

    public Exact(int width, int height) {
        super(width, height);
    }

    @Override
    public ArrayList<Land> calculate() {
        // Say which method got called
        System.out.println("Exact method called");

        // Call the divide function
        Land startLand = new Land(0, 0, this.width, this.height);
        BestDivision bestDivision = findExact(startLand);

        // Assign price
        this.price = bestDivision.price;

        // Show text output
        System.out.println(textDisplay(bestDivision.list, bestDivision.price));

        // Return list
        return bestDivision.list;
    }

    private BestDivision findExact(Land startLand) {
        // Set best as the current division
        BestDivision best = new BestDivision(getLandPrice(startLand), startLand);

        return best;
    }
}
