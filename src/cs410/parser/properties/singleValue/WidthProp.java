package cs410.parser.properties.singleValue;

import cs410.Lexer;

public class WidthProp extends SingleValuePropNode {

    public static final String TOKEN_NAME = "width";

    public WidthProp(Lexer lexer) {
        super(lexer);
    }

    @Override
    public String name() {
        return  WidthProp.TOKEN_NAME;
    }

    @Override
    public String evaluate() {
        return String.format("width=\"%s\"", Double.toString(value));
    }
}
