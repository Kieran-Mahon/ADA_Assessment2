package ADA_Assessment2;

import java.util.ArrayList;

public abstract class Subdivision {

    protected final int divideCost = 50;
    protected int[][] landValues;
    protected int width;
    protected int height;
    protected int price = -1;

    public Subdivision(int width, int height) {
        //Make sure the number is not less or equal to 0
        if ((width <= 0) ||(height <= 0)) {
            throw new IllegalArgumentException("Size too small");
        }
        
        //Set width and height
        this.width = width;
        this.height = height;
        //Load land values
        loadLandValues();
        
        //Display created message
        System.out.println("Created a " + this.width + " by " + this.height + " land! \n");
    }
    
    private void loadLandValues() {
        this.landValues = new int[][]{
            //1   2    3    4    5    6
            {20, 40, 100, 130, 150, 200}, //   1
            {40, 140, 250, 320, 400, 450}, //  2
            {100, 250, 350, 420, 450, 500}, // 3
            {130, 320, 420, 500, 600, 700}, // 4
            {150, 400, 450, 600, 700, 800}, // 5
            {200, 450, 500, 700, 800, 900}}; //6
    }
    
    //Get price of a piece of land
    protected int getLandPrice(Land land) {
        //If out of land values bounds then return a price of $0
        if ((land.width > 6) ||(land.height > 6)) {
            return 0;
        }
        return this.landValues[land.width - 1][land.height - 1];
    }
    
    //Used to start and get the division method's results
    public abstract ArrayList<Land> calculate();
    
    //Used to get the price of the results (a value of -1 means the calculation
    //hasn't been done yet
    public int getPrice() {
        return this.price;
    }
    
    //Text display of a division
    public String textDisplay(ArrayList<Land> landList, int price) {
        //Add list to string
        String toReturn = landList.toString() + "\n";
        
        //Convert the land into an array format
        int[][] displayArray  = new int[this.width][this.height];
        int num = 0;
        for (Land land : landList) {
            num++;
            for (int w = 0; w < land.width; w++) {
                for (int h = 0; h < land.height; h++) {
                    displayArray[land.x + w][land.y + h] = num;
                }
            }
        }
        
        //Convert array format into string format
        for (int h = 0; h < this.height; h++) {
            for (int w = 0; w < this.width; w++) {
                toReturn += displayArray[w][h] ;
                if (w != this.width - 1) {
                    toReturn += " ";
                }
            }
            toReturn += "\n";
        }
        
        //Add price
        toReturn += "\nPrice: $" + price;
        
        //Return the string
        return toReturn;
    }
}