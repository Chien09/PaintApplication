package view;

import model.ShapeType;
import model.interfaces.IShape;
import view.interfaces.IDrawStrategy;

import java.awt.*;

public class FilledInStrategy implements IDrawStrategy {

    @Override
    public void DrawShape(IShape shape) {

        Graphics2D graphics2D = shape.getPaintCanvas().getGraphics2D();

        if(shape.getShapeType() == ShapeType.RECTANGLE){
            graphics2D.setColor(shape.getPrimaryColor());
            graphics2D.fillRect(shape.getMinPoint().getX(), shape.getMinPoint().getY(), shape.getWidth(), shape.getHeight());
        }
        else if(shape.getShapeType() == ShapeType.ELLIPSE){
            graphics2D.setColor(shape.getPrimaryColor());
            graphics2D.fillOval(shape.getMinPoint().getX(), shape.getMinPoint().getY(), shape.getWidth(), shape.getHeight());
        }
        else { //this.shape.getShapeType() == ShapeType.TRIANGLE
            graphics2D.setColor(shape.getPrimaryColor());
            graphics2D.fillPolygon(shape.getXPoints(), shape.getYPoints(), 3);
        }
    }
}
