package model;

import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.interfaces.IUndoRedo;
import view.interfaces.PaintCanvasBase;

import java.awt.*;

public class DeleteCommand implements ICommand, IUndoRedo {

    private PaintCanvasBase paintCanvas;
    private ShapeArrayList ShapesList;
    private SelectedShapesList SelectedList;

    //Constructor
    public DeleteCommand(PaintCanvasBase paintCanvas, ShapeArrayList ShapesList, SelectedShapesList SelectedList){
        this.paintCanvas = paintCanvas;
        this.ShapesList = ShapesList;
        this.SelectedList = SelectedList;
    }

    @Override
    public void execute() {

        for(IShape shape: SelectedList.getSelectedShapesList()){

            ShapesList.removeShape(shape);

            System.out.println("Selected Shape Deleted");
        }

        //For clearing paintCanvas for redraw not a good solution because it adds a new layer on top
        //so OLD data is not erased from the ShapesList or the SelectedList
        Graphics2D graphics2D = paintCanvas.getGraphics2D();
        graphics2D.clearRect(0,0, paintCanvas.getWidth(), paintCanvas.getHeight());

        //To draw all shapes using Strategy Pattern
        ShapesList.drawShapes();

        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        for(IShape shape: SelectedList.getSelectedShapesList()){
            ShapesList.addShape(shape);
        }

        //For clearing paintCanvas for redraw not a good solution because it adds a new layer on top
        //so OLD data is not erased from the ShapesList or the SelectedList
        Graphics2D graphics2D = paintCanvas.getGraphics2D();
        graphics2D.clearRect(0,0, paintCanvas.getWidth(), paintCanvas.getHeight());

        //To draw all shapes using Strategy Pattern
        ShapesList.drawShapes();

        //Trigger of drawing border Strategy Pattern
        SelectedList.DrawSelectedBorders();

        System.out.println("Undo--->Adding deleted shape back");
    }

    @Override
    public void redo() {

        for(IShape shape: SelectedList.getSelectedShapesList()){
            ShapesList.removeShape(shape);
        }

        //For clearing paintCanvas for redraw not a good solution because it adds a new layer on top
        //so OLD data is not erased from the ShapesList or the SelectedList
        Graphics2D graphics2D = paintCanvas.getGraphics2D();
        graphics2D.clearRect(0,0, paintCanvas.getWidth(), paintCanvas.getHeight());

        //To draw all shapes using Strategy Pattern
        ShapesList.drawShapes();

        System.out.println("Redo--->Delete shape");

    }
}
