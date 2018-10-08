package cs410.parser.properties.singleValue;

import cs410.Lexer;

public class RadiusProp extends SingleValuePropNode {
    public static final String TOKEN_NAME = "radius";

    public RadiusProp(Lexer lexer) {
        super(lexer);
    }

    @Override
    public String name() {
        return RadiusProp.TOKEN_NAME;
    }

    @Override
    public String evaluate() {
        return String.format("r=\"%s\"", Double.toString(this.value));
    }
}
