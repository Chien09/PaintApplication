package main;

import controller.IJPaintController;
import controller.JPaintController;
import controller.DrawMouseListener;
import model.CopiedClipBoard;
import model.SelectedShapesList;
import model.ShapeArrayList;
import model.persistence.ApplicationState;
import view.gui.Gui;
import view.gui.GuiWindow;
import view.gui.PaintCanvas;
import view.interfaces.IGuiWindow;
import view.interfaces.PaintCanvasBase;
import view.interfaces.IUiModule;

import java.awt.*;

public class Main {
    public static void main(String[] args){
        PaintCanvasBase paintCanvas = new PaintCanvas();
        IGuiWindow guiWindow = new GuiWindow(paintCanvas);
        IUiModule uiModule = new Gui(guiWindow);
        ApplicationState appState = new ApplicationState(uiModule);  //Getting current appstate the mode,shape,color,shading....

        //Create Master Shape List to store shapes
        ShapeArrayList ShapesList = new ShapeArrayList();

        //Create Selected Shape List to store selected shapes mode
        SelectedShapesList SelectedShapes = new SelectedShapesList();

        //Create Copy Clipboard of Shapes Selected
        CopiedClipBoard CopiedShapesList = new CopiedClipBoard();

        IJPaintController controller = new JPaintController(uiModule, appState, paintCanvas, ShapesList, SelectedShapes, CopiedShapesList);
        controller.setup();

        //MouseListener for triggering events caused by User Mouse
        paintCanvas.addMouseListener(new DrawMouseListener(paintCanvas, appState, ShapesList, SelectedShapes));

    }
}
