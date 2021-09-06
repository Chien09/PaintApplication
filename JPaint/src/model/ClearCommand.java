package model;

import model.interfaces.ICommand;
import view.interfaces.PaintCanvasBase;

public class ClearCommand implements ICommand {

    private PaintCanvasBase paintCanvas;
    private ShapeArrayList ShapesList;
    private SelectedShapesList SelectedList;
    private CopiedClipBoard CopiedList;

    //Constructor
    public ClearCommand(PaintCanvasBase paintCanvas, ShapeArrayList ShapesList, SelectedShapesList SelectedList, CopiedClipBoard CopiedList){
        this.paintCanvas = paintCanvas;
        this.ShapesList = ShapesList;
        this.SelectedList = SelectedList;
        this.CopiedList = CopiedList;
    }

    @Override
    public void execute() {
        this.ShapesList.getShapesList().clear();
        this.SelectedList.getSelectedShapesList().clear();
        this.CopiedList.getCopiedShapesList().clear();

        //Works for clearing screen and drawing new shapes, but problem with redrawing the previous stored shapes
        this.paintCanvas.repaint();
    }
}
