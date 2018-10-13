package cs410.parser.properties.stringValue;

import cs410.Lexer;
import cs410.parser.Node;

abstract public class StringValuePropNode extends Node {
    protected String value;

    public StringValuePropNode(Lexer lexer) {
        super(lexer);
    }

    @Override
    public void parse() {
        this.value = lexer.getNext();
    }

    @Override
    public String debugInfo() {
        return "";
    }
}
