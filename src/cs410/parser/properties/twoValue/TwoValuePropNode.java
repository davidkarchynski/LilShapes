package cs410.parser.properties.twoValue;

import cs410.Lexer;
import cs410.Main;
import cs410.parser.Node;

abstract public class TwoValuePropNode extends Node {
    protected double first;
    protected double second;

    public TwoValuePropNode(Lexer lexer) {
        super(lexer);
    }

    @Override
    public void parse() {
        try {
            first = Double.parseDouble(lexer.getNext());
            second = Double.parseDouble(lexer.getNext());
        } catch (NumberFormatException nfe) {
            System.out.println("Error parsing value for " + this.name());
            Main.errorsList.add("Error parsing value for " + this.name());
        }
    }

    @Override
    public String debugInfo() {
        return "";
    }
}
