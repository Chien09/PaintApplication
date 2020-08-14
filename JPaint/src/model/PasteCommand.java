package model;

import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.interfaces.IUndoRedo;


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

            ShapesList.drawShapes();
        }
        else{ //empty list (FOR Checking purposes)
            //throw new IllegalArgumentException("Empty ClipBoardList!!!!!!!");
            System.out.println("EMPTY ClipBoardList!!!!!!!!");
        }
    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }
}
