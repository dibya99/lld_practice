package tictactoe.enums;

public enum Symbol {
    X('X'),
    Y('Y'),
    BLANK('_');

    private char ch;

    Symbol(char ch) {
        this.ch = ch;
    }

    public char getChar() {
        return ch;
    }
}
