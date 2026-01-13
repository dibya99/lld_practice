package tictactoe.entities;

import tictactoe.states.GameState;
import tictactoe.states.InProgressState;
import tictactoe.strategies.ColumnWinningStrategy;
import tictactoe.strategies.DiagonalWinningStrategy;
import tictactoe.strategies.RowWinningStrategy;
import tictactoe.strategies.WinningStrategy;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Player winner;

    private GameState gameState;
    private List<WinningStrategy> winningStrategyList;

    public Game(Player player1, Player player2) {
        board = new Board(3);
        this.player1 = player1;
        this.player2 = player2;
        currentPlayer = player1;
        winner = null;

        gameState = new InProgressState();
        winningStrategyList = new ArrayList<>();
        winningStrategyList.add(new RowWinningStrategy());
        winningStrategyList.add(new ColumnWinningStrategy());
        winningStrategyList.add(new DiagonalWinningStrategy());

    }

    void switchPlayer() {
        if (currentPlayer == player1)
            currentPlayer = player2;
        else
            currentPlayer = player1;
    }


    public void makeMove(int x, int y) {
        this.gameState.makeMove(this, currentPlayer, x, y);
        switchPlayer();
    }

    public boolean checkWinner(Player player) {
        for (WinningStrategy winningStrategy : winningStrategyList) {
            if (winningStrategy.checkWinner(board, player))
                return true;
        }
        return false;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public List<WinningStrategy> getWinningStrategyList() {
        return winningStrategyList;
    }

    public void setWinningStrategyList(List<WinningStrategy> winningStrategyList) {
        this.winningStrategyList = winningStrategyList;
    }
}
