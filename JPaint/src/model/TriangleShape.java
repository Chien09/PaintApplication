package model;

import model.interfaces.IShape;
import view.interfaces.PaintCanvasBase;

import java.awt.*;

public class TriangleShape implements IShape {

    private PaintCanvasBase paintCanvas;
    private ShapeShadingType ShadingType;
    private ShapeType ShapeTYPE;
    private Point minPoint;
    private Point maxPoint;
    private Color primaryColor;
    private Color secondaryColor;
    private int[] Xpoints = new int[3];
    private int[] Ypoints = new int[3];

    //MAYBE not need for drawing triangle shapes, but for select command mode to test collision.
    private int width;
    private int height;


    //Constructor
    public TriangleShape(PaintCanvasBase paintCanvas, Point minPoint, Point maxPoint, ShapeType ShapeTYPE, ShapeShadingType ShadingType, Color primaryColor, Color secondaryColor) {
        this.paintCanvas = paintCanvas;
        this.ShapeTYPE = ShapeTYPE;
        this.ShadingType = ShadingType;
        //Copy object, cause the pass in is reference Type Object
        Point CopyMinPoint = new Point(minPoint.getX(), minPoint.getY());
        Point CopyMaxPoint = new Point(maxPoint.getX(), maxPoint.getY());
        this.minPoint = CopyMinPoint;
        this.maxPoint = CopyMaxPoint;
        this.Xpoints[0] = this.minPoint.getX();
        this.Xpoints[1] = this.maxPoint.getX();
        this.Xpoints[2] = this.minPoint.getX();
        this.Ypoints[0] = this.minPoint.getY();
        this.Ypoints[1] = this.maxPoint.getY();
        this.Ypoints[2] = this.maxPoint.getY();

        //For collision when in select shape command
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
        this.maxPoint.UpdateAddX(deltaX);

        //Need to update again because Xpoints (Array of int[] Primitive Type)
        this.Xpoints[0] = this.minPoint.getX();
        this.Xpoints[1] = this.maxPoint.getX();
        this.Xpoints[2] = this.minPoint.getX();
    }

    @Override
    public void UpdateY(int deltaY) {
        this.minPoint.UpdateAddY(deltaY);
        this.maxPoint.UpdateAddY(deltaY);

        //Need to Assigned again because Ypoints (Array of int[] Primitive Type)
        this.Ypoints[0] = this.minPoint.getY();
        this.Ypoints[1] = this.maxPoint.getY();
        this.Ypoints[2] = this.maxPoint.getY();
    }

    @Override
    public void UndoUpdateX(int deltaX) {
        this.minPoint.UpdateMinusX(deltaX);
        this.maxPoint.UpdateMinusX(deltaX);

        //Need to update again because Xpoints (Array of int[] Primitive Type)
        this.Xpoints[0] = this.minPoint.getX();
        this.Xpoints[1] = this.maxPoint.getX();
        this.Xpoints[2] = this.minPoint.getX();
    }

    @Override
    public void UndoUpdateY(int deltaY) {
        this.minPoint.UpdateMinusY(deltaY);
        this.maxPoint.UpdateMinusY(deltaY);

        //Need to Assigned again because Ypoints (Array of int[] Primitive Type)
        this.Ypoints[0] = this.minPoint.getY();
        this.Ypoints[1] = this.maxPoint.getY();
        this.Ypoints[2] = this.maxPoint.getY();
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
        return this.Xpoints;
    }

    @Override
    public int[] getYPoints() {
        return this.Ypoints;
    }

}
