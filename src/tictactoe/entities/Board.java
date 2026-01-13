package tictactoe.entities;

import tictactoe.enums.Symbol;
import tictactoe.exceptions.InvalidMoveException;

public class Board {
    private int size;
    private Cell[][] grid;

    public Board(int size) {
        this.size = size;
        grid = new Cell[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = new Cell();
            }
        }
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public void setGrid(Cell[][] grid) {
        this.grid = grid;
    }

    public boolean isFull() {
        int no_blank = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (grid[i][j].getSymbol() == Symbol.BLANK)
                    no_blank++;
            }
        }

        if (no_blank == 0)
            return true;
        return false;

    }

    public void placeSymbol(Symbol symbol, int x, int y) {
        if (x < 0 || x >= size)
            throw new InvalidMoveException();

        if (y < 0 || y >= size)
            throw new InvalidMoveException();

        if (grid[x][y].getSymbol() != Symbol.BLANK)
            throw new InvalidMoveException();

        grid[x][y].setSymbol(symbol);
    }

    public void printBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(grid[i][j].getSymbol().getChar() + "  ");
            }
            System.out.println();
        }
    }

    public Cell getCell(int x, int y) {
        return grid[x][y];
    }
}
