package cs410.parser;

import cs410.Lexer;
import cs410.Main;
import cs410.parser.shapes.ShapedefNode;

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
    public void parse() {
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
                    System.out.println("Error parsing position argument for \"draw\"");
                    Main.errorsList.add("Error parsing position argument for \"draw\"");
                    System.exit(1);
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
                        System.out.println("\"draw from X1, Y1\" must be followed by \"to X2, Y2\"");
                        Main.errorsList.add("\"draw from X1, Y1\" must be followed by \"to X2, Y2\"");
                        System.exit(1);
                    }

                    x2 = Double.parseDouble(lexer.getNext());
                    y2 = Double.parseDouble(lexer.getNext());
                    posArgs.add(x2);
                    posArgs.add(y2);

                    break;
                } catch (NumberFormatException nfe) {
                    System.out.println("Error parsing position argument for \"draw\"");
                    Main.errorsList.add("Error parsing position argument for \"draw\"");
                    System.exit(1);
                }
                break;
            default:
                System.out.println("\"draw\" call must be followed by a shape name and one of the following: \"from\", \"at\"");
                Main.errorsList.add("\"draw\" call must be followed by a shape name and one of the following: \"from\", \"at\"");
                System.exit(1);
        }
    }

    @Override
    public String evaluate() {
        ShapedefNode shapeNode = Parser.symbolTable.get(this.shapeName);
        if (shapeNode == null) {
            Main.errorsList.add(this.shapeName + " is not defined.");
            System.out.println(this.shapeName + " is not defined.");
            System.exit(1);
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
                .append("font-size=\"15\" ")
                .append("font-family=\"\'Comic Sans MS\', cursive, sans-serif\" ")
                .append("x=\"")
                .append(x)
                .append("\" ")
                .append("y=\"")
                .append(y)
                .append("\"")
                .append(">")
                .append(shapeNode.debugInfo())
                .append(" ")
                .append(shapeName)
                .append(" at ")
                .append(x)
                .append(" , ")
                .append(y)
                .append("</text>");

        return sb.toString();
    }
}
