package cs410.parser;

import java.awt.*;
import java.util.ArrayList;

public class ShapedecNode extends Node {

    public ArrayList<PropertyNode> properties;

    public ShapedecNode() {
        this.properties = new ArrayList<PropertyNode>();
    }

    @Override
    public void parse() {

    }

    @Override
    public String evaluate() {
        return null;
    }
}
