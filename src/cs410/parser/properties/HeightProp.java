package cs410.parser.properties;

public class HeightProp extends PropertyNode {
    public static final String TOKEN_NAME = "height";
    double value;

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
