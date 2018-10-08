package cs410.parser;

import cs410.Lexer;

public abstract class Node {
    protected Lexer lexer;

    public Node(Lexer lexer) {
        this.lexer = lexer;
    }

    abstract public String name();
    abstract public void parse();
    abstract public String evaluate();
}
