package model.persistence;

import model.*;
import model.dialogs.DialogProvider;
import model.interfaces.IApplicationState;
import model.interfaces.ICommand;
import model.interfaces.IDialogProvider;
import view.interfaces.IUiModule;
import view.interfaces.PaintCanvasBase;

import java.io.Serializable;

public class ApplicationState implements IApplicationState, Serializable {
    private static final long serialVersionUID = -5545483996576839009L;
    private final IUiModule uiModule;
    private final IDialogProvider dialogProvider;

    private ShapeType activeShapeType;
    private ShapeColor activePrimaryColor;
    private ShapeColor activeSecondaryColor;
    private ShapeShadingType activeShapeShadingType;
    private StartAndEndPointMode activeStartAndEndPointMode;

    public ApplicationState(IUiModule uiModule) {
        this.uiModule = uiModule;
        this.dialogProvider = new DialogProvider(this);
        setDefaults();
    }

    @Override
    public void setActiveShape() {
        activeShapeType = uiModule.getDialogResponse(dialogProvider.getChooseShapeDialog());
    }

    @Override
    public void setActivePrimaryColor() {
        activePrimaryColor = uiModule.getDialogResponse(dialogProvider.getChoosePrimaryColorDialog());
    }

    @Override
    public void setActiveSecondaryColor() {
        activeSecondaryColor = uiModule.getDialogResponse(dialogProvider.getChooseSecondaryColorDialog());
    }

    @Override
    public void setActiveShadingType() {
        activeShapeShadingType = uiModule.getDialogResponse(dialogProvider.getChooseShadingTypeDialog());
    }

    @Override
    public void setActiveStartAndEndPointMode() {
        activeStartAndEndPointMode = uiModule.getDialogResponse(dialogProvider.getChooseStartAndEndPointModeDialog());
    }

    @Override
    public void copy(SelectedShapesList SelectedShapes, CopiedClipBoard CopiedShapes) {
        ICommand CopyShapes = new CopyCommand(SelectedShapes, CopiedShapes);
        CopyShapes.execute();
    }

    @Override
    public void paste(ShapeArrayList ShapesList, CopiedClipBoard CopiedShapes) {
        ICommand PasteShapes = new PasteCommand(ShapesList, CopiedShapes);
        PasteShapes.execute();
    }

    @Override
    public void delete(PaintCanvasBase paintCanvas, ShapeArrayList ShapesList, SelectedShapesList SelectedList) {
        ICommand DeleteSelectedShapes = new DeleteCommand(paintCanvas, ShapesList, SelectedList);
        DeleteSelectedShapes.execute();
    }

    @Override
    public void clear(PaintCanvasBase paintCanvas, ShapeArrayList ShapesList, SelectedShapesList SelectedList, CopiedClipBoard CopiedList) {
        ICommand ClearPaintCanvas = new ClearCommand(paintCanvas, ShapesList, SelectedList, CopiedList);
        ClearPaintCanvas.execute();
    }

    @Override
    public void undo() {
        ICommand Undoing = new UndoCommand();
        Undoing.execute();
    }

    @Override
    public void redo() {
        ICommand Redoing = new RedoCommand();
        Redoing.execute();
    }

    @Override
    public void group(ShapeArrayList ShapesList, SelectedShapesList SelectedList) {
        ICommand Grouping = new GroupCommand(ShapesList, SelectedList);
        Grouping.execute();
    }

    @Override
    public void ungroup(ShapeArrayList ShapesList, SelectedShapesList SelectedList) {
        ICommand UnGrouping = new UnGroupCommand(ShapesList, SelectedList);
        UnGrouping.execute();
    }

    @Override
    public ShapeType getActiveShapeType() {
        return activeShapeType;
    }

    @Override
    public ShapeColor getActivePrimaryColor() {
        return activePrimaryColor;
    }

    @Override
    public ShapeColor getActiveSecondaryColor() {
        return activeSecondaryColor;
    }

    @Override
    public ShapeShadingType getActiveShapeShadingType() {
        return activeShapeShadingType;
    }

    @Override
    public StartAndEndPointMode getActiveStartAndEndPointMode() {
        return activeStartAndEndPointMode;
    }

    private void setDefaults() {
        activeShapeType = ShapeType.ELLIPSE;
        activePrimaryColor = ShapeColor.BLUE;
        activeSecondaryColor = ShapeColor.GREEN;
        activeShapeShadingType = ShapeShadingType.FILLED_IN;
        activeStartAndEndPointMode = StartAndEndPointMode.DRAW;
    }
}
