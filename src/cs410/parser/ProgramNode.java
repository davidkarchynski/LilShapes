package cs410.parser;

import cs410.parser.shapes.RectShapedef;
import cs410.parser.shapes.ShapedefNode;

import java.util.ArrayList;

public class ProgramNode extends Node {

    ArrayList<ShapedefNode> shapes;

    public ProgramNode() {
        this.shapes = new ArrayList<ShapedefNode>();
    }

    @Override
    public String name() {
        // Not used
        return "";
    }

    @Override
    public void parse() {
        while (!lexer.empty()) {
            String nextToken = lexer.peek();
            // FIXME: fix this to be generic, support rect for now
            ShapedefNode shape = new RectShapedef();
            // TODO: add all supported shapes
            switch (nextToken) {
                case "circle":
                case "rectangle":
                    shape = new RectShapedef();
                case "ellipse":
                case "line":
                default:
                    System.out.println("Unsupported token: " + nextToken);
                    System.exit(1);
            }
            shape.parse();
            this.shapes.add(shape);
        }
    }

    @Override
    public String evaluate() {
        StringBuilder sb = new StringBuilder();

        for (ShapedefNode s : this.shapes) {
            sb.append(s.evaluate()).append("\n");
        }

        return sb.toString();
    }
}
