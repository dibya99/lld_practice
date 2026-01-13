package tictactoe.exceptions;

public class InvalidMoveException extends RuntimeException {
    public InvalidMoveException() {
      super("Invalid move");
    }
}
