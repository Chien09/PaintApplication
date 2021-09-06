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

    public void removeShape(IShape shape) {SelectedList.remove(shape); }

    public ArrayList<IShape> getSelectedShapesList() { return SelectedList;}

    public void clearList(){
        SelectedList.clear();
    }

    //Trigger of drawing border Strategy Pattern
    public void DrawSelectedBorders(){

        for (IShape shape : SelectedList) {

            if(shape instanceof GroupedShapesComposite) { //if the shape is of GroupedShapesComposite Type

                //Use the GroupedShapesComposite drawBorders instead (Casting shape into GroupedShapesComposite object type)
                ((GroupedShapesComposite) shape).drawSelectedGroupBorders();
            }
            else {
                BorderShapes BorderStrategy;
                if (shape.getShapeType() == ShapeType.RECTANGLE) {
                    BorderStrategy = new BorderShapes(new BorderRectangleStrategy());
                    BorderStrategy.executeStrategy(shape);
                } else if (shape.getShapeType() == ShapeType.ELLIPSE) {
                    BorderStrategy = new BorderShapes(new BorderEllipseStrategy());
                    BorderStrategy.executeStrategy(shape);
                } else { //shape.getShapeType() == ShapeType.TRIANGLE
                    BorderStrategy = new BorderShapes(new BorderTriangleStrategy());
                    BorderStrategy.executeStrategy(shape);
                }
            }
        }
    }
}
