package cs410.parser;

import cs410.parser.shapes.RectShapedec;
import cs410.parser.shapes.ShapedecNode;

import java.util.ArrayList;

public class ProgramNode extends Node {

    ArrayList<ShapedecNode> shapes;

    public ProgramNode() {
        this.shapes = new ArrayList<ShapedecNode>();
    }

    @Override
    public void parse() {
        while (!lexer.empty()) {
            String nextToken = lexer.peek();
            // FIXME: fix this to be generic, support rect for now
            ShapedecNode shape = new RectShapedec();
            // TODO: add all supported shapes
            switch (nextToken) {
                case "circle":
                case "rectangle":
                    shape = new RectShapedec();
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

        for (ShapedecNode s : this.shapes) {
            sb.append(s.evaluate()).append("\n");
        }

        return sb.toString();
    }
}
