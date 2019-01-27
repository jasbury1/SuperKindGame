
package others.superkind;

/**
 * An immutable snapshot of the Super Kind game state at one instant in
 * time.
 */
public interface GameState {

    /**
     * Visit all the squares in the game board
     */
    public void accept(BoardVisitor v);

    /**
     * Returns true iff the game is over
     */
    public boolean getGameOver();
}
