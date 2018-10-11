package cs410.parser.properties.stringValue;

import cs410.Lexer;

public class ColorProp extends StringValuePropNode {
    public static final String TOKEN_NAME = "color";

    public ColorProp(Lexer lexer) {
        super(lexer);
    }

    @Override
    public String name() {
        return ColorProp.TOKEN_NAME;
    }

    @Override
    public String evaluate() {
        return this.value;
    }
}
