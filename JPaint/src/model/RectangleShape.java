package model;

import model.interfaces.IShape;
import view.interfaces.PaintCanvasBase;

import java.awt.*;

public class RectangleShape implements IShape {

    private PaintCanvasBase paintCanvas;
    private ShapeShadingType ShadingType;
    private ShapeType ShapeTYPE;
    private Point minPoint;
    private Point maxPoint;
    private int width;
    private int height;
    private Color primaryColor;
    private Color secondaryColor;

    //Constructor
    public RectangleShape(PaintCanvasBase paintCanvas, Point minPoint, Point maxPoint, ShapeType ShapeTYPE, ShapeShadingType ShadingType, Color primaryColor, Color secondaryColor){
        this.paintCanvas = paintCanvas;
        this.ShapeTYPE = ShapeTYPE;
        this.ShadingType = ShadingType;
        //Copy object, cause the pass in is reference Type Object
        Point CopyMinPoint = new Point(minPoint.getX(), minPoint.getY());
        Point CopyMaxPoint = new Point(maxPoint.getX(), maxPoint.getY());
        this.minPoint = CopyMinPoint;
        this.maxPoint = CopyMaxPoint;
        this.width = this.maxPoint.getX() - this.minPoint.getX();
        this.height = this.maxPoint.getY() - this.minPoint.getY();
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public Point getMinPoint() {
        return this.minPoint;
    }

    @Override
    public Point getMaxPoint() {
        return this.maxPoint;
    }

    @Override
    public void UpdateX(int deltaX) {
        this.minPoint.UpdateAddX(deltaX);
    }

    @Override
    public void UpdateY(int deltaY) {
        this.minPoint.UpdateAddY(deltaY);
    }

    @Override
    public void UndoUpdateX(int deltaX) {
        this.minPoint.UpdateMinusX(deltaX);
    }

    @Override
    public void UndoUpdateY(int deltaY) {
        this.minPoint.UpdateMinusY(deltaY);
    }

    @Override
    public ShapeShadingType getShadingType() {
        return this.ShadingType;
    }

    @Override
    public ShapeType getShapeType() {
        return this.ShapeTYPE;
    }

    @Override
    public PaintCanvasBase getPaintCanvas() {
        return this.paintCanvas;
    }

    @Override
    public Color getPrimaryColor() {
        return this.primaryColor;
    }

    @Override
    public Color getSecondaryColor() {
        return this.secondaryColor;
    }

    @Override
    public int[] getXPoints() {
        return new int[0];
    }

    @Override
    public int[] getYPoints() {
        return new int[0];
    }

}
