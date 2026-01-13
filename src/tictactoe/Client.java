package tictactoe;

import tictactoe.entities.Board;
import tictactoe.entities.GameSystem;
import tictactoe.entities.Player;
import tictactoe.enums.Symbol;

import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Player player1 = new Player("Dibya", Symbol.X);
        Player player2 = new Player("Satish", Symbol.Y);
        GameSystem gameSystem = GameSystem.getInstance();
        gameSystem.makeGame(player1, player2);
        while (true) {
            try {
                System.out.println("Game starts");
                System.out.println(gameSystem.getGame().getCurrentPlayer().getName() + " plays");
                gameSystem.getGame().getBoard().printBoard();
                System.out.println("Enter x and y coordinates");
                int x = sc.nextInt();
                int y = sc.nextInt();
                gameSystem.makeMove(x, y);
                if (gameSystem.getGame().getWinner() != null) {
                    System.out.println(gameSystem.getGame().getWinner().getName() + " wins");
                    break;
                } else if (gameSystem.getGame().getBoard().isFull()) {
                    System.out.println("Draw ");
                    break;
                }
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
                System.out.println("Try again");
            }

        }
//        Board board = new Board(3);
//        board.printBoard();
    }
}
