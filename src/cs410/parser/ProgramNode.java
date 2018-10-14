package cs410.parser;

import cs410.Lexer;
import cs410.parser.shapes.*;
import cs410.util.ParseErrorException;

import java.util.ArrayList;

public class ProgramNode extends Node {

    ArrayList<Node> drawProcs;

    public ProgramNode(Lexer lexer) {
        super(lexer);
        this.drawProcs = new ArrayList<>();
    }

    @Override
    public String name() {
        // Not used
        return "";
    }

    @Override
    public void parse() throws ParseErrorException {
        while (!lexer.empty()) {
            String nextToken = lexer.getNext();
            // FIXME: handle this properly
            Node node = null;
            // TODO: add all supported shapes
            switch (nextToken) {
                case "circle":
                    node = new CircleShapedef(lexer);
                    break;
                case "rectangle":
                    node = new RectShapedef(lexer);
                    break;
                case "ellipse":
                    node = new EllipseShapedef(lexer);
                    break;
                case "line":
                    node = new LineShapedef(lexer);
                    break;
                case "path":
                    node = new PathDef(lexer);
                    break;
                case "draw":
                    node = new DrawProcNode(lexer);
                    this.drawProcs.add(node);
                    break;
                default:
                    System.out.println("Expected a shape definition or draw expression, got: " + nextToken);
                    System.exit(1);
            }
            node.parse();
        }
    }

    @Override
    public String evaluate() throws ParseErrorException {
        StringBuilder sb = new StringBuilder();

        for (Node drawProc : this.drawProcs) {
            sb.append(drawProc.evaluate());
        }

        return sb.toString();
    }

    @Override
    public String debugInfo() {
        StringBuilder sb = new StringBuilder();

        for (Node drawProc : this.drawProcs) {
            sb.append(drawProc.debugInfo());
        }

        return sb.toString();
    }
}
