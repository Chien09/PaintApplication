package model;

import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.interfaces.IUndoRedo;
import model.persistence.ApplicationState;
import view.interfaces.PaintCanvasBase;

import java.awt.*;

public class CreateShapeCommand implements ICommand, IUndoRedo {

    private PaintCanvasBase paintCanvas;
    private ApplicationState appState;
    private ShapeArrayList ShapesList;
    private Point startPoint;
    private Point endPoint;

    //Empty ShapeFactory
    ShapeFactory ShapeCreated = new ShapeFactory();
    private IShape Shape;


    //Constructor
    public CreateShapeCommand(PaintCanvasBase paintCanvas, ApplicationState appState, Point startPoint, Point endPoint, ShapeArrayList ShapesList){
        this.paintCanvas = paintCanvas;
        this.appState = appState;
        this.ShapesList = ShapesList;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    //ICommand
    @Override
    public void execute() {

        ShapeType ShapeTYPE = appState.getActiveShapeType();
        ShapeShadingType ShadingType = appState.getActiveShapeShadingType();

        ColorTransition PrimaryColorConverter = new ColorTransition(appState.getActivePrimaryColor());
        Color primaryColor = PrimaryColorConverter.getColor();

        ColorTransition SecondaryColorConverter = new ColorTransition(appState.getActiveSecondaryColor());
        Color secondaryColor = SecondaryColorConverter.getColor();

        //Setting up points for Drawing Shapes (Need to Consider when clicking the mouse from right to left X,Y also)
        Point minPoint = new Point(Math.min(startPoint.getX(), endPoint.getX()), Math.min(startPoint.getY(), endPoint.getY()));
        Point maxPoint = new Point(Math.max(startPoint.getX(), endPoint.getX()), Math.max(startPoint.getY(), endPoint.getY()));


        //Trigger of Abstract Factory
        if(appState.getActiveShapeType() == ShapeType.RECTANGLE){
            Shape = ShapeCreated.CreateShapeRectangle(paintCanvas, minPoint, maxPoint, ShapeTYPE, ShadingType, primaryColor, secondaryColor);
        }
        else if(appState.getActiveShapeType() == ShapeType.ELLIPSE){
            Shape = ShapeCreated.CreateShapeEllipse(paintCanvas, minPoint, maxPoint, ShapeTYPE, ShadingType, primaryColor, secondaryColor);
        }
        else {//if(appState.getActiveShapeType() == ShapeType.TRIANGLE)
            Shape = ShapeCreated.CreateShapeTriangle(paintCanvas, minPoint, maxPoint, ShapeTYPE, ShadingType, primaryColor, secondaryColor);
        }

        //Storing Shapes that are drawn on the screen
        if(Shape != null) { //for checking
            ShapesList.addShape(Shape);
        }

        //To draw all shapes using Strategy Pattern
        ShapesList.drawShapes();

        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        ShapesList.removeShape(Shape);
        System.out.println("Undo--->removing shape from ShapesList");

        Graphics2D graphics2D = paintCanvas.getGraphics2D();

        //For clearing paintCanvas for redraw shapes and border not a good solution because it adds a new layer on top
        //so OLD data is not erased from the ShapesList or the SelectedList
        graphics2D.clearRect(0,0,paintCanvas.getWidth(),paintCanvas.getHeight());

        //To draw all shapes using Strategy Pattern
        ShapesList.drawShapes();
    }

    @Override
    public void redo() {
        ShapesList.addShape(Shape);
        System.out.println("Redo--->adding shape back to ShapesList");

        //To draw all shapes using Strategy Pattern
        ShapesList.drawShapes();
    }
}
