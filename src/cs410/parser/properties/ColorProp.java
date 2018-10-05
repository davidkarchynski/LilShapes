package cs410.parser.properties;

public class ColorProp extends PropertyNode {
    public static final String TOKEN_NAME = "color";
    String value;

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
