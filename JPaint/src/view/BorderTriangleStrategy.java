package view;

import model.interfaces.IShape;
import view.interfaces.IBorderStrategy;

import java.awt.*;

public class BorderTriangleStrategy implements IBorderStrategy {

    @Override
    public void DrawBorder(IShape shape) {

        Graphics2D graphics2D = shape.getPaintCanvas().getGraphics2D();

        //Drawing border around the selected shape
        Stroke stroke = new BasicStroke(4, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
        graphics2D.setStroke(stroke);
        graphics2D.setColor(Color.BLACK);
        
        int[] XArrayPoints = shape.getXPoints();
        int[] YArrayPoints = shape.getYPoints();

        XArrayPoints[0] = XArrayPoints[0] - 5;
        XArrayPoints[1] = XArrayPoints[1] + 20;
        XArrayPoints[2] = XArrayPoints[2] - 5;

        YArrayPoints[0] = YArrayPoints[0] - 10;
        YArrayPoints[1] = YArrayPoints[1] + 5;
        YArrayPoints[2] = YArrayPoints[2] + 5;

        graphics2D.drawPolygon(XArrayPoints, YArrayPoints, 3);
    }
}
