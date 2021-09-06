package view;

import model.interfaces.IShape;
import view.interfaces.IBorderStrategy;

public class BorderShapes {

    private IBorderStrategy BorderStrategy;

    //Constructor
    public BorderShapes(IBorderStrategy BorderStrategy){
        this.BorderStrategy = BorderStrategy;
    }

    //To execute each Strategy called
    public void executeStrategy(IShape shape){
        BorderStrategy.DrawBorder(shape);
    }

}
