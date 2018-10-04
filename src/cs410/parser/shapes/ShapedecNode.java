package cs410.parser.shapes;

import cs410.parser.Node;
import cs410.parser.Parser;
import cs410.parser.properties.PropertyNode;

import java.util.*;

public abstract class ShapedecNode extends Node {

    // Override in subclass
    private Set<String> supportedProps = new HashSet<>();

    public Map<String, PropertyNode> properties;

    public ShapedecNode() {
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
        // stub
        return null;
    }
}
