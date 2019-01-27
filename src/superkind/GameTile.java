package superkind;

import edu.calpoly.spritely.ImageTile;

public class GameTile {
    private double changeTime;
    private boolean isClicked = false;
    private String tileType;

    public GameTile() {
        this.tileType = "logo";
    }

    public void setClicked() {
        if("rudeDude".equals(tileType)) {
            isClicked = true;
        }
    }

    public boolean checkForClick() {
        return isClicked;
    }

    public void advanceState() {
        if("logo".equals(tileType)) {
            tileType = "rudeDude";
        }
        else if("rudeDude".equals(tileType)) {
            tileType = "superKind";
        }
    }

    //GETTERS AND SETTERS:t

    public String getTileType(){
        return tileType;
    }

    public void setTileType(String newType){
        this.tileType = newType;
    }

    public void setChangeTime(double time) {
        changeTime = time;
    }

    public double getChangeTime() {
        return changeTime;
    }

}
