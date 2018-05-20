package group144.kuzmin;

import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {
    @Test
    public void twiceTurnTest() {
        Game game = new Game();
        assertTrue(game.turn(0, 0));
        assertFalse(game.turn(0, 0));
    }

    @Test
    public void stateTest() {
        Game game = new Game();
        game.turn(0,0);
        assertEquals("PLAYING", game.state());
    }

    @Test
    public void lastTurnTest() {
        Game game = new Game();
        game.turn(0, 0);
        game.turn(1, 1);
        game.turn(2, 1);

        assertEquals("X", game.getLastTurnedPlayersName());
    }

    @Test
    public void winXTest() {
        Game game = new Game();
        game.turn(0, 0);
        game.turn(1, 0);
        game.turn(1, 1);
        game.turn(1, 2);
        game.turn(2, 2);

        assertEquals("X_WON", game.state());
    }
}