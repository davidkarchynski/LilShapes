package cs410.parser.properties;

import cs410.Lexer;

public class CirclePositionProp extends PositionProp {
    public CirclePositionProp(Lexer lexer) {
        super(lexer);
    }

    @Override
    public String evaluate() {
        return String.format("cx=\"%s\" cy=\"%s\"", Double.toString(x), Double.toString(y));
    }
}
