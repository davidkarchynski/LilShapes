package cs410.parser.properties.stringValue;

import cs410.Lexer;

public class LineColorProp extends StringValuePropNode {
    public static final String TOKEN_NAME = "lineColor";

    public LineColorProp(Lexer lexer) {
        super(lexer);
    }

    @Override
    public String name() {
        return LineColorProp.TOKEN_NAME;
    }

    @Override
    public String evaluate() {
        return this.value;
    }
}
