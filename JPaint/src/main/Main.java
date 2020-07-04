package main;

import controller.IJPaintController;
import controller.JPaintController;
import controller.MyMouseListener;
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
        ApplicationState appState = new ApplicationState(uiModule);  //Getting current state the mode,shape,color,shadding....
        IJPaintController controller = new JPaintController(uiModule, appState);
        controller.setup();


        //Add MouseListener to paintCanvas and pass the paintCanvas into MouseListener
        paintCanvas.addMouseListener(new MyMouseListener(paintCanvas));

        // For example purposes only; remove all lines below from your final project.

//       try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        //Create the 2D object drawing plane
        //Graphics2D graphics2d = paintCanvas.getGraphics2D();

        //Draw fill-in Green Rectangle
        //graphics2d.setColor(Color.GREEN);
        //graphics2d.fillRect(12, 13, 200, 400);

        //Draw Outlined blue rectangle
        //graphics2d.setStroke(new BasicStroke(5));
        //graphics2d.setColor(Color.BLUE);
        //graphics2d.drawRect(12, 13, 200, 400);

        // Selected Shape
        //Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
        //graphics2d.setStroke(stroke);
        //graphics2d.setColor(Color.BLACK);
        //graphics2d.drawRect(7, 8, 210, 410);

        // Clears the Canvas
        //paintCanvas.repaint();
    }
}
