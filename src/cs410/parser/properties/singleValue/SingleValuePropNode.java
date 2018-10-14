package cs410.parser.properties.singleValue;

import cs410.Lexer;
import cs410.parser.Node;
import cs410.util.ParseErrorException;

public abstract class SingleValuePropNode extends Node {
    protected double value;

    public SingleValuePropNode(Lexer lexer) {
        super(lexer);
    }

    @Override
    public void parse() throws ParseErrorException {
        String token = lexer.getNext();

        try {
            value = Double.parseDouble(token);
        } catch (NumberFormatException nfe) {
            throw new ParseErrorException("Error parsing " + token + " for " + this.name());
        }
    }

    @Override
    public String debugInfo() {
        return "";
    }
}
