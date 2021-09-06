package model.interfaces;

import model.Point;
import model.ShapeShadingType;
import model.ShapeType;
import model.persistence.ApplicationState;
import view.interfaces.PaintCanvasBase;

import java.awt.*;

public interface IShape {

    int getWidth();

    int getHeight();

    Point getMinPoint();

    Point getMaxPoint();

    // Setting/updating new fields for Select/MoveShapeCommand to redraw/update shapes
    void UpdateX(int deltaX);
    void UpdateY(int deltaY);

    //For Undo Updating X, Y
    void UndoUpdateX(int deltaX);
    void UndoUpdateY(int deltaY);

    ShapeShadingType getShadingType();

    ShapeType getShapeType();

    PaintCanvasBase getPaintCanvas();

    Color getPrimaryColor();

    Color getSecondaryColor();

    //This is only for TRIANGLE Shape So might want to modify
    int[] getXPoints();
    int[] getYPoints();
}
