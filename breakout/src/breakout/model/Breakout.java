package breakout.model;


import breakout.event.EventBus;
import breakout.event.ModelEvent;

import java.util.ArrayList;
import java.util.List;

import static breakout.event.ModelEvent.Type.*;

/*
 *  Overall all logic for the Breakout Game
 *  Model class representing the full game
 *  This class should use other objects delegate work.
 *
 *  NOTE: Nothing visual here
 *
 */
public class Breakout {

    public static final double GAME_WIDTH = 400;
    public static final double GAME_HEIGHT = 400;
    public static final double BALL_SPEED_FACTOR = 1.05; // Increase ball speed
    public static final long SEC = 1_000_000_000;  // Nano seconds used by JavaFX

    private int nBalls = 5;
    private Ball ball;
    private Paddle paddle;
    private List <Brick> bricks;
    private List <Wall> walls;
    private int playerPoints;

    // TODO Constructor that accepts all objects needed for the model


    // Put objects that is needed for the model into the constructor.
    public Breakout(List<Wall> walls, List<Brick> bricks, Ball ball, Paddle paddle) {
        this.walls = walls;
        this.bricks = bricks;
        this.ball = ball;
        this.paddle = paddle;
    }


    // --------  Game Logic -------------

    private long timeForLastHit;         // To avoid multiple collisions

    public void update(long now) {
        // TODO  Main game loop, start functional decomposition from here

        ball.move();
        paddle.move();
        if (now - timeForLastHit > 10_000_000) { // ser till att det är ett tidsintervall mellan studsen
            ballSpawner(now);
            brickCollision(now);
            paddleCollision(now);
            bounceOfWalls(now);
        }
    }

    // ----- Helper methods--------------


    // delegates operation to paddle to move the paddle.
    public void movePaddleRight(double speed){
        paddle.setRightMotion(speed);
    }
    public void movePaddleLeft(double speed){
        paddle.setLeftMotion(speed);
    }


    // Om det finns bollar, bollens y position är större än världens höjd (bollen har fallit ned) och det finns bricks kvar
    // initiera en ny boll.
    private void ballSpawner(long now){
        if (nBalls > 0 && ball.getY() > GAME_HEIGHT && !bricks.isEmpty()) {
            nBalls--;
            ball = createBall();
            timeForLastHit = now;
            EventBus.INSTANCE.publish(new ModelEvent(NEW_BALL));

        }
    }

    // När bollen träffar paddle (if isColliding är true), vi inverterar bollens direction (via objectCollision).
    private void paddleCollision(long now){
        if (ball.isColliding(paddle)) {
            ball.invertDirection();
            timeForLastHit = now;
            EventBus.INSTANCE.publish(new ModelEvent(BALL_HIT_PADDLE));
        }
    }


    private void brickCollision(long now){
        for (Brick brick : bricks) {
            if (ball.isColliding(brick)) {
                ball.invertDirection();
                timeForLastHit = now;
                playerPoints += brick.getPoints();
                bricks.remove(brick);
                EventBus.INSTANCE.publish(new ModelEvent(BALL_HIT_BRICK));
                break;
            }
        }
    }
    private void bounceOfWalls(long now){
        for (Wall wall : walls) {
            if (ball.isColliding(wall)) {
                ball.wallCollision(wall);
                timeForLastHit = now;
                EventBus.INSTANCE.publish(new ModelEvent(BALL_HIT_WALL));
            }
        }
    }


    // Used for functional decomposition



    // --- Used by GUI  ------------------------


    /**
     * Skapar en lista av objects som ska visualiseras av view.
     * @return
     */
    public List<IPositionable> getPositionables() {
        ArrayList<IPositionable>objects = new ArrayList<>();
        objects.add(ball);
        objects.add(paddle);
        objects.addAll(bricks);
        return objects;
    }

    public int getPlayerPoints() {
        return playerPoints;
    }

    public int getnBalls() {
        return nBalls;
    }


    /**
     * Returnerar breakouts paddle, inte den som skapas av "createPaddle".
     * @return
     */
    public Paddle getPaddle(){
        return paddle;
    }


    /**
     * To create a ball and paddle. Tror inte dessa bör bo i view,
     * utan snarare i model. Vet dock inte om det är här de bör
     * skapas.
     * @return
     */
    public Ball createBall(){
        return new Ball(GAME_WIDTH / 2, GAME_HEIGHT/2);
    }



}
