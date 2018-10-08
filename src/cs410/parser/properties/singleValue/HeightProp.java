package cs410.parser.properties.singleValue;

import cs410.Lexer;

public class HeightProp extends SingleValuePropNode {
    public static final String TOKEN_NAME = "height";

    public HeightProp(Lexer lexer) {
        super(lexer);
    }

    @Override
    public String name() {
        return HeightProp.TOKEN_NAME;
    }

    @Override
    public String evaluate() {
        return String.format("height=\"%s\"", Double.toString(value));
    }
}
