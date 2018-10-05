package cs410.parser;

import cs410.Lexer;

public abstract class Node {
    protected Lexer lexer;

    abstract public String name();
    abstract public void parse();
    abstract public String evaluate();
}
