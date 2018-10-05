package cs410.parser.properties;

import cs410.Lexer;

public class HeightProp extends PropertyNode {
    public static final String TOKEN_NAME = "height";
    double value;

    public HeightProp(Lexer lexer) {
        super(lexer);
    }

    @Override
    public String name() {
        return HeightProp.TOKEN_NAME;
    }

    @Override
    public void parse() {
        String token = lexer.getNext();

        try {
            value = Double.parseDouble(token);
        } catch (NumberFormatException nfe) {
            System.out.println("Error parsing " + token + " for " + this.name());
        }
    }

    @Override
    public String evaluate() {
        return String.format("height=\"%s\"", Double.toString(value));
    }
}
