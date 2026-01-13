package tictactoe.strategies;

import tictactoe.entities.Board;
import tictactoe.entities.Cell;
import tictactoe.entities.Player;
import tictactoe.enums.Symbol;

public class RowWinningStrategy implements WinningStrategy{
    public boolean checkWinner(Board board, Player player) {
        int size = board.getSize();
        Cell[][] grid = board.getGrid();
        Symbol symbol = player.getSymbol();
        for (int i = 0; i < size; i++) {
            int count = 0;
            for (int j = 0; j < size; j++) {
                if (grid[i][j].getSymbol() == symbol)
                    count++;
            }
            if (count == size)
                return true;
        }
        return false;
    }
}
