package model;

//Creating Own Point X,Y Class
public class Point {

    private int x;
    private int y;

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

    public void UpdateAddX(int deltaX){
        this.x = this.x + deltaX;
    }

    public void UpdateAddY(int deltaY){
        this.y = this.y + deltaY;
    }

    public void UpdateMinusX(int deltaX){
        this.x = this.x - deltaX;
    }

    public void UpdateMinusY(int deltaY){
        this.y = this.y - deltaY;
    }

}
