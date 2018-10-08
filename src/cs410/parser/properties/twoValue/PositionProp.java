package cs410.parser.properties.twoValue;

import cs410.Lexer;

public class PositionProp extends TwoValuePropNode {
    public static final String TOKEN_NAME = "pos";

    public PositionProp(Lexer lexer) {
        super(lexer);
    }

    @Override
    public String name() {
        return PositionProp.TOKEN_NAME;
    }

    @Override
    public String evaluate() {
        return String.format("x=\"%s\" y=\"%s\"", Double.toString(this.first), Double.toString(this.second));
    }
}
