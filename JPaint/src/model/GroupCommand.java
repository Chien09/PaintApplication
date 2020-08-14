package model;

import model.interfaces.ICommand;
import model.interfaces.IShape;

public class GroupCommand implements ICommand {

    private ShapeArrayList ShapesList;
    private SelectedShapesList SelectedList;
    private GroupedShapesComposite GroupedShape;

    public GroupCommand(ShapeArrayList ShapesList, SelectedShapesList SelectedList){
        this.SelectedList = SelectedList;
        this.ShapesList = ShapesList;
    }

    @Override
    public void execute() {

        GroupedShape = new GroupedShapesComposite(ShapesList, SelectedList);

        if(SelectedList.getSelectedShapesList().isEmpty() == false) { //Check

            for (IShape shape: SelectedList.getSelectedShapesList()) {

                GroupedShape.addShape(shape);

                //Remove the shapes because going to group them into a Grouped Shape Composite
                ShapesList.removeShape(shape);

            }

            //Clear SelectedList to add the Grouped Shape Composite
            SelectedList.clearList();

            //Add the Grouped Shapes Composite to SelectedList
            SelectedList.addShape(GroupedShape);

            //Add the Grouped Shapes Composite to MasterList
            ShapesList.addShape(GroupedShape);

            System.out.println("Selected Shapes are GROUPED");
        }
    }
}
