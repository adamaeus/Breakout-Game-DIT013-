package breakout.model;

/*
        A wall for the ball to bounce
 */
public class Wall extends GameObject{

    public static final double WALL_THICKNESS = 20;
    private final Dir dir;
    public enum Dir {
        HORIZONTAL, VERTICAL;
    }

    //-------------- CONSTRUCTOR ---------------
    public Wall (double posX, double posY, double width, double height, Dir dir){
        super(posX, posY, width, height);
        this.dir = dir;
    }


    //-------------- GETTERS ---------------
    public Dir getDirection(){
        return dir;
    }

}
