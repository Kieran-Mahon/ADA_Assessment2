package ADA_Assessment2;

import java.util.ArrayList;

public class Exact extends Subdivision {

    public Exact(int width, int height) {
        super(width, height);
    }

    @Override
    public ArrayList<Land> calculate() {
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
