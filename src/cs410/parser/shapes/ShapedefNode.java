package cs410.parser.shapes;

import cs410.Lexer;
import cs410.parser.Node;
import cs410.parser.Parser;
import cs410.parser.properties.singleValue.HeightProp;
import cs410.parser.properties.singleValue.RadiusProp;
import cs410.parser.properties.singleValue.WidthProp;
import cs410.parser.properties.stringValue.ColorProp;
import cs410.parser.properties.twoValue.CirclePositionProp;
import cs410.parser.properties.twoValue.PositionProp;

import java.util.*;

public abstract class ShapedefNode extends Node {
    // Override in subclass
    protected Set<String> supportedProps = new HashSet<>();
    protected Set<String> requiredProps = new HashSet<>();

    public Map<String, Node> properties;

    public ShapedefNode(Lexer lexer) {
        super(lexer);
        this.properties = new HashMap<>();
    }

    @Override
    public void parse() {
        while (!Parser.supportedShapes.contains(lexer.peek())) {
            String token = lexer.getNext();

            if (token.equals(Lexer.NULL_TOKEN)) {
                break;
            }

            Node prop = propNodeFromToken(token);
            prop.parse();
            this.properties.put(prop.name(), prop);
        }
    }

    private Node propNodeFromToken(String token) {
        if (!this.supportedProps.contains(token)) {
            System.out.println("Unsupported property "
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
            case PositionProp.TOKEN_NAME:
                if (this.name().equals(CircleShapedef.TOKEN_NAME)) {
                    return new CirclePositionProp(lexer);
                }
                return new PositionProp(lexer);
            case RadiusProp.TOKEN_NAME:
                return new RadiusProp(lexer);
            default:
                System.out.println("Could not parse property: " + token);
                System.exit(1);
        }
        return null;
    }

    protected void verifyRequiredProps() {
        if (!properties.keySet().containsAll(requiredProps)) {
            // ok to mutate since we exit here with an error
            requiredProps.removeAll(properties.keySet());
            System.out.println("Missing one or more properties for " + this.name() + ": " + properties.keySet().toString());
            System.exit(1);
        }
    }
}
