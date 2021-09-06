package model;

import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.interfaces.IUndoRedo;

import java.util.ArrayList;


public class PasteCommand implements ICommand, IUndoRedo {

    private CopiedClipBoard CopiedShapes;
    private ShapeArrayList ShapesList;

    //Constructor
    public PasteCommand(ShapeArrayList ShapesList, CopiedClipBoard CopiedShapes){
        this.CopiedShapes = CopiedShapes;
        this.ShapesList = ShapesList;
    }


    @Override
    public void execute() {

        if(CopiedShapes.getCopiedShapesList().isEmpty() == false) {
            for (IShape shape : CopiedShapes.getCopiedShapesList()) {

                //Change the X, Y so when pasting its not in the same original spot
                shape.UpdateX(90);
                shape.UpdateY(90);

                ShapesList.addShape(shape);

                System.out.println("Copied Shape Pasted");
            }

            //drawShapes uses Strategy Pattern
            ShapesList.drawShapes();
        }
        else{ //empty list (FOR Checking purposes)
            //throw new IllegalArgumentException("Empty ClipBoardList!!!!!!!");
            System.out.println("EMPTY ClipBoardList!!!!!!!!");
        }
    }

    @Override
    public void undo() {

        for(IShape Shape: CopiedShapes.getCopiedShapesList()){
            ShapesList.removeShape(Shape);
        }

        //drawShapes uses Strategy Pattern
        ShapesList.drawShapes();
    }

    @Override
    public void redo() {

        for (IShape shape : CopiedShapes.getCopiedShapesList()) {

            //Change the X, Y so when pasting its not in the same original spot
            shape.UpdateX(90);
            shape.UpdateY(90);

            ShapesList.addShape(shape);
        }

        //drawShapes uses Strategy Pattern
        ShapesList.drawShapes();
    }
}
