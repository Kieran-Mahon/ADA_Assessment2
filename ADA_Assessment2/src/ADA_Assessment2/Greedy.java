package ADA_Assessment2;

import java.util.ArrayList;

public class Greedy extends Subdivision {

    public Greedy(int width, int height) {
        super(width, height);
    }

    @Override
    public ArrayList<Land> calculate() {
        Land startLand = new Land(0, 0, this.width, this.height);
    }
}