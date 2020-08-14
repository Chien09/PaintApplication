package model;

import model.interfaces.IShape;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.util.ArrayList;

public class GroupedShapesComposite implements IShape {

    private ShapeArrayList ShapesList;
    private SelectedShapesList SelectedList;
    private ArrayList<IShape> GroupedShapesList = new ArrayList<IShape>();

    public GroupedShapesComposite(ShapeArrayList ShapesList, SelectedShapesList SelectedList){
        this.ShapesList = ShapesList;
        this.SelectedList = SelectedList;
    }

    public void addShape(IShape shape){
        GroupedShapesList.add(shape);
    }

    public void ClearGroupedList(IShape shape){
        GroupedShapesList.clear();
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public Point getMinPoint() {
        return null;
    }

    @Override
    public Point getMaxPoint() {
        return null;
    }

    @Override
    public void UpdateX(int deltaX) {

    }

    @Override
    public void UpdateY(int deltaY) {

    }

    @Override
    public void UndoUpdateX(int deltaX) {

    }

    @Override
    public void UndoUpdateY(int deltaY) {

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
