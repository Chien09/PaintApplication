package model;

import model.interfaces.IShape;
import view.BorderEllipseStrategy;
import view.BorderRectangleStrategy;
import view.BorderShapes;
import view.BorderTriangleStrategy;

import java.util.ArrayList;

public class SelectedShapesList {

    //Whenever shapes are selected it will be stored here
    private ArrayList<IShape> SelectedList = new ArrayList<IShape>();

    public void addShape(IShape shape){
        SelectedList.add(shape);
    }

    public ArrayList<IShape> getSelectedShapesList() { return SelectedList;}

    public void clearList(){
        SelectedList.clear();
    }

    public void DrawSelectedBorders(){
        //Drawing border around the selected shapes
        for (IShape shape : SelectedList) {

            //Trigger of drawing border Strategy Pattern
            BorderShapes BorderStrategy;
            if(shape.getShapeType() == ShapeType.RECTANGLE) {
                BorderStrategy = new BorderShapes(new BorderRectangleStrategy());
                BorderStrategy.executeStrategy(shape);
            }
            else if(shape.getShapeType() == ShapeType.ELLIPSE){
                BorderStrategy = new BorderShapes(new BorderEllipseStrategy());
                BorderStrategy.executeStrategy(shape);
            }
            else{ //shape.getShapeType() == ShapeType.TRIANGLE
                BorderStrategy = new BorderShapes(new BorderTriangleStrategy());
                BorderStrategy.executeStrategy(shape);
            }
        }
    }
}
