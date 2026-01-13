package tictactoe.states;

import tictactoe.entities.Game;
import tictactoe.entities.Player;
import tictactoe.exceptions.InvalidMoveException;

public class DrawState implements GameState {
    public void makeMove(Game game, Player player, int x, int y) {
        throw new InvalidMoveException();
    }
}
