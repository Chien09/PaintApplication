package view;

import model.ShapeType;
import model.interfaces.IShape;
import view.interfaces.IDrawStrategy;

import java.awt.*;

public class FilledOutlineStrategy implements IDrawStrategy {

    @Override
    public void DrawShape(IShape shape) {

        Graphics2D graphics2D = shape.getPaintCanvas().getGraphics2D();

        if(shape.getShapeType() == ShapeType.RECTANGLE){
            //Inside Fill Shape
            graphics2D.setColor(shape.getPrimaryColor());
            graphics2D.fillRect(shape.getMinPoint().getX(), shape.getMinPoint().getY(), shape.getWidth(), shape.getHeight());

            //Outside Outline Shape
            graphics2D.setColor(shape.getSecondaryColor());
            graphics2D.setStroke(new BasicStroke(5));
            graphics2D.drawRect(shape.getMinPoint().getX(), shape.getMinPoint().getY(), shape.getWidth(), shape.getHeight());
        }
        else if(shape.getShapeType() == ShapeType.ELLIPSE){
            //Inside Fill Shape
            graphics2D.setColor(shape.getPrimaryColor());
            graphics2D.fillOval(shape.getMinPoint().getX(), shape.getMinPoint().getY(), shape.getWidth(), shape.getHeight());

            //Outside Outline Shape
            graphics2D.setColor(shape.getSecondaryColor());
            graphics2D.setStroke(new BasicStroke(5));
            graphics2D.drawOval(shape.getMinPoint().getX(), shape.getMinPoint().getY(), shape.getWidth(), shape.getHeight());
        }
        else { //shape.getShapeType() == ShapeType.TRIANGLE
            //Inside Fill Shape
            graphics2D.setColor(shape.getPrimaryColor());
            graphics2D.fillPolygon(shape.getXPoints(), shape.getYPoints(), 3);

            //Outside Outline Shape
            graphics2D.setColor(shape.getSecondaryColor());
            graphics2D.setStroke(new BasicStroke(5));
            graphics2D.drawPolygon(shape.getXPoints(), shape.getYPoints(), 3);
        }

    }
}
