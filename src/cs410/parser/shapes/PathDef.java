package cs410.parser.shapes;


import cs410.Lexer;
import cs410.parser.Node;
import cs410.parser.properties.singleValue.LineWidthProp;
import cs410.parser.properties.stringValue.ColorProp;
import cs410.parser.properties.stringValue.LineColorProp;
import cs410.parser.properties.twoValue.MoveProp;
import cs410.util.ParseErrorException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class PathDef extends ShapedefNode {

    private ArrayList<Node> moves = new ArrayList<>();

    public PathDef(Lexer lexer) {
        super(lexer);
        this.TOKEN_NAME = "path";
        this.supportedProps = new HashSet<>(Arrays.asList("move", "color", "lineColor", "lineWidth"));
        this.requiredProps = new HashSet<>(Arrays.asList("move", "color"));
    }

    @Override
    public String name() {
        return PathDef.TOKEN_NAME;
    }

    @Override
    protected Node parseProperty() throws ParseErrorException {
        Node prop = super.parseProperty();

        if (prop.name().equals(MoveProp.TOKEN_NAME)) {
            this.moves.add(prop);
        }

        return prop;
    }

    @Override
    public String evaluate() throws ParseErrorException {
        // Make sure we have all the required properties
        this.verifyRequiredProps();

        StringBuilder sb = new StringBuilder();

        ColorProp color = (ColorProp) this.properties.get(ColorProp.TOKEN_NAME);

        // Optional properties
        String evaluatedLineColor = "";
        String evaluatedLineWidth = "";
        if (properties.containsKey(LineColorProp.TOKEN_NAME)) {
            LineColorProp lineColor = (LineColorProp) this.properties.get(LineColorProp.TOKEN_NAME);

            evaluatedLineColor = lineColor.evaluate();
        }

        if (properties.containsKey(LineWidthProp.TOKEN_NAME)) {
            LineWidthProp lineWidth = (LineWidthProp) this.properties.get(LineWidthProp.TOKEN_NAME);

            evaluatedLineWidth = lineWidth.evaluate();

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
                .append("stroke-width=\"")
                .append(evaluatedLineWidth)
                .append("\" ")
                .append("/>\n");

        return sb.toString();
    }
}
