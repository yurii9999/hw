package group144.kuzmin;


/** Class describes game for two players */
public class Game {
    private static final int FIELD_SIZE = 3;
    private Cell[][] field;
    private Player turn;
    private State state;

    public Game() {
        field = new Cell[FIELD_SIZE][FIELD_SIZE];
        for (int i = 0; i < FIELD_SIZE; i++)
            for (int j = 0; j < FIELD_SIZE; j++)
                field[i][j] = Cell.Nothing;

        turn = Player.X;
        state = State.Playing;
    }

    /**
     * Method put a symbol of Player whose turn is on empty position of field
     *
     * @param row first coordinate of pos
     * @param column second coordinate of pos
     * @return true if symbol put
     * @return false if looking position is not empty
     */
    public boolean turn(int row, int column) {
        if (field[row][column] != Cell.Nothing || state != State.Playing)
            return false;

        field[row][column] = turn.toCell();
        turn = turn.opposite();
        updateState();
        return true;
    }

    /** @return Symbol of player that did last turn, as a String */
    public String getLastTurnedPlayersName() {return turn.opposite().toString();}

    /** @return Enum state that show what is going on in game */
    public String state() {return state.toString();}

    /**
     * Method check state and update of the game, it usually run after turn
     * Checks is one of players already won, or are all positions full and no one won
     */
    private void updateState() {
        for (int i = 0; i < FIELD_SIZE; i++) {
            boolean isXWonVertical = true;
            boolean isOWonVertical = true;
            boolean isXWonHorizontal = true;
            boolean isOWonHorizontal = true;

            for (int j = 0; j < FIELD_SIZE; j++) {
                isXWonHorizontal = isXWonHorizontal && field[i][j] == Cell.X;
                isOWonHorizontal = isOWonHorizontal && field[i][j] == Cell.O;

                isXWonVertical = isXWonVertical && field[j][i] == Cell.X;
                isOWonVertical = isOWonVertical && field[j][i] == Cell.O;
            }

            setState(isXWonHorizontal || isXWonVertical, isOWonHorizontal || isOWonVertical);
            if (state != State.Playing)
                return;
        }
        boolean isXWonDiagonal = true;
        boolean isOWonDiagonal = true;

        for (int i = 0; i < FIELD_SIZE; i++) {
            isXWonDiagonal = isXWonDiagonal && field[i][i] == Cell.X;
            isOWonDiagonal = isOWonDiagonal && field[i][i] == Cell.O;
        }

        setState(isXWonDiagonal, isOWonDiagonal);
        if (state != State.Playing)
            return;

        isXWonDiagonal = true;
        isOWonDiagonal = true;

        for (int i = 0; i < FIELD_SIZE; i++) {
            isXWonDiagonal = isXWonDiagonal && field[FIELD_SIZE - 1 - i][i] == Cell.X;
            isOWonDiagonal = isOWonDiagonal && field[FIELD_SIZE - 1 - i][i] == Cell.O;
        }

        setState(isXWonDiagonal, isOWonDiagonal);
        if (state != State.Playing)
            return;

        for (int i = 0; i < FIELD_SIZE; i++)
            for (int j = 0; j < FIELD_SIZE; j++)
                if (field[i][j] == Cell.Nothing)
                    return;

        state = State.Standoff;
    }

    /**
     * Update state if one of players won
     * @param isXWonCondition condition of X player victory
     * @param isOWonCondition condition of O player victory
     */
    private void setState(boolean isXWonCondition, boolean isOWonCondition) {
        if (isXWonCondition) {
            state = State.XWon;
            return;
        }

        if (isOWonCondition)
            state = State.OWon;
    }

    /** Enum of states of game */
    private enum State {
        Playing, XWon, OWon, Standoff
    }

    /** Enum of all thing that cell of field can become to */
    private enum Cell {X, O, Nothing}

    /** Enum of players */
    private enum Player {
        X, O;
        public Cell toCell() {return this == Player.X ? Cell.X : Cell.O;}
        public Player opposite() {return this == Player.X ? Player.O : Player.X;}
        }
    }
