package model;

import model.interfaces.IShape;
import model.interfaces.IShapeFactory;
import view.interfaces.PaintCanvasBase;

import java.awt.*;

public class ShapeFactory implements IShapeFactory{

    @Override
    public IShape CreateShapeRectangle(PaintCanvasBase paintCanvas, Point minPoint, Point maxPoint, ShapeType ShapeTYPE, ShapeShadingType ShadeType, Color primaryColor, Color secondaryColor) {
        return new RectangleShape(paintCanvas, minPoint, maxPoint, ShapeTYPE, ShadeType, primaryColor, secondaryColor);
    }

    @Override
    public IShape CreateShapeEllipse(PaintCanvasBase paintCanvas, Point minPoint, Point maxPoint, ShapeType ShapeTYPE, ShapeShadingType ShadeType, Color primaryColor, Color secondaryColor) {
        return new EllipseShape(paintCanvas, minPoint, maxPoint, ShapeTYPE, ShadeType, primaryColor, secondaryColor);
    }

    @Override
    public IShape CreateShapeTriangle(PaintCanvasBase paintCanvas, Point minPoint, Point maxPoint, ShapeType ShapeTYPE, ShapeShadingType ShadeType, Color primaryColor, Color secondaryColor) {
        return new TriangleShape(paintCanvas, minPoint, maxPoint, ShapeTYPE, ShadeType, primaryColor, secondaryColor);
    }
}
