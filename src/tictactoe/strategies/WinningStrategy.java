package tictactoe.strategies;

import tictactoe.entities.Board;
import tictactoe.entities.Player;

public interface WinningStrategy {
    public boolean checkWinner(Board board, Player player);
}
