package ADA_Assessment2;

public class TestClass {
    
    public static void main(String[] args) {
        // Testing 
        
        // Bruite force
        BruteForce bruteForce = new BruteForce(6, 3);
        bruteForce.calculate();
        assert bruteForce.price == 550: "Not correct";
        
        
    }
}