package ADA_Assessment2;

/*
 * @author Kieran
 */
public abstract class Subdivision {

    protected final int divideCost = 50;
    protected int[][] landValues;
    protected int width;
    protected int height;

    public Subdivision(int width, int height) {
        if ((width <= 0) ||(height <= 0)) {
            throw new IllegalArgumentException("Size too small"); // kieran gets this error a lot with girls
        }
        this.width = width;
        this.height = height;
        loadLandValues();
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
    
    protected int getCellPrice(Cell cell) {
        //If out of land values bounds then return a price of $0
        if ((cell.width > 6) ||(cell.height > 6)) {
            System.out.println("Area over 6, W:" + cell.width + " H:" + cell.height);
            return 0;
        }
        return this.landValues[cell.width - 1][cell.height - 1];
    }
    
    public abstract void calculate();
    
    protected class Cell {

        protected int x;
        protected int y;
        protected int width;
        protected int height;
        
        protected Cell cellA;
        protected Cell cellB;

        public Cell(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        @Override
        public String toString() {
            return "(X:" + this.x +" Y:" + this.y + " Width:" + this.width + " Height:" + this.height + ")";
        }
        
        public Cell cloneCell() {
            return new Cell(this.x, this.y, this.width, this.height);
        }
    }
}
