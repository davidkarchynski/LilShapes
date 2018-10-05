package cs410.parser.properties;

public class WidthProp extends PropertyNode {

    public static final String TOKEN_NAME = "width";
    double value;

    @Override
    public String name() {
        return  WidthProp.TOKEN_NAME;
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
        return String.format("width=\"%s\"", Double.toString(value));
    }
}
