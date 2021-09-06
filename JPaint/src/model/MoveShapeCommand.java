package model;

import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.interfaces.IUndoRedo;
import view.interfaces.PaintCanvasBase;

import java.awt.*;

public class MoveShapeCommand implements ICommand, IUndoRedo {

    private PaintCanvasBase paintCanvas;
    private ShapeArrayList ShapesList;
    private SelectedShapesList SelectedList;
    private Point startPoint;
    private Point endPoint;
    private int deltaX;
    private int deltaY;

    //Constructor
    public MoveShapeCommand(PaintCanvasBase paintCanvas, ShapeArrayList ShapesList, SelectedShapesList SelectedList, Point startPoint, Point endPoint){
        this.paintCanvas = paintCanvas;
        this.ShapesList = ShapesList;
        this.SelectedList = SelectedList;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.deltaX = endPoint.getX() - startPoint.getX();
        this.deltaY = endPoint.getY() - startPoint.getY();
    }

    //ICommand
    @Override
    public void execute() {

        if (SelectedList.getSelectedShapesList().isEmpty() == false) { //Check

            Graphics2D graphics2D = paintCanvas.getGraphics2D();

            //For clearing paintCanvas for redraw shapes and border not a good solution because it adds a new layer on top
            //so OLD data is not erased from the ShapesList or the SelectedList
            graphics2D.clearRect(0,0,paintCanvas.getWidth(),paintCanvas.getHeight());

            for (IShape shape : SelectedList.getSelectedShapesList()) {

                //Updating Shapes in SelectedList for move which also updates on the master list due to reference of objects
                shape.UpdateX(deltaX);
                shape.UpdateY(deltaY);

                System.out.println("Shape Modified");
            }

            //To redraw all shapes using Strategy Pattern
            ShapesList.drawShapes();

            //Trigger of drawing border Strategy Pattern
            SelectedList.DrawSelectedBorders();
        }
        else{ //empty list (FOR Checking purposes)
            //throw new IllegalArgumentException("Empty SelectedList!!!!!!!");
            System.out.println("EMPTY SelectedList!!!!!!!!");
        }

        CommandHistory.add(this);
    }

    @Override
    public void undo() {

        Graphics2D graphics2D = paintCanvas.getGraphics2D();

        //For clearing paintCanvas for redraw shapes and border not a good solution because it adds a new layer on top
        //so OLD data is not erased from the ShapesList or the SelectedList
        graphics2D.clearRect(0,0,paintCanvas.getWidth(),paintCanvas.getHeight());

        for(IShape shape: SelectedList.getSelectedShapesList()){

            //Minus the delta X,Y that has been added
            shape.UndoUpdateX(deltaX);
            shape.UndoUpdateY(deltaY);
        }

        //To redraw all shapes using Strategy Pattern
        ShapesList.drawShapes();

        //Trigger of drawing border Strategy Pattern
        SelectedList.DrawSelectedBorders();

        System.out.println("Undo--->Moving shape (Minus delta X,Y)");
    }


    @Override
    public void redo() {

        Graphics2D graphics2D = paintCanvas.getGraphics2D();

        //For clearing paintCanvas for redraw shapes and border not a good solution because it adds a new layer on top
        //so OLD data is not erased from the ShapesList or the SelectedList
        graphics2D.clearRect(0,0,paintCanvas.getWidth(),paintCanvas.getHeight());

        for (IShape shape : SelectedList.getSelectedShapesList()) {
            shape.UpdateX(deltaX);
            shape.UpdateY(deltaY);
        }

        //To redraw all shapes using Strategy Pattern
        ShapesList.drawShapes();

        //Trigger of drawing border Strategy Pattern
        SelectedList.DrawSelectedBorders();

        System.out.println("Redo--->Moving shape (Add delta X,Y)");
    }
}
