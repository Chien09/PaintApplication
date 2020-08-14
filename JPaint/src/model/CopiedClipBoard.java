package model;

import model.interfaces.IShape;

import java.util.ArrayList;

public class CopiedClipBoard {

    //Whenever a shape is Copied it is added here
    private ArrayList<IShape> CopiedShapes = new ArrayList<IShape>();

    public ArrayList<IShape> getCopiedShapesList() { return CopiedShapes;}

    public void CopyShape(IShape shape){
        CopiedShapes.add(shape);
    }

    public void clearList(){
        CopiedShapes.clear();
    }

}
