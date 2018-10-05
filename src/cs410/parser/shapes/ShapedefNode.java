package cs410.parser.shapes;

import cs410.parser.Node;
import cs410.parser.Parser;
import cs410.parser.properties.PropertyNode;

import java.util.*;

public abstract class ShapedefNode extends Node {
    // Override in subclass
    protected Set<String> supportedProps = new HashSet<>();
    protected Set<String> requiredProps = new HashSet<>();

    public Map<String, PropertyNode> properties;

    public ShapedefNode() {
        this.properties = new HashMap<>();
    }

    @Override
    public void parse() {
        while (!Parser.supportedShapes.contains(lexer.peek())) {
            PropertyNode prop = propNodeFromToken(lexer.getNext());
            prop.parse();
            this.properties.put(prop.name(), prop);
        }
    }

    private PropertyNode propNodeFromToken(String token) {
        if (!this.supportedProps.contains(token)) {
            System.out.println("Unsupported property "
                    + token.replace(":", "")
                    + " for shape " + this.name());
        }
        // TODO: switch on token parameter and return the correct implementation
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
