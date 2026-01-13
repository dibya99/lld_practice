package tictactoe.states;

import tictactoe.entities.Game;
import tictactoe.entities.Player;
import tictactoe.enums.Symbol;

public class InProgressState implements GameState {
    public void makeMove(Game game, Player player, int x, int y) {
        System.out.println("Player: " + player.getName() + " plays");
        game.getBoard().placeSymbol(player.getSymbol(), x, y);
        if (game.checkWinner(player)) {
            game.setWinner(player);
            if (player.getSymbol() == Symbol.X)
                game.setGameState(new XWinsState());
            else
                game.setGameState(new YWinsState());
        }
        if (game.getBoard().isFull())
            game.setGameState(new DrawState());

    }
}
