package cs410.parser.properties.singleValue;

import cs410.Lexer;
import cs410.parser.Node;

public abstract class SingleValuePropNode extends Node {
    protected double value;

    public SingleValuePropNode(Lexer lexer) {
        super(lexer);
    }

    @Override
    public void parse() {
        String token = lexer.getNext();

        try {
            value = Double.parseDouble(token);
        } catch (NumberFormatException nfe) {
            System.out.println("Error parsing " + token + " for " + this.name());
        }
    }
}
