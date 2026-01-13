package tictactoe.strategies;

import tictactoe.entities.Board;
import tictactoe.entities.Cell;
import tictactoe.entities.Player;
import tictactoe.enums.Symbol;

public class DiagonalWinningStrategy implements WinningStrategy {
    public boolean checkWinner(Board board, Player player) {
        int size = board.getSize();
        Cell[][] grid = board.getGrid();
        Symbol symbol = player.getSymbol();
        int i = 0;
        int j = 0;
        int cnt = 0;
        while (i < size && j < size) {
            if (grid[i][j].getSymbol() == symbol)
                cnt++;
            i++;
            j++;
        }
        if (cnt == size)
            return true;

        cnt = 0;
        i = 0;
        j = size - 1;
        while (i < size && j >= 0) {
            if (grid[i][j].getSymbol() == symbol)
                cnt++;
            i++;
            j--;
        }
        if (cnt == size)
            return true;
        return false;
    }
}
