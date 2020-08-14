package model;

import model.interfaces.ICommand;

public class UnGroupCommand implements ICommand {

    private ShapeArrayList ShapesList;
    private SelectedShapesList SelectedList;

    public UnGroupCommand(ShapeArrayList ShapesList, SelectedShapesList SelectedList){
        this.SelectedList = SelectedList;
        this.ShapesList = ShapesList;
    }

    @Override
    public void execute() {

    }
}
