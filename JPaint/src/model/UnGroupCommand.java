package model;

import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.interfaces.IUndoRedo;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.util.ArrayList;

public class UnGroupCommand implements ICommand, IUndoRedo {

    private PaintCanvasBase paintCanvas;
    private ShapeArrayList ShapesList;
    private SelectedShapesList SelectedList;
    private ArrayList<IShape> TmpGroupedShapeList;
    private GroupedShapesComposite TmpGroupedShape;

    public UnGroupCommand(PaintCanvasBase paintCanvas, ShapeArrayList ShapesList, SelectedShapesList SelectedList){
        this.paintCanvas = paintCanvas;
        this.SelectedList = SelectedList;
        this.ShapesList = ShapesList;
    }

    @Override
    public void execute() {

        for(IShape shape: SelectedList.getSelectedShapesList()){

            if(shape instanceof GroupedShapesComposite){

                //Maintain reference to GroupComposite Object
                TmpGroupedShape = (GroupedShapesComposite) shape;

                //Retrieve the GroupedShape List
                TmpGroupedShapeList = TmpGroupedShape.getGroupedShapesList();
            }
        }

        //Remove the Grouped Shape Object
        SelectedList.removeShape(TmpGroupedShape);
        ShapesList.removeShape(TmpGroupedShape);

        //Add the shape back
        for(IShape shape: TmpGroupedShapeList) {
            SelectedList.addShape(shape);
            ShapesList.addShape(shape);
        }

        Graphics2D graphics2D = paintCanvas.getGraphics2D();
        //For clearing paintCanvas for redraw shapes and border not a good solution because it adds a new layer on top
        //so OLD data is not erased from the ShapesList or the SelectedList
        //CLEARING for "Deselecting Purposes"
        graphics2D.clearRect(0,0,paintCanvas.getWidth(),paintCanvas.getHeight());

        //To redraw all shapes using Strategy Pattern
        ShapesList.drawShapes();

        //Trigger of drawing border Strategy Pattern
        SelectedList.DrawSelectedBorders();

        System.out.println("Grouped Shape is Ungrouped");

        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        GroupedShapesComposite GroupedShapes = new GroupedShapesComposite(paintCanvas);

        GroupedShapes.SetGroupedShapesList(TmpGroupedShapeList);

        //Remove the Shapes that are Grouped from ShapesList & SelectedList
        for(IShape shape: TmpGroupedShapeList){
            SelectedList.removeShape(shape);
            ShapesList.removeShape(shape);
        }

        SelectedList.addShape(GroupedShapes);
        ShapesList.addShape(GroupedShapes);

        Graphics2D graphics2D = paintCanvas.getGraphics2D();
        //For clearing paintCanvas for redraw shapes and border not a good solution because it adds a new layer on top
        //so OLD data is not erased from the ShapesList or the SelectedList
        //CLEARING for "Deselecting Purposes"
        graphics2D.clearRect(0,0,paintCanvas.getWidth(),paintCanvas.getHeight());

        //To redraw all shapes using Strategy Pattern (For removing Selected Black Outline cause some shapes are GROUPED)
        ShapesList.drawShapes();

        //ReDraw Selected Shapes Outline Borders (Red Borders around GROUPED shapes)
        SelectedList.DrawSelectedBorders();

        System.out.println("Undo--->UnGrouped Shapes are Grouped");
    }

    @Override
    public void redo() {

        for(IShape shape: SelectedList.getSelectedShapesList()){

            if(shape instanceof GroupedShapesComposite){

                //Maintain reference to GroupComposite Object
                TmpGroupedShape = (GroupedShapesComposite) shape;

                //Retrieve the GroupedShape List
                TmpGroupedShapeList = TmpGroupedShape.getGroupedShapesList();
            }
        }

        //Remove the GroupedShape Object
        SelectedList.removeShape(TmpGroupedShape);
        ShapesList.removeShape(TmpGroupedShape);

        //Add the shape back
        for(IShape shape: TmpGroupedShapeList) {
            SelectedList.addShape(shape);
            ShapesList.addShape(shape);
        }

        Graphics2D graphics2D = paintCanvas.getGraphics2D();
        //For clearing paintCanvas for redraw shapes and border not a good solution because it adds a new layer on top
        //so OLD data is not erased from the ShapesList or the SelectedList
        //CLEARING for "Deselecting Purposes"
        graphics2D.clearRect(0,0,paintCanvas.getWidth(),paintCanvas.getHeight());

        //To redraw all shapes using Strategy Pattern
        ShapesList.drawShapes();

        //Trigger of drawing border Strategy Pattern
        SelectedList.DrawSelectedBorders();

        System.out.println("Redo--->Grouped Shape is Ungrouped");
    }
}
