package ADA_Assessment2;

import java.util.ArrayList;

public class Greedy extends Subdivision {

    public Greedy(int width, int height) {
        super(width, height);
    }

    // chooses the best division value with the current piece of land
    @Override
    public ArrayList<Land> calculate() {
        Land startLand = new Land(0, 0, this.width, this.height);
        
        for (int i = 0; i < startLand.width; i++) {
            
            
            int cost = startLand.height * this.divideCost;
        }
        
        
        return null;
    }
}