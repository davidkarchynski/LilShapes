package cs410.parser.properties.singleValue;

import cs410.Lexer;

public class LineWidthProp extends SingleValuePropNode {

    public static final String TOKEN_NAME = "lineWidth";

    public LineWidthProp(Lexer lexer) {
        super(lexer);
    }

    @Override
    public String name() {
        return  LineWidthProp.TOKEN_NAME;
    }

    @Override
    public String evaluate() {
        return Double.toString(value);
    }
}
