package cs410.parser;

import cs410.Lexer;
import cs410.util.ParseErrorException;

public abstract class Node {
    protected Lexer lexer;

    public Node(Lexer lexer) {
        this.lexer = lexer;
    }

    abstract public String name();
    abstract public void parse() throws ParseErrorException;
    abstract public String evaluate() throws ParseErrorException;
    abstract public String debugInfo();
}
