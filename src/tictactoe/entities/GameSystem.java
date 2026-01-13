package tictactoe.entities;

public class GameSystem {
    static private GameSystem gameSystem;
    private Game game;

    private GameSystem() {
    }

    public static GameSystem getInstance() {
        if (gameSystem == null)
            gameSystem = new GameSystem();
        return gameSystem;
    }

    public void makeGame(Player player1, Player player2) {
        game = new Game(player1, player2);
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void makeMove(int x, int y) {
        game.getBoard().printBoard();
        game.makeMove(x, y);
    }


}
