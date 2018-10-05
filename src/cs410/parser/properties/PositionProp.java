package cs410.parser.properties;

import cs410.Lexer;

public class PositionProp extends PropertyNode {
    public static final String TOKEN_NAME = "pos";

    double x;
    double y;

    public PositionProp(Lexer lexer) {
        super(lexer);
    }

    @Override
    public String name() {
        return PositionProp.TOKEN_NAME;
    }

    @Override
    public void parse() {
        try {
            x = Double.parseDouble(lexer.getNext());
            y = Double.parseDouble(lexer.getNext());
        } catch (NumberFormatException nfe) {
            System.out.println("Error parsing value for " + this.name());
        }
    }

    @Override
    public String evaluate() {
        return String.format("x=\"%s\" y=\"%s\"", Double.toString(x), Double.toString(y));
    }
}
