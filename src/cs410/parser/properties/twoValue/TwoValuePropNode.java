package cs410.parser.properties.twoValue;

import cs410.Lexer;
import cs410.parser.Node;
import cs410.util.ParseErrorException;

abstract public class TwoValuePropNode extends Node {
    protected double first;
    protected double second;

    public TwoValuePropNode(Lexer lexer) {
        super(lexer);
    }

    @Override
    public void parse() throws ParseErrorException {
        try {
            first = Double.parseDouble(lexer.getNext());
            second = Double.parseDouble(lexer.getNext());
        } catch (NumberFormatException nfe) {
            throw new ParseErrorException("Error parsing value for " + this.name());
        }
    }

    @Override
    public String debugInfo() {
        return "";
    }
}
