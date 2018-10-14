package cs410.parser;

import cs410.Lexer;
import cs410.parser.shapes.ShapedefNode;
import cs410.util.ParseErrorException;

import java.util.ArrayList;
import java.util.List;

public class DrawProcNode extends Node {
    public static final String TOKEN_NAME = "draw";
    private String shapeName;
    private List<Double> posArgs = new ArrayList<>();

    public DrawProcNode(Lexer lexer) {
        super(lexer);
    }

    @Override
    public String name() {
        return DrawProcNode.TOKEN_NAME;
    }

    @Override
    public void parse() throws ParseErrorException {
        this.shapeName = lexer.getNext();

        String atFromToken = lexer.getNext();

        double x1;
        double y1;
        double x2;
        double y2;

        switch (atFromToken) {
            case "at":
                try {
                    x1 = Double.parseDouble(lexer.getNext());
                    y1 = Double.parseDouble(lexer.getNext());
                    posArgs.add(x1);
                    posArgs.add(y1);
                } catch (NumberFormatException nfe) {
                    throw new ParseErrorException("Error parsing position argument for \"draw\"");
                }
                break;
            case "from":
                try {
                    x1 = Double.parseDouble(lexer.getNext());
                    y1 = Double.parseDouble(lexer.getNext());
                    posArgs.add(x1);
                    posArgs.add(y1);

                    String to = lexer.getNext();

                    if(!to.equals("to")) {
                        throw new ParseErrorException("\"draw from X1, Y1\" must be followed by \"to X2, Y2\"");
                    }

                    x2 = Double.parseDouble(lexer.getNext());
                    y2 = Double.parseDouble(lexer.getNext());
                    posArgs.add(x2);
                    posArgs.add(y2);

                    break;
                } catch (NumberFormatException nfe) {
                    throw new ParseErrorException("Error parsing position argument for \"draw\"");
                }
            default:
                throw new ParseErrorException("\"draw\" call must be followed by a shape name and one of the following: \"from\", \"at\"");
        }
    }

    @Override
    public String evaluate() throws ParseErrorException {
        ShapedefNode shapeNode = Parser.symbolTable.get(this.shapeName);
        if (shapeNode == null) {
            throw new ParseErrorException(this.shapeName + " is not defined.");
        }

        ArrayList<String> stringArgs = new ArrayList<>();

        for (Double posArg : this.posArgs) {
            stringArgs.add(Double.toString(posArg));
        }

        return String.format(shapeNode.evaluate(), stringArgs.toArray());
    }

    @Override
    public String debugInfo() {
        ShapedefNode shapeNode = Parser.symbolTable.get(this.shapeName);

        String x = Double.toString(this.posArgs.get(0));
        String y = Double.toString(this.posArgs.get(1));

        StringBuilder sb = new StringBuilder();

        sb.append("<text fill=\"red\" ") // TODO: input the color and the size from the front end
                .append("font-size=\"12\" ")
                .append("font-family=\" \'Courier New\', Courier, \'Lucida Sans Typewriter\' \" ")
                .append("font-weight:bold; ")
                .append("x=\"")
                .append(x)
                .append("\" ")
                .append("y=\"")
                .append(y)
                .append("\"")
                .append(">")
                .append(shapeNode.debugInfo())
                .append(" ")
                .append(this.shapeName)
                .append(" at ")
                .append(x)
                .append(" , ")
                .append(y)
                .append("</text>");

        return sb.toString();
    }
}
