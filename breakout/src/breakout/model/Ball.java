package breakout.model;

/*
 *    A Ball for the Breakout game
 */

import java.util.Random;

public class Ball extends GameObject {


    //-------------- INSTANCE VARIABLES ---------------

    // Gjorde BALL SIZE till static så att IsMovable kommer åt den för
    // sin konstruktor.
    public static final double BALL_SIZE = 10;
    private double dX;
    private double dY;


    //-------------- CONSTRUCTOR ---------------
    public Ball(double posX, double posY){
        super(posX, posY, BALL_SIZE, BALL_SIZE);
        //setDimension(BALL_SIZE, BALL_SIZE); // Experimental, method in superclass.
        dX = newXDirection(0.8);
        dY = newYDirection(0.7);
    }
    Random rs = new Random();

    //-------------- MOVEMENT ---------------
    public void move() {
        this.setX(getX() + dX);
        this.setY(getY() + dY);
    }

    // Ger en ny riktning i x led.
    public double newXDirection(double speed) {
        return (speed * -1) * (rs.nextDouble(-35, 35) / (double) 10);
    }

    // ger främst riktning i Y led. Vi vill inte att Y ska kunna genereras som negativ då det skulle innebära att bollen
    // spawnar med en färdriktning uppåt.
    public double newYDirection(double speed){
        return speed + (rs.nextInt(5,12)/(double)10);
    }

    //-------------- COLLISION MECHANICS ---------------
    public void wallCollision(Wall wall) {
        if (wall.getDirection() == Wall.Dir.VERTICAL) {
            dX *= -1;
        } else {
            dY *= -1;
        }
    }
    public void invertDirection() {
        dY = dY * -1;
    }

}

