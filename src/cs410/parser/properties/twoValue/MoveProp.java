package cs410.parser.properties.twoValue;

import cs410.Lexer;

public class MoveProp extends TwoValuePropNode {
    public static final String TOKEN_NAME = "move";

    public MoveProp(Lexer lexer) {
        super(lexer);
    }

    @Override
    public String name() {
        return MoveProp.TOKEN_NAME;
    }

    @Override
    public String evaluate() {
        return String.format("l%s %s", Double.toString(this.first), Double.toString(this.second));
    }
}
