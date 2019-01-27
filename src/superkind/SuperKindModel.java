package superkind;

import edu.calpoly.spritely.Size;
import edu.calpoly.spritely.ImageTile;

import others.superkind.GameModel;
import others.superkind.GameObserver;
import others.superkind.GameState;

import java.util.Random;
import java.util.ArrayList;

class SuperKindModel implements GameModel{

    private ArrayList<GameObserver> observers = new ArrayList<GameObserver>();

    private boolean gameOver = false;
    private boolean gameWon = false;

    private final Size GAME_SIZE = new Size(6, 5);

    private GameTile[][] tiles = new GameTile[6][5];

    private double gameLengthSeconds;
    private int numRudeDudes = 0;

    //CONSTRUCTOR
    public SuperKindModel(double gameLengthSeconds){
        this.gameLengthSeconds = gameLengthSeconds;
    }

    public void initTiles() {
        Random r = new Random();
        double min = gameLengthSeconds * .05;
        double max = gameLengthSeconds - min;
        double randomValue;

        for(int i = 0; i < 6; ++i) {
            for(int j = 0; j < 5; ++j) {
                randomValue = min + (max - min) * r.nextDouble();
                tiles[i][j] = new GameTile();
                tiles[i][j].setChangeTime(randomValue);
            }
        }
    }

    public boolean isUpdated(double currentTime){
        boolean hasUpdate = false;
        for(int i = 0; i < GAME_SIZE.width; ++i) {
            for(int j = 0; j < GAME_SIZE.height; ++j) {
                if((tiles[i][j].getChangeTime() <= currentTime) && ("logo".equals(tiles[i][j].getTileType())) ) {
                    tiles[i][j].advanceState();
                    ++numRudeDudes;
                    hasUpdate = true;
                    notifyObservers();
                }
                if(tiles[i][j].checkForClick() && !("superKind".equals(tiles[i][j].getTileType())) ) {
                    tiles[i][j].advanceState();
                    --numRudeDudes;
                    hasUpdate = true;
                }
            }
        }
        if(hasUpdate){
            notifyObservers();
        }
        return hasUpdate;
    }


    public void notifyObservers(){
        for(GameObserver o : observers){
            o.update();
        }
    }


    /**
     * Attach an observer.  If the same observer is already attached, the
     * semantics of this method are undefined.
     */
    public void attach(GameObserver o){
        observers.add(o);
    }

    /**
     * Detach an observer.  Does nothing if that observer isn't present.
     */
    public void detach(GameObserver o){
        observers.remove(o);
    }

    /**
     * Get a snapshot the current state of play at this moment in time.
     */
    public GameState getState(){
        SuperKindState state = new SuperKindState(this);
        return state;
    }

    /**
     * Get the size of the board, that is, the number of squares
     * horizontally and vertically.  This never changes.
     */
    public Size getBoardSize(){
        return GAME_SIZE;
    }

    public GameTile[][] getTiles() {
        return tiles;
    }

    public double getGameLengthSeconds() {
        return gameLengthSeconds;
    }

    public int getNumRudeDudes(){
        return numRudeDudes;
    }

    public boolean isGameOver() {
        if(numRudeDudes >= 6){
            setGameOver();
        }
        return gameOver;
    }

    public boolean isGameWon() {
        return gameWon;
    }

    public void setGameOver(){
        this.gameOver = true;
        notifyObservers();
    }

    public void setGameWon(boolean gameWon) {
        this.gameWon = gameWon;
        notifyObservers();
    }

}