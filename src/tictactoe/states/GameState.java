package tictactoe.states;

import tictactoe.entities.Game;
import tictactoe.entities.Player;

public interface GameState {
    public void makeMove(Game game, Player player, int x, int y);
}
