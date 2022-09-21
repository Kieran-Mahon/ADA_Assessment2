package ADA_Assessment2;

public class TestClass {

    // Class to test if the program is working
    // Will throw an error if there is a problem
    
    public static void main(String[] args) {
        // Testing 

        // Brute force
        BruteForce bruteForce = new BruteForce(7, 7);
        bruteForce.calculate();
        assert bruteForce.price == 990 : "Not correct";

        // Greedy
        Greedy greedy = new Greedy(7, 7);
        greedy.calculate();
        assert greedy.price == 0 : "Not correct";

        // Exact
        Exact exact = new Exact(7, 7);
        exact.calculate();
        assert exact.price == 990 : "Not correct";
    }
}
