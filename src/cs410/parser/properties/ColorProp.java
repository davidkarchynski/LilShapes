package cs410.parser.properties;

import cs410.Lexer;

public class ColorProp extends PropertyNode {
    public static final String TOKEN_NAME = "color";
    String value;

    public ColorProp(Lexer lexer) {
        super(lexer);
    }

    @Override
    public String name() {
        return ColorProp.TOKEN_NAME;
    }

    @Override
    public void parse() {
        this.value = lexer.getNext();
    }

    @Override
    public String evaluate() {
        return String.format("fill:%s;", value);
    }
}
