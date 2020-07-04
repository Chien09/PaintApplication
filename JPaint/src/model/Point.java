package model;

//Creating Own Point X,Y Class
public class Point {

    //Final means attributes/fields are immutable
    private final int x;
    private final int y;

    //Constructor
    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

}
