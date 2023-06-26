package breakout.model;

public abstract class GameObject implements IPositionable{


    //-------------- SUPERCLASS VARIABLES ---------------
    private double posX;
    private double posY;
    private double width;
    private double height;

    //-------------- SUPER CONSTRUCTOR ---------------
    public GameObject(double posX, double posY, double width, double height) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
    }

    //-------------- "HELPER" METHOD FOR SETTING OBJECT DIMENSIONS ---------------
    protected void setDimension(double width, double height) {
        this.width = width;
        this.height = height;
    }

    //-------------- SETTER & GETTERS ---------------
    public double getX() {
        return posX;
    }
    public double getY() {
        return posY;
    }
    public void setX(double posX){
        this.posX = posX;
    }
    public void setY(double posY){
        this.posY = posY;
    }

    public double getWidth() {
        return width;
    }
    public double getHeight() {
        return height;
    }



    //-------------- SUPERCLASS METHODS ---------------

    // Method ger "true" om någon av lokala booleanska variablarna ger false.
    public boolean isColliding(IPositionable obj) {
        boolean isAbove = (posY + height) < obj.getY();
        boolean isBelow = posY > (obj.getY() + obj.getHeight());
        boolean isRight = posX > (obj.getX() + obj.getWidth());
        boolean isLeft = (posX + width) < obj.getX();

        return !(isAbove || isBelow || isRight || isLeft);
    }


}
