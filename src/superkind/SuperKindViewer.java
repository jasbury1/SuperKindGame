package superkind;

import edu.calpoly.spritely.AnimationFrame;
import edu.calpoly.spritely.AnimationWindow;
import edu.calpoly.spritely.ImageTile;
import edu.calpoly.spritely.Size;
import edu.calpoly.spritely.SpriteWindow;

class SuperKindViewer{
    private SuperKindModel model;

    private SpriteWindow window;
    private AnimationFrame frame;

    static ImageTile rudeDude;
    static ImageTile superKind;
    static ImageTile logo;

    private final Size TILE_SIZE = new Size(100, 100);

    public SuperKindViewer(SuperKindModel model){
        this.model = model;
    }

    public void paintGameTile(int x, int y){
        if("superKind".equals(model.getTiles()[x][y].getTileType()))
            frame.addTile(x, y, superKind);
        else if("rudeDude".equals(model.getTiles()[x][y].getTileType()))
            frame.addTile(x, y, rudeDude);
        else
            frame.addTile(x, y, logo);
    }

    public void init(){
        window = new SpriteWindow("No Loud Music", model.getBoardSize());
        window.setTileSize(TILE_SIZE);
        window.setMouseClickedHandler(new InputHandler(model));
        frame = window.getInitialFrame();
        for(int x = 0; x < model.getBoardSize().width; ++x) {
            for(int y = 0; y < model.getBoardSize().height; y++) {
                paintGameTile(x, y);
            }
        }
        window.start();
    }

    public void run(){
        init();
        while(window.isRunning()){
            frame = window.waitForNextFrame();

            if(frame == null) {
                model.setGameOver();
                break;
            }

            if(model.isUpdated((window.getTimeSinceStart() / 1000))){
                for(int x = 0; x < model.getBoardSize().width; ++x) {
                    for(int y = 0; y < model.getBoardSize().height; ++y) {
                        paintGameTile(x, y);
                    }
                }
                window.showNextFrame();
            }

            if(model.isGameOver()) {
                window.stop();
            }
            if((window.getTimeSinceStart() / 1000) > model.getGameLengthSeconds()) {
                model.setGameWon(true);
                model.setGameOver();
                window.stop();
            }
        }
    }

}