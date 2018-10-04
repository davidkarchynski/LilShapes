package cs410.parser;

import cs410.Lexer;

public abstract class Node {
    private Lexer lexer;

    abstract public void parse();
    abstract public String evaluate();
}
