
package others.superkind;

import edu.calpoly.spritely.Size;

public interface GameModel  {

    /**
     * Attach an observer.  If the same observer is already attached, the
     * semantics of this method are undefined.
     */
    public void attach(GameObserver o);

    /**
     * Detach an observer.  Does nothing if that observer isn't present.
     */
    public void detach(GameObserver o);

    /**
     * Get a snapshot the current state of play at this moment in time.
     */
    public GameState getState();

    /**
     * Get the size of the board, that is, the number of squares
     * horizontally and vertically.  This never changes.
     */
    public Size getBoardSize();
}
