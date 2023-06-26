package breakout.model;

import static breakout.model.Breakout.GAME_WIDTH;

/*
 * A Paddle for the Breakout game
 *
 */
public class Paddle extends GameObject{


    public static final double PADDLE_WIDTH = 60;  // Default values, use in constructors, not directly
    public static final double PADDLE_HEIGHT = 10;
    public static final double PADDLE_SPEED = 2;

    // Speed increase on X axis.
    private double dX;
    private boolean moveLeft = false;
    private boolean moveRight = false;

    public Paddle(double posX, double posY) {
        super(posX, posY, PADDLE_WIDTH, PADDLE_HEIGHT);
        //setDimension(PADDLE_WIDTH, PADDLE_HEIGHT);
    }
    public double getSpeed(){
        return PADDLE_SPEED;
    }
    //-------------- MOVEMENT ---------------

    // Main method for movement. Checks which of the keypresses would be true, acting accordingly.
    void move() {
        if (moveRight) {
            double newX = getX() + dX;
            if (newX < GAME_WIDTH - PADDLE_WIDTH) {
                setX(newX);
            } else {
                setX(GAME_WIDTH - PADDLE_WIDTH);
            }
        } else if (moveLeft) {
            double newX = getX() + dX;
            if (newX > 5) {
                setX(newX);
            } else {
                setX(5);
            }
        }
    }

    // Dessa metoder implementerade jag enbart för att sätta motsatt riktning till False från den vi flyttar oss i.
    public void setRightMotion(double speed) {
        dX = speed;
        if (speed > 0) {
            moveRight = true;
            moveLeft = false;
        } else {
            moveRight = false;
        }
    }

    public void setLeftMotion(double speed) {
        dX = speed;
        if (speed < 0) {
            moveLeft = true;
            moveRight = false;
        } else {
            moveLeft = false;
        }
    }

    //-------------- GETTERS & SETTERS ---------------

}
