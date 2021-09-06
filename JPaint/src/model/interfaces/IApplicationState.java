package model.interfaces;

import model.*;
import view.interfaces.PaintCanvasBase;

public interface IApplicationState {
    void setActiveShape();

    void setActivePrimaryColor();

    void setActiveSecondaryColor();

    void setActiveShadingType();

    void setActiveStartAndEndPointMode();

    void copy(SelectedShapesList SelectedShapes, CopiedClipBoard CopiedShapes);

    void paste(ShapeArrayList ShapesList, CopiedClipBoard CopiedShapes);

    void delete(PaintCanvasBase paintCanvas, ShapeArrayList ShapesList, SelectedShapesList SelectedList);

    void clear(PaintCanvasBase paintCanvas, ShapeArrayList ShapesList, SelectedShapesList SelectedList, CopiedClipBoard CopiedList);

    void undo();

    void redo();

    void group(PaintCanvasBase paintCanvas, ShapeArrayList ShapesList, SelectedShapesList SelectedList);

    void ungroup(PaintCanvasBase paintCanvas, ShapeArrayList ShapesList, SelectedShapesList SelectedList);

    ShapeType getActiveShapeType();

    ShapeColor getActivePrimaryColor();

    ShapeColor getActiveSecondaryColor();

    ShapeShadingType getActiveShapeShadingType();

    StartAndEndPointMode getActiveStartAndEndPointMode();
}
