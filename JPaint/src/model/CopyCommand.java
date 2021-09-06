package model;

import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.interfaces.IShapeFactory;

import java.awt.*;
import java.util.ArrayList;

public class CopyCommand implements ICommand {

    private SelectedShapesList SelectedShapes;
    private CopiedClipBoard CopiedShapes;

    //Constructor
    public CopyCommand(SelectedShapesList SelectedShapes, CopiedClipBoard CopiedShapes){
        this.SelectedShapes = SelectedShapes;
        this.CopiedShapes = CopiedShapes;
    }

    //method Creating a new shape from Copied Clipboard due to reference type
    private IShape CreateShape(IShape shape){

        IShapeFactory ShapeCreated = new ShapeFactory();
        IShape newShape;

        if(shape instanceof GroupedShapesComposite){ //GroupedShapesComposite Type

            ArrayList<IShape> TmpShapeArray; //tmp store GroupedShapesComposite shapes List
            TmpShapeArray = ((GroupedShapesComposite) shape).getGroupedShapesList();

            //For new Copy
            GroupedShapesComposite newGroupedShapesComposite = new GroupedShapesComposite(shape.getPaintCanvas());

            //iterate through the GroupedShapes
            for(IShape TmpShape: TmpShapeArray){
                ShapeType ShapeTYPE = TmpShape.getShapeType();
                ShapeShadingType ShadingType = TmpShape.getShadingType();
                Point minPoint = new Point(TmpShape.getMinPoint().getX(), TmpShape.getMinPoint().getY());
                Point maxPoint = new Point(TmpShape.getMaxPoint().getX(), TmpShape.getMaxPoint().getY());
                Color primaryColor = TmpShape.getPrimaryColor();
                Color secondaryColor = TmpShape.getSecondaryColor();

                //Trigger of Abstract Factory
                if (ShapeTYPE == ShapeType.RECTANGLE) {
                    newShape = ShapeCreated.CreateShapeRectangle(TmpShape.getPaintCanvas(), minPoint, maxPoint, ShapeTYPE, ShadingType, primaryColor, secondaryColor);
                } else if (ShapeTYPE == ShapeType.ELLIPSE) {
                    newShape = ShapeCreated.CreateShapeEllipse(TmpShape.getPaintCanvas(), minPoint, maxPoint, ShapeTYPE, ShadingType, primaryColor, secondaryColor);
                } else {//if(ShapeTYPE == ShapeType.TRIANGLE)
                    newShape = ShapeCreated.CreateShapeTriangle(TmpShape.getPaintCanvas(), minPoint, maxPoint, ShapeTYPE, ShadingType, primaryColor, secondaryColor);
                }

                newGroupedShapesComposite.addShape(newShape);
            }
            return newGroupedShapesComposite;
        }
        else { //IShape Type
            ShapeType ShapeTYPE = shape.getShapeType();
            ShapeShadingType ShadingType = shape.getShadingType();
            Point minPoint = new Point(shape.getMinPoint().getX(), shape.getMinPoint().getY());
            Point maxPoint = new Point(shape.getMaxPoint().getX(), shape.getMaxPoint().getY());
            Color primaryColor = shape.getPrimaryColor();
            Color secondaryColor = shape.getSecondaryColor();

            //Trigger of Abstract Factory
            if (ShapeTYPE == ShapeType.RECTANGLE) {
                newShape = ShapeCreated.CreateShapeRectangle(shape.getPaintCanvas(), minPoint, maxPoint, ShapeTYPE, ShadingType, primaryColor, secondaryColor);
            } else if (ShapeTYPE == ShapeType.ELLIPSE) {
                newShape = ShapeCreated.CreateShapeEllipse(shape.getPaintCanvas(), minPoint, maxPoint, ShapeTYPE, ShadingType, primaryColor, secondaryColor);
            } else {//if(ShapeTYPE == ShapeType.TRIANGLE)
                newShape = ShapeCreated.CreateShapeTriangle(shape.getPaintCanvas(), minPoint, maxPoint, ShapeTYPE, ShadingType, primaryColor, secondaryColor);
            }

            return newShape;
        }
    }


    @Override
    public void execute() {

        CopiedShapes.clearList();

        if(SelectedShapes.getSelectedShapesList().isEmpty() == false) {
            for (IShape shape : SelectedShapes.getSelectedShapesList()) {

                //Creating actual/deep copy of Shape Object NOT reference
                IShape newCopiedShape = CreateShape(shape);

                CopiedShapes.CopyShape(newCopiedShape);

                System.out.println("Selected Shape(s) Copied onto Clipboard List");
            }
        }
        else{ //empty list (FOR Checking purposes)
            //throw new IllegalArgumentException("Empty SelectedList!!!!!!!");
            System.out.println("EMPTY SelectedList!!!!!!!!");
        }
    }
}
