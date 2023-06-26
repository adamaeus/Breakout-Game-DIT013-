package breakout.model;

/*
 *   A brick for the rows of bricks
 */

public class Brick extends GameObject {

    public static final double BRICK_WIDTH = 20;  // Default values, use in constructors, not directly
    public static final double BRICK_HEIGHT = 10;
    private int points;


    //-------------- CONSTRUCTOR ---------------
    public Brick(double posX, double posY, double width, double height,  int points) {
        super(posX, posY, width, height);
        this.points = points;
    }

    public int getPoints(){
        return points;
    }

}
