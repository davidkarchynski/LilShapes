package cs410.parser.properties;

import cs410.Lexer;
import cs410.parser.Node;

public abstract class PropertyNode extends Node {
    public PropertyNode(Lexer lexer) {
        this.lexer = lexer;
    }
}
