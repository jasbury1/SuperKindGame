package superkind;

import edu.calpoly.spritely.Size;
import edu.calpoly.spritely.ImageTile;

import java.io.IOException;
import java.io.File;

import others.superkind.GameModel;

final class SuperKindGame {
    public static final Size TILE_SIZE = new Size(100, 100);
    private SuperKindModel gameModel;
    private SuperKindViewer gameViewer;

    private double gameLengthSeconds;

    //CONSTRUCTOR
    public SuperKindGame(double seconds) {
        this.gameLengthSeconds = seconds;
        this.gameModel = new SuperKindModel(seconds);
        gameModel.initTiles();
        gameViewer = new SuperKindViewer(gameModel);
    }

    public static void loadImages() throws IOException {
        SuperKindViewer.rudeDude = new ImageTile(new File("images/rude_dude.jpg"), TILE_SIZE, 'r');
        SuperKindViewer.superKind = new ImageTile(new File("images/super_kind.jpg"), TILE_SIZE, 'r');
        SuperKindViewer.logo = new ImageTile(new File("images/LACMTA-Logo.png"), TILE_SIZE, 'r');
    }

    public boolean play() {

        gameViewer.run();
        return gameModel.isGameWon();

    }

    //GETTERS AND SETTERS
    public GameModel getModel() {
        return gameModel;
    }

}
