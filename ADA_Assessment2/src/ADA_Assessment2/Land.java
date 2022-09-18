package ADA_Assessment2;

public class Land {

    protected int x;
    protected int y;
    protected int width;
    protected int height;

    public Land(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public String toString() {
        return "(X:" + this.x + " Y:" + this.y + " Width:" + this.width + " Height:" + this.height + ")";
    }
}
