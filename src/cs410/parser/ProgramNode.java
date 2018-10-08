package cs410.parser;

import cs410.Lexer;
import cs410.parser.shapes.CircleShapedef;
import cs410.parser.shapes.RectShapedef;
import cs410.parser.shapes.ShapedefNode;

import java.util.ArrayList;

public class ProgramNode extends Node {

    ArrayList<ShapedefNode> shapes;

    public ProgramNode(Lexer lexer) {
        super(lexer);
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
            String nextToken = lexer.getNext();
            // FIXME: fix this to be generic, support rect for now
            ShapedefNode shape = new RectShapedef(lexer);
            // TODO: add all supported shapes
            switch (nextToken) {
                case "circle":
                    shape = new CircleShapedef(lexer);
                    break;
                case "rectangle":
                    shape = new RectShapedef(lexer);
                    break;
                case "ellipse":
                case "line":
                default:
                    System.out.println("Expected a shape definition, got: " + nextToken);
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
            sb.append(s.evaluate());
        }

        return sb.toString();
    }
}
