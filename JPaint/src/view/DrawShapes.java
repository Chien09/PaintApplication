package view;

import model.interfaces.IShape;

import view.interfaces.IDrawStrategy;


public class DrawShapes {
    private IDrawStrategy DrawStrategy;

    //Constructor
    public DrawShapes(IDrawStrategy DrawStrategy) {
        this.DrawStrategy = DrawStrategy;
    }

    //To execute each Strategy called
    public void executeStrategy(IShape shape){
        DrawStrategy.DrawShape(shape);
    }
}
