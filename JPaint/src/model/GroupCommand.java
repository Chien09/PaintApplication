package model;

import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.interfaces.IUndoRedo;
import view.interfaces.PaintCanvasBase;

import java.awt.*;

public class GroupCommand implements ICommand, IUndoRedo {

    private PaintCanvasBase paintCanvas;
    private ShapeArrayList ShapesList;
    private SelectedShapesList SelectedList;
    private GroupedShapesComposite GroupedShape;

    public GroupCommand(PaintCanvasBase paintCanvas, ShapeArrayList ShapesList, SelectedShapesList SelectedList){
        this.paintCanvas = paintCanvas;
        this.SelectedList = SelectedList;
        this.ShapesList = ShapesList;
    }

    @Override
    public void execute() {

        GroupedShape = new GroupedShapesComposite(paintCanvas);

        if(SelectedList.getSelectedShapesList().isEmpty() == false) { //Check

            //Add Shapes to GroupedList Composite
            for(IShape shape: SelectedList.getSelectedShapesList()) {
                GroupedShape.addShape(shape);
            }

            //Remove the Shapes that are Grouped from ShapesList & SelectedList
            for(IShape shape: GroupedShape.getGroupedShapesList()){

                //Remove the shape because going to group them into a Grouped Shape Composite
                ShapesList.removeShape(shape);

                //Remove the shapes because going to group them into a Grouped Shape Composite
                SelectedList.removeShape(shape);
            }

            SelectedList.addShape(GroupedShape);
            ShapesList.addShape(GroupedShape);

            Graphics2D graphics2D = paintCanvas.getGraphics2D();
            //For clearing paintCanvas for redraw shapes and border not a good solution because it adds a new layer on top
            //so OLD data is not erased from the ShapesList or the SelectedList
            //CLEARING for "Deselecting Purposes"
            graphics2D.clearRect(0,0,paintCanvas.getWidth(),paintCanvas.getHeight());

            //To redraw all shapes using Strategy Pattern (For removing Selected Black Outline cause some shapes are GROUPED)
            ShapesList.drawShapes();

            //ReDraw Selected Shapes Outline Borders (Red Borders around GROUPED shapes)
            SelectedList.DrawSelectedBorders();

            CommandHistory.add(this);

            System.out.println("Selected Shapes are GROUPED");
        }
        else{
            System.out.println("SelectedList is Empty!!!!!");
        }
    }

    @Override
    public void undo() {
        SelectedList.removeShape(GroupedShape);
        ShapesList.removeShape(GroupedShape);

        for(IShape shape: GroupedShape.getGroupedShapesList()){
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

        System.out.println("Undo--->Grouped Shapes are Ungrouped");
    }

    @Override
    public void redo() {

        GroupedShape = new GroupedShapesComposite(paintCanvas);

        //Add Shapes to GroupedList Composite
        for(IShape shape: SelectedList.getSelectedShapesList()) {
            GroupedShape.addShape(shape);
        }

        //Remove the Shapes that are Grouped from ShapesList & SelectedList
        for(IShape shape: GroupedShape.getGroupedShapesList()){

            //Remove the shape because going to group them into a Grouped Shape Composite
            ShapesList.removeShape(shape);

            //Remove the shapes because going to group them into a Grouped Shape Composite
            SelectedList.removeShape(shape);
        }

        SelectedList.addShape(GroupedShape);
        ShapesList.addShape(GroupedShape);

        Graphics2D graphics2D = paintCanvas.getGraphics2D();
        //For clearing paintCanvas for redraw shapes and border not a good solution because it adds a new layer on top
        //so OLD data is not erased from the ShapesList or the SelectedList
        //CLEARING for "Deselecting Purposes"
        graphics2D.clearRect(0,0,paintCanvas.getWidth(),paintCanvas.getHeight());

        //To redraw all shapes using Strategy Pattern (For removing Selected Black Outline cause some shapes are GROUPED)
        ShapesList.drawShapes();

        //ReDraw Selected Shapes Outline Borders (Red Borders around GROUPED shapes)
        SelectedList.DrawSelectedBorders();

        System.out.println("Redo--->Selected shapes are Grouped");
    }
}
