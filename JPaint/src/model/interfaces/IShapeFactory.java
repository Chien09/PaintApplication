package model.interfaces;

import model.Point;
import model.ShapeShadingType;
import model.ShapeType;

import view.interfaces.PaintCanvasBase;

import java.awt.*;

public interface IShapeFactory {
    IShape CreateShapeRectangle(PaintCanvasBase paintCanvas, Point minPoint, Point maxPoint, ShapeType ShapeTYPE, ShapeShadingType ShadeType, Color primaryColor, Color secondaryColor);
    IShape CreateShapeEllipse(PaintCanvasBase paintCanvas, Point minPoint, Point maxPoint, ShapeType ShapeTYPE, ShapeShadingType ShadeType, Color primaryColor, Color secondaryColor);
    IShape CreateShapeTriangle(PaintCanvasBase paintCanvas, Point minPoint, Point maxPoint, ShapeType ShapeTYPE, ShapeShadingType ShadeType, Color primaryColor, Color secondaryColor);
}
