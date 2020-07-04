package controller;

import model.Point;
import view.gui.PaintCanvas;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyMouseListener extends MouseAdapter {

    //For storing mouse clicked points
    private Point startPoint;
    private Point endPoint;

    private PaintCanvasBase paintCanvas; //Storing passed over paintCanvas

    //Constructor
    public MyMouseListener(PaintCanvasBase paintCanvas){
        this.paintCanvas = paintCanvas;
    }

    //When Mouse Is Clicked
    @Override
    public void mousePressed(MouseEvent e) {
        startPoint = new Point(e.getX(), e.getY());
    }

    //When Mouse is Released draw the rectangle
    @Override
    public void mouseReleased(MouseEvent e) {
        //Create Rectangle
        endPoint = new Point(e.getX(), e.getY());

        Point minPoint = new Point(Math.min(startPoint.getX(), endPoint.getX()), Math.min(startPoint.getY(), endPoint.getY()));
        Point maxPoint = new Point(Math.max(startPoint.getX(), endPoint.getX()), Math.max(startPoint.getY(), endPoint.getY()));

        int width = maxPoint.getX() - minPoint.getX();
        int height = maxPoint.getY() - minPoint.getY();

        Graphics2D graphics2d = paintCanvas.getGraphics2D();
        graphics2d.setColor(Color.BLUE);

        //Draw Rectangle
        graphics2d.fillRect(minPoint.getX(), minPoint.getY(), width, height);
    }
}
