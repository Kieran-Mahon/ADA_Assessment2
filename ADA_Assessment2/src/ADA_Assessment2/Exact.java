package ADA_Assessment2;

import java.util.ArrayList;

public class Exact extends Subdivision {
    
    //Private variable to make it easier to set and get values via functions
    private final BestDivision[][] grid;
    
    public Exact(int width, int height) {
        super(width, height);
        this.grid = new BestDivision[width][height];
    }

    @Override
    public ArrayList<Land> calculate() {
        // Say which method got called
        System.out.println("Exact method called");

        // Call the find exact function
        Land startLand = new Land(0, 0, this.width, this.height);
        BestDivision bestDivision = findExact(startLand);

        // Assign price
        this.price = bestDivision.price;

        // ---- CAN REMOVE BELOW ---- (was used for testing)
        String text = "Best div:\n";
        for (Land land : bestDivision.list) {
            text += land.toString() + ",";
        }
        System.out.println(text + "\n");
        System.out.println("Text display:");
        System.out.println(displayGrid());
        // ---- CAN REMOVE ABOVE ----
        
        // Show text output
        System.out.println(textDisplay(bestDivision.list, bestDivision.price));

        // Return list
        return bestDivision.list;
    }
    
    // Function to find the best division
    private BestDivision findExact(Land startLand) {
        // Fill in the grid
        for (int w = 1; w < startLand.width + 1; w++) {
            for (int h = 1; h < startLand.height + 1; h++) {
                
                // No division (set as first best)
                Land noDivLand = new Land(0, 0, w, h);
                BestDivision best = new BestDivision(getLandValue(noDivLand), noDivLand);
                
                // Vertical divide
                for (int i = 1; i < noDivLand.width; i++) {
                    // Get a and b land sizes from grid
                    BestDivision a = checkGrid(i, noDivLand.height);
                    BestDivision b = checkGrid(noDivLand.width - i, noDivLand.height);
                    
                    // Move b side to the right 
                    for (Land land : b.list) {
                        land.x += i;
                    }
                    
                    // Calculate the cost of the division
                    int cost = noDivLand.height * this.divideCost;
                    
                    // Combine a and b together within a Best Division
                    BestDivision division = new BestDivision(a, b); //Add a and b together
                    division.price -= cost; //Remove cost
                    
                    // Check if this division better than the current best
                    if ((division.price) > best.price) {
                        best = division;
                    }
                }

                // Horizontal
                for (int i = 1; i < noDivLand.height; i++) {
                    // Get a and b land sizes from grid
                    BestDivision a = checkGrid(noDivLand.width, i);
                    BestDivision b = checkGrid(noDivLand.width, noDivLand.height - i);
                    
                    // Move b side down 
                    for (Land land : b.list) {
                        land.y += i;
                    }
                    
                    // Calculate the cost of the division
                    int cost = noDivLand.width * this.divideCost;

                    // Combine a and b together within a Best Division
                    BestDivision division = new BestDivision(a, b); //Add a and b together
                    division.price -= cost; //Remove cost

                    // Check if this divide is the best
                    if ((division.price) > best.price) {
                        best = division;
                    }
                }
                setGrid(w, h, best);
            }
        }
        
        // Return the bottom right most grid value
        return checkGrid(this.width, this.height);
    }
    
    // Function to get the value from the grid
    private BestDivision checkGrid(int w, int h) {
        // Remove 1 from width and height as array starts at 0 but
        // width and height starts at 1
        w = w - 1;
        h = h - 1;
        // Make clone
        BestDivision clone = new BestDivision(0, new Land(0, 0, 0, 0)); // Make empty
        clone.price = this.grid[w][h].price; // Copy price over
        clone.list.clear(); // Remove the empty land from the list
        // Add the land by VALUE (NOT REFERENCE) into the new list
        for (Land d : this.grid[w][h].list) {
            clone.list.add(new Land(d.x, d.y, d.width, d.height));
        }
        // Return clone
        return clone;
    }
    
    // Function to set a value to the grid
    private void setGrid(int w, int h, BestDivision division) {
        // Remove 1 from width and height as array starts at 0 but
        // width and height starts at 1
        w = w - 1;
        h = h - 1;
        // Set new best division to grid
        this.grid[w][h] = division;
    }
    
    
    //NEED TO BE DELETED!
    public String displayGrid() {
        System.out.println("<GRID>");
        String text = "";
        for (int h = 0; h < this.grid[0].length; h++) {
            for (int w = 0; w < this.grid.length; w++) {
                text += "{";
                for (Land land : this.grid[w][h].list) {
                    text += land.toString() + ", ";
                }
                text += "}, ";
            }
            text += "\n";
        }
        
        return text;
    }
    // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ DELETE
}