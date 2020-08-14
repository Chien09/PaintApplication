package model;

import model.interfaces.ICommand;
import model.interfaces.IShape;
import view.interfaces.PaintCanvasBase;

import java.awt.*;

public class SelectShapeCommand implements ICommand {

    private PaintCanvasBase paintCanvas;
    private ShapeArrayList ShapesList;
    private SelectedShapesList SelectedList;
    private Point startPoint;
    private Point endPoint;

    //Constructor
    public SelectShapeCommand(PaintCanvasBase paintCanvas, ShapeArrayList ShapesList, SelectedShapesList SelectedShapes, Point startPoint, Point endPoint){
        this.paintCanvas = paintCanvas;
        this.ShapesList = ShapesList;
        this.SelectedList = SelectedShapes;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    //ICommand
    @Override
    public void execute() {

        Graphics2D graphics2D = paintCanvas.getGraphics2D();

        if(SelectedList.getSelectedShapesList().isEmpty() == false) { //Check

            //clearing SelectedList from previous Select
            SelectedList.clearList();
            System.out.println("SelectedList Cleared");

            //For clearing paintCanvas for redraw shapes and border not a good solution because it adds a new layer on top
            //so OLD data is not erased from the ShapesList or the SelectedList
            //CLEARING for "Deselecting Purposes"
            graphics2D.clearRect(0,0,paintCanvas.getWidth(),paintCanvas.getHeight());

            //To redraw all shapes using Strategy Pattern
            ShapesList.drawShapes();
        }
        else{
            System.out.println("SelectedList is EMPTY");
        }

        for(IShape shape: ShapesList.getShapesList()){

            //Select Mouse Points
            Point SelectedMinPoint = new Point(Math.min(startPoint.getX(), endPoint.getX()), Math.min(startPoint.getY(), endPoint.getY()));
            Point SelectedMaxPoint = new Point(Math.max(startPoint.getX(), endPoint.getX()), Math.max(startPoint.getY(), endPoint.getY()));

            int SelectedWidth = SelectedMaxPoint.getX() - SelectedMinPoint.getX();
            int SelectedHeight = SelectedMaxPoint.getY() - SelectedMinPoint.getY();

            //Collision Detection-----------------------------------------------------------------------------------------------------
            Rectangle SelectedRectangle = new Rectangle(SelectedMinPoint.getX(), SelectedMinPoint.getY(), SelectedWidth, SelectedHeight);
            Rectangle OriginalShapeBorder = new Rectangle(shape.getMinPoint().getX(), shape.getMinPoint().getY(), shape.getWidth(), shape.getHeight());

            if(SelectedRectangle.intersects(OriginalShapeBorder)){
                System.out.println("There is Collision");
            //Collision Detection-----------------------------------------------------------------------------------------------------

                SelectedList.addShape(shape);
                System.out.println("Shape Added to SelectedShapeList");

                //Trigger of drawing border Strategy Pattern
                SelectedList.DrawSelectedBorders();
            }
            else{
                System.out.println("No Collision");
            }
        }
    }
}
