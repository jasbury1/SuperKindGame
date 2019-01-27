package superkind;

import edu.calpoly.spritely.MouseClickedHandler;
import edu.calpoly.spritely.Size;


public class InputHandler implements MouseClickedHandler{

    private SuperKindModel model;

    public InputHandler(SuperKindModel model) {
        this.model = model;
    }

    @Override
    public void mouseClicked(int x, int y){
        model.getTiles()[x][y].setClicked();
    }

}
