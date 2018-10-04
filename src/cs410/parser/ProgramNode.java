package cs410.parser;

import java.util.ArrayList;

public class ProgramNode extends Node {

    ArrayList<ShapedecNode> shapes;

    public ProgramNode() {
        this.shapes = new ArrayList<ShapedecNode>();
    }

    @Override
    public void parse() {

    }

    @Override
    public String evaluate() {
        return null;
    }
}
