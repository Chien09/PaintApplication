package view;

import model.interfaces.IShape;
import view.interfaces.IBorderStrategy;

import java.awt.*;

public class BorderRectangleStrategy implements IBorderStrategy {

    @Override
    public void DrawBorder(IShape shape) {
        Graphics2D graphics2D = shape.getPaintCanvas().getGraphics2D();

        //Drawing border around the selected shape
        Stroke stroke = new BasicStroke(4, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
        graphics2D.setStroke(stroke);
        graphics2D.setColor(Color.BLACK);

        graphics2D.drawRect(shape.getMinPoint().getX() - 5, shape.getMinPoint().getY() - 5, shape.getWidth() + 10, shape.getHeight() + 10);
    }
}
