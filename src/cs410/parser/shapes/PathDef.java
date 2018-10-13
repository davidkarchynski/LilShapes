package cs410.parser.shapes;


import cs410.Lexer;
import cs410.parser.Node;
import cs410.parser.properties.stringValue.ColorProp;
import cs410.parser.properties.stringValue.LineColorProp;
import cs410.parser.properties.twoValue.MoveProp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class PathDef extends ShapedefNode {
    public static final String TOKEN_NAME = "path";

    private ArrayList<Node> moves = new ArrayList<>();

    public PathDef(Lexer lexer) {
        super(lexer);
        this.supportedProps = new HashSet<>(Arrays.asList("move", "color", "lineColor"));
        this.requiredProps = new HashSet<>(Arrays.asList("move", "color"));
    }

    @Override
    public String name() {
        return PathDef.TOKEN_NAME;
    }

    @Override
    protected Node parseProperty() {
        Node prop = super.parseProperty();

        if (prop.name().equals(MoveProp.TOKEN_NAME)) {
            this.moves.add(prop);
        }

        return prop;
    }

    @Override
    public String evaluate() {
        // Make sure we have all the required properties
        this.verifyRequiredProps();

        StringBuilder sb = new StringBuilder();

        ColorProp color = (ColorProp) this.properties.get(ColorProp.TOKEN_NAME);

        String evaluatedLineColor = "";
        if (properties.containsKey(LineColorProp.TOKEN_NAME)) {
            LineColorProp lineColor = (LineColorProp) this.properties.get(LineColorProp.TOKEN_NAME);

            evaluatedLineColor = lineColor.evaluate();
        }


        sb.append("<path d=\"M%s %s")
                .append(" ");

        for (Node m: this.moves) {
            sb.append(m.evaluate())
                    .append(" ");
        }

        sb.append("Z")
                .append("\"")
                .append(" ")
                .append("fill=\"")
                .append(color.evaluate())
                .append("\" ")
                .append("stroke=\"")
                .append(evaluatedLineColor)
                .append("\" ")
                .append("/>\n");

        return sb.toString();
    }
}
