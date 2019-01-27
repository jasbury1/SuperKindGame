package superkind;

import others.superkind.GameState;
import others.superkind.BoardVisitor;

/**
 * An immutable snapshot of the Super Kind game state at one instant in
 * time.
 */
class SuperKindState implements GameState{
    SuperKindModel model;

    public SuperKindState(SuperKindModel model){
        this.model = model;
    }

    /**
     * Visit all the squares in the game board
     */
    public void accept(BoardVisitor v){
        for(int i = 0; i < model.getBoardSize().width; ++i) {
            for(int j = 0; j < model.getBoardSize().height; j++) {
                if(("rudeDude".equals(model.getTiles()[i][j].getTileType())) )
                    v.visitRudeDude(i, j);
                else if(("superKind".equals(model.getTiles()[i][j].getTileType())) )
                    v.visitSuperKind(i, j);
                else{
                    v.visitBackground(i, j);
                }
            }
        }
    }

    /**
     * Returns true iff the game is over
     */
    public boolean getGameOver(){
        return model.isGameOver();
    }
}