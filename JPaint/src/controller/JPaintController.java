package controller;

import model.CopiedClipBoard;
import model.SelectedShapesList;
import model.ShapeArrayList;
import model.interfaces.IApplicationState;
import view.EventName;
import view.interfaces.IUiModule;
import view.interfaces.PaintCanvasBase;

public class JPaintController implements IJPaintController {
    private final IUiModule uiModule;
    private final IApplicationState applicationState;
    private SelectedShapesList SelectedShapes;
    private CopiedClipBoard CopiedShapes;
    private ShapeArrayList ShapesList;
    private PaintCanvasBase paintCanvas;


    public JPaintController(IUiModule uiModule, IApplicationState applicationState, PaintCanvasBase paintCanvas, ShapeArrayList ShapesList, SelectedShapesList SelectedShapes, CopiedClipBoard CopiedShapes) {
        this.uiModule = uiModule;
        this.applicationState = applicationState;
        this.paintCanvas = paintCanvas;
        this.SelectedShapes = SelectedShapes;
        this.CopiedShapes = CopiedShapes;
        this.ShapesList = ShapesList;
    }

    @Override
    public void setup() {
        setupEvents();
    }

    //Using Lambdas, understand what happens behind it addEvent/EvenName
    private void setupEvents() {
        uiModule.addEvent(EventName.CHOOSE_SHAPE, () -> applicationState.setActiveShape());
        uiModule.addEvent(EventName.CHOOSE_PRIMARY_COLOR, () -> applicationState.setActivePrimaryColor());
        uiModule.addEvent(EventName.CHOOSE_SECONDARY_COLOR, () -> applicationState.setActiveSecondaryColor());
        uiModule.addEvent(EventName.CHOOSE_SHADING_TYPE, () -> applicationState.setActiveShadingType());
        uiModule.addEvent(EventName.CHOOSE_START_POINT_ENDPOINT_MODE, () -> applicationState.setActiveStartAndEndPointMode());

        //Trigger of these Commands when clicked
        uiModule.addEvent(EventName.COPY, () -> applicationState.copy(SelectedShapes, CopiedShapes));
        uiModule.addEvent(EventName.PASTE, () -> applicationState.paste(ShapesList, CopiedShapes));
        uiModule.addEvent(EventName.DELETE, () -> applicationState.delete(paintCanvas, ShapesList, SelectedShapes));
        uiModule.addEvent(EventName.CLEAR, () -> applicationState.clear(paintCanvas, ShapesList, SelectedShapes, CopiedShapes));
        uiModule.addEvent(EventName.UNDO, () -> applicationState.undo());
        uiModule.addEvent(EventName.REDO, () -> applicationState.redo());
        uiModule.addEvent(EventName.GROUP, () -> applicationState.group(paintCanvas,ShapesList, SelectedShapes));
        uiModule.addEvent(EventName.UNGROUP, () -> applicationState.ungroup(paintCanvas, ShapesList, SelectedShapes));
    }
}
