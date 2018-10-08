package cs410.parser.properties.twoValue;

import cs410.Lexer;

public class VertexProp extends TwoValuePropNode {

    public static final String TOKEN_NAME_V1 = "vertex1";
    public static final String TOKEN_NAME_V2 = "vertex2";
    public static final String TOKEN_NAME_V3 = "vertex3";

    private String propName;

    public VertexProp(Lexer lexer, String name) {
        super(lexer);
        this.propName = name;
    }

    @Override
    public String name() {
        return this.propName;
    }

    @Override
    public String evaluate() {
        return String.format("%s,%s", Double.toString(this.first), Double.toString(this.second));
    }
}
