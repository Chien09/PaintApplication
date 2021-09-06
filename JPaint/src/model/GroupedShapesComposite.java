package model;

import model.interfaces.IShape;
import view.*;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.util.ArrayList;

public class GroupedShapesComposite implements IShape {

    private PaintCanvasBase paintCanvas;
    private ArrayList<IShape> GroupedShapesList = new ArrayList<IShape>();

    public GroupedShapesComposite(PaintCanvasBase paintCanvas){
        this.paintCanvas = paintCanvas;
    }

    public void addShape(IShape shape){
        GroupedShapesList.add(shape);
    }

    public void SetGroupedShapesList(ArrayList<IShape> arraylist){
        GroupedShapesList = arraylist;
    }

    public ArrayList<IShape> getGroupedShapesList(){
        return GroupedShapesList;
    }

    //drawShapes uses Strategy Pattern
    public void drawGroupedShapes(){
        for(IShape shape: GroupedShapesList){
            DrawShapes DrawStrategy;

            if (shape.getShadingType() == ShapeShadingType.OUTLINE) {
                DrawStrategy = new DrawShapes(new OutlineStrategy());
                DrawStrategy.executeStrategy(shape);
            } else if (shape.getShadingType() == ShapeShadingType.FILLED_IN) {
                DrawStrategy = new DrawShapes(new FilledInStrategy());
                DrawStrategy.executeStrategy(shape);
            } else { //shape.getShadingType() == ShapeShadingType.OUTLINE_AND_FILLED_IN
                DrawStrategy = new DrawShapes(new FilledOutlineStrategy());
                DrawStrategy.executeStrategy(shape);
            }
        }
    }


    public void drawSelectedGroupBorders(){

        Graphics2D graphics2D = paintCanvas.getGraphics2D();
        Stroke stroke = new BasicStroke(4, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
        graphics2D.setStroke(stroke);
        graphics2D.setColor(Color.RED);

        for(IShape shape: GroupedShapesList){
            if (shape.getShapeType() == ShapeType.RECTANGLE) {

                graphics2D.drawRect(shape.getMinPoint().getX() - 5, shape.getMinPoint().getY() - 5, shape.getWidth() + 10, shape.getHeight() + 10);

            } else if (shape.getShapeType() == ShapeType.ELLIPSE) {

                graphics2D.drawOval(shape.getMinPoint().getX() - 5, shape.getMinPoint().getY() - 5, shape.getWidth() + 10, shape.getHeight() + 10);

            } else { //shape.getShapeType() == ShapeType.TRIANGLE

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
    }

    @Override
    public int getWidth() {

        int TmpWidth = 0;

        for(IShape shape: GroupedShapesList){
            TmpWidth = TmpWidth + shape.getWidth();
        }

        return TmpWidth;
    }

    @Override
    public int getHeight() {
        int TmpHeight = 0;

        for(IShape shape: GroupedShapesList){
            TmpHeight = TmpHeight + shape.getWidth();
        }

        return TmpHeight;
    }

    @Override
    public Point getMinPoint() {
        int TmpMinXPointArray[] = new int[GroupedShapesList.size()];
        int TmpMinYPointArray[] = new int[GroupedShapesList.size()];
        int TmpMinX;
        int TmpMinY;

        for(IShape shape: GroupedShapesList){
            addElement(TmpMinXPointArray, shape.getMinPoint().getX()); //Storing Min X points
            addElement(TmpMinYPointArray, shape.getMinPoint().getY()); //Storing Min Y points
        }

        TmpMinX = smallestPoint(TmpMinXPointArray);
        TmpMinY = smallestPoint(TmpMinYPointArray);

        Point TmpPoint = new Point(TmpMinX, TmpMinY);

        return TmpPoint;
    }

    //Adding int element
    private static void addElement(int[] array, int XorY){
        for(int i=0; i < array.length; i++)
            if(array[i] == 0) {
                array[i] = XorY;
                break;
            }
    }

    //finding the smallest min point
    private static int smallestPoint(int [] array) {
        int min = array[0];

        for(int i = 0; i<array.length; i++ ) {
            if(array[i]<min) {
                min = array[i];
            }
        }
        return min;
    }

    @Override
    public Point getMaxPoint() {
        int TmpMaxXPointArray[] = new int[GroupedShapesList.size()];
        int TmpMaxYPointArray[] = new int[GroupedShapesList.size()];
        int TmpMaxX;
        int TmpMaxY;

        for(IShape shape: GroupedShapesList){
            addElement(TmpMaxXPointArray, shape.getMaxPoint().getX()); //Storing Max X points
            addElement(TmpMaxYPointArray, shape.getMaxPoint().getY()); //Storing Max Y points
        }

        TmpMaxX = largestPoint(TmpMaxXPointArray);
        TmpMaxY = largestPoint(TmpMaxYPointArray);

        Point TmpPoint = new Point(TmpMaxX, TmpMaxY);

        return TmpPoint;
    }

    //finding the largest min point
    private static int largestPoint(int [] array) {
        int max = 0;

        for(int i=0; i<array.length; i++ ) {
            if(array[i]>max) {
                max = array[i];
            }
        }
        return max;
    }

    @Override
    public void UpdateX(int deltaX) {
        for(IShape shape: GroupedShapesList) {
            shape.UpdateX(deltaX);
        }
    }

    @Override
    public void UpdateY(int deltaY) {
        for(IShape shape: GroupedShapesList) {
            shape.UpdateY(deltaY);
        }
    }

    @Override
    public void UndoUpdateX(int deltaX) {
        for(IShape shape: GroupedShapesList) {
            shape.UndoUpdateX(deltaX);
        }
    }

    @Override
    public void UndoUpdateY(int deltaY) {
        for(IShape shape: GroupedShapesList) {
            shape.UndoUpdateY(deltaY);
        }
    }

    @Override
    public ShapeShadingType getShadingType() {
        return null;
    }

    @Override
    public ShapeType getShapeType() {
        return null;
    }

    @Override
    public PaintCanvasBase getPaintCanvas() {
        return null;
    }

    @Override
    public Color getPrimaryColor() {
        return null;
    }

    @Override
    public Color getSecondaryColor() {
        return null;
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
