package cs410.parser.shapes;

import cs410.parser.Node;
import cs410.parser.Parser;
import cs410.parser.properties.PropertyNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public abstract class ShapedecNode extends Node {

    private Set<String> supportedProps = new HashSet<>();

    public ArrayList<PropertyNode> properties;

    public ShapedecNode() {
        this.properties = new ArrayList<PropertyNode>();
    }

    @Override
    public void parse() {
        String nextToken = lexer.peek();
        while (!Parser.supportedShapes.contains(nextToken)) {
            PropertyNode prop = propNodeFromToken(lexer.getNext());
            prop.parse();
            this.properties.add(prop);
        }

    }

    private PropertyNode propNodeFromToken(String token) {
        // stub
        return null;
    }
}
