package model;


import javafx.scene.Group;
import model.interfaces.IShape;
import view.DrawShapes;
import view.FilledInStrategy;
import view.FilledOutlineStrategy;
import view.OutlineStrategy;

import java.util.ArrayList;

public class ShapeArrayList{

    //Whenever a shape is created it will be stored here
    private ArrayList<IShape> ShapesList = new ArrayList<IShape>();

    public void addShape(IShape shape){
        ShapesList.add(shape);
    }

    public void removeShape(IShape shape) {ShapesList.remove(shape);}

    public ArrayList<IShape> getShapesList() {return ShapesList; }

    //drawShapes uses Strategy Pattern
    public void drawShapes(){
        for(IShape shape : ShapesList){
            if(shape instanceof GroupedShapesComposite) { //if the shape is of GroupedShapesComposite Type

                //Use the GroupedShapesComposite draw instead (Casting shape into GroupedShapesComposite object type)
                ((GroupedShapesComposite) shape).drawGroupedShapes();
            }
            else{ //Draw each shape of IShape type
                DrawShapes DrawStrategy;

                if (shape.getShadingType() == ShapeShadingType.OUTLINE) {
                    DrawStrategy = new DrawShapes(new OutlineStrategy());
                    DrawStrategy.executeStrategy(shape);
                } else if (shape.getShadingType() == ShapeShadingType.FILLED_IN) {
                    DrawStrategy = new DrawShapes(new FilledInStrategy());
                    DrawStrategy.executeStrategy(shape);
                } else { //shape.getShadingType() == ShapeShadingType.OUTLINE_AND_FILLED_IN
                    DrawStrategy = new DrawShapes(new FilledOutlineStrategy());
                    DrawStrategy.executeStrategy(shape);
                }
            }
        }
    }
}
