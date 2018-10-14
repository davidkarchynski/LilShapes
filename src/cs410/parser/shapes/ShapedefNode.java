package cs410.parser.shapes;

import cs410.Lexer;
import cs410.parser.Node;
import cs410.parser.Parser;
import cs410.parser.properties.singleValue.HeightProp;
import cs410.parser.properties.singleValue.LineWidthProp;
import cs410.parser.properties.singleValue.RadiusProp;
import cs410.parser.properties.singleValue.WidthProp;
import cs410.parser.properties.stringValue.ColorProp;
import cs410.parser.properties.stringValue.LineColorProp;
import cs410.parser.properties.twoValue.CirclePositionProp;
import cs410.parser.properties.twoValue.MoveProp;
import cs410.parser.properties.twoValue.PositionProp;
import cs410.util.ParseErrorException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class ShapedefNode extends Node {
    // Override in subclass
    protected Set<String> supportedProps = new HashSet<>();
    protected Set<String> requiredProps = new HashSet<>();
    protected String symbolName;

    public static String TOKEN_NAME = "";

    public Map<String, Node> properties;

    public ShapedefNode(Lexer lexer) {
        super(lexer);
        this.properties = new HashMap<>();
    }

    @Override
    public void parse() throws ParseErrorException {
        String symbolName = lexer.getNext();
        if (!symbolName.matches("\\\"([^\\\"]*)\\\"")) {
            throw new ParseErrorException("Expected a name for a shape (ex. \"shapename\"), got " + symbolName);
        }
        this.symbolName = symbolName;
        // FIXME: handle this properly
        while (!Parser.supportedShapes.contains(lexer.peek()) && !lexer.empty() && !lexer.peek().equals("draw")) {
            if (lexer.peek().equals(Lexer.NULL_TOKEN)) {
                break;
            }
            Node prop = this.parseProperty();
            this.properties.put(prop.name(), prop);
        }

        Parser.symbolTable.put(this.symbolName.substring(1, this.symbolName.length() - 1), this);
    }

    protected Node parseProperty() throws ParseErrorException {
        String token = lexer.getNext();

        Node prop = propNodeFromToken(token);
        prop.parse();
        return prop;
    }

    protected Node propNodeFromToken(String token) throws ParseErrorException {
        if (!this.supportedProps.contains(token)) {
            throw new ParseErrorException("Unsupported property "
                    + token.replace(":", "")
                    + " for shape " + this.name());
        }

        switch (token) {
            case ColorProp.TOKEN_NAME:
                return new ColorProp(lexer);
            case WidthProp.TOKEN_NAME:
                return new WidthProp(lexer);
            case HeightProp.TOKEN_NAME:
                return new HeightProp(lexer);
            case LineColorProp.TOKEN_NAME:
                return new LineColorProp(lexer);
            case LineWidthProp.TOKEN_NAME:
                return new LineWidthProp(lexer);
            case PositionProp.TOKEN_NAME:
                if (this.name().equals(CircleShapedef.TOKEN_NAME)) {
                    return new CirclePositionProp(lexer);
                }
                return new PositionProp(lexer);
            case RadiusProp.TOKEN_NAME:
                return new RadiusProp(lexer);
            case MoveProp.TOKEN_NAME:
                return new MoveProp(lexer);
            default:
                throw new ParseErrorException("Could not parse property: " + token);
        }
    }

    protected void verifyRequiredProps() throws ParseErrorException {
        if (!properties.keySet().containsAll(requiredProps)) {
            // ok to mutate since we exit here with an error
            requiredProps.removeAll(properties.keySet());
            throw new ParseErrorException("Missing one or more properties for " + this.name() + ": " + properties.keySet().toString());
        }
    }

    @Override
    public String debugInfo() {
        return this.TOKEN_NAME;
    }
}
