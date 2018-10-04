package cs410.parser.shapes;

import cs410.parser.Node;
import cs410.parser.Parser;
import cs410.parser.properties.PropertyNode;

import java.util.*;

public abstract class ShapedefNode extends Node {
    protected String name;

    // Override in subclass
    protected Set<String> supportedProps = new HashSet<>();

    public Map<String, PropertyNode> properties;

    public ShapedefNode() {
        this.properties = new HashMap<>();
    }

    @Override
    public void parse() {
        String nextToken = lexer.peek();
        while (!Parser.supportedShapes.contains(nextToken)) {
            PropertyNode prop = propNodeFromToken(lexer.getNext());
            prop.parse();
            this.properties.put(prop.name, prop);
        }
    }

    private PropertyNode propNodeFromToken(String token) {
        if (!this.supportedProps.contains(token)) {
            System.out.println("Unsupported property "
                    + token.replace(":", "")
                    + " for shape " + this.name);
        }
        // TODO: switch on token parameter and return the correct implementation
        return null;
    }
}
