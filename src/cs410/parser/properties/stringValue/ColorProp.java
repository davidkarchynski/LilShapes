package cs410.parser.properties.stringValue;

import cs410.Lexer;
import cs410.parser.properties.singleValue.SingleValuePropNode;

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
        return String.format("fill:%s;", this.value);
    }
}
