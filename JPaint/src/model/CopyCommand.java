package model;

import model.interfaces.ICommand;
import model.interfaces.IShape;

import java.awt.*;

public class CopyCommand implements ICommand {

    private SelectedShapesList SelectedShapes;
    private CopiedClipBoard CopiedShapes;

    //Constructor
    public CopyCommand(SelectedShapesList SelectedShapes, CopiedClipBoard CopiedShapes){
        this.SelectedShapes = SelectedShapes;
        this.CopiedShapes = CopiedShapes;
    }

    //Creating a new shape from Copied Clipboard due to reference type
    private IShape CreateShape(IShape shape){

        ShapeType ShapeTYPE = shape.getShapeType();
        ShapeShadingType ShadingType = shape.getShadingType();
        Point minPoint = new Point(shape.getMinPoint().getX(), shape.getMinPoint().getY());
        Point maxPoint = new Point(shape.getMaxPoint().getX(), shape.getMaxPoint().getY());
        Color primaryColor = shape.getPrimaryColor();
        Color secondaryColor = shape.getSecondaryColor();

        ShapeFactory ShapeCreated = new ShapeFactory();
        IShape Shape;

        //Trigger of Abstract Factory
        if(ShapeTYPE == ShapeType.RECTANGLE){
            Shape = ShapeCreated.CreateShapeRectangle(shape.getPaintCanvas(), minPoint, maxPoint, ShapeTYPE, ShadingType, primaryColor, secondaryColor);
        }
        else if(ShapeTYPE == ShapeType.ELLIPSE){
            Shape = ShapeCreated.CreateShapeEllipse(shape.getPaintCanvas(), minPoint, maxPoint, ShapeTYPE, ShadingType, primaryColor, secondaryColor);
        }
        else {//if(ShapeTYPE == ShapeType.TRIANGLE)
            Shape = ShapeCreated.CreateShapeTriangle(shape.getPaintCanvas(), minPoint, maxPoint, ShapeTYPE, ShadingType, primaryColor, secondaryColor);
        }

        return Shape;
    }


    @Override
    public void execute() {

        CopiedShapes.clearList();

        if(SelectedShapes.getSelectedShapesList().isEmpty() == false) {
            for (IShape shape : SelectedShapes.getSelectedShapesList()) {

                //Creating actual/deep copy of Shape Object NOT reference, to be added to the Master ShapesList
                IShape newCopiedShape = CreateShape(shape);

                CopiedShapes.CopyShape(newCopiedShape);

                System.out.println("Selected Shape Copied onto Clipboard List");
            }
        }
        else{ //empty list (FOR Checking purposes)
            //throw new IllegalArgumentException("Empty SelectedList!!!!!!!");
            System.out.println("EMPTY SelectedList!!!!!!!!");
        }
    }
}
