package controller;

import model.*;
import model.Point;
import model.interfaces.ICommand;
import model.persistence.ApplicationState;
import view.interfaces.PaintCanvasBase;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DrawMouseListener extends MouseAdapter {

    //For storing mouse clicked/release points
    private Point startPoint;
    private Point endPoint;

    private PaintCanvasBase paintCanvas;
    private ApplicationState appState; //for checking the user chosen state (shape, primary color, secondary color, shading type, mode)
    private ShapeArrayList ShapesList;
    private SelectedShapesList SelectedShapes;

    //Constructor
    public DrawMouseListener(PaintCanvasBase paintCanvas, ApplicationState appState, ShapeArrayList ShapesList, SelectedShapesList SelectedShapes){
        this.paintCanvas = paintCanvas;
        this.appState = appState;
        this.ShapesList = ShapesList;
        this.SelectedShapes = SelectedShapes;
    }

    //When Mouse Is Clicked
    @Override
    public void mousePressed(MouseEvent e) {

        //Get the Mouse first Clicked Points x,y
        startPoint = new Point(e.getX(), e.getY());
    }

    //When Mouse is Released
    @Override
    public void mouseReleased(MouseEvent e) {

        //Get the MouseReleased Points x,y
        endPoint = new Point(e.getX(), e.getY());

        //Check which MODE appState selected
        if(appState.getActiveStartAndEndPointMode() == StartAndEndPointMode.DRAW){

            ICommand CreateShape = new CreateShapeCommand(paintCanvas, appState, startPoint, endPoint, ShapesList);
            CreateShape.execute();
        }
        else if(appState.getActiveStartAndEndPointMode() == StartAndEndPointMode.SELECT){

            ICommand SelectShape = new SelectShapeCommand(paintCanvas, ShapesList, SelectedShapes, startPoint, endPoint);
            SelectShape.execute();
        }
        else {  //if appState.getActiveStartAndEndPointMode() == StartAndEndPointMode.MOVE

            ICommand MoveShape = new MoveShapeCommand(paintCanvas, ShapesList, SelectedShapes, startPoint, endPoint);
            MoveShape.execute();
        }
    }
}
