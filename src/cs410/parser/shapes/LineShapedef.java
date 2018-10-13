package cs410.parser.shapes;


import cs410.Lexer;
import cs410.parser.properties.singleValue.HeightProp;
import cs410.parser.properties.singleValue.LineWidthProp;
import cs410.parser.properties.singleValue.WidthProp;
import cs410.parser.properties.stringValue.ColorProp;
import cs410.parser.properties.stringValue.LineColorProp;

import java.util.Arrays;
import java.util.HashSet;

public class LineShapedef extends ShapedefNode {
    public static final String TOKEN_NAME = "line";

    public LineShapedef(Lexer lexer) {
        super(lexer);
        this.supportedProps = new HashSet<>(Arrays.asList("lineColor", "lineWidth"));
        this.requiredProps = new HashSet<>(Arrays.asList("lineColor"));
    }

    @Override
    public String name() {
        return LineShapedef.TOKEN_NAME;
    }

    @Override
    public String evaluate() {
        // Make sure we have all the required properties
        this.verifyRequiredProps();

        StringBuilder sb = new StringBuilder();

        LineColorProp lineColor = (LineColorProp) this.properties.get(LineColorProp.TOKEN_NAME);


        sb.append("<line x1=\"%s\" y1=\"%s\" x2=\"%s\" y2=\"%s\"")
                .append(" ")
                .append("style=\"stroke:")
                .append(lineColor.evaluate())
                .append(";");

        // Optional properties
        if (properties.containsKey(LineWidthProp.TOKEN_NAME)) {
            LineWidthProp lineWidth = (LineWidthProp) this.properties.get(LineWidthProp.TOKEN_NAME);

            sb.append("stroke-width:2" + lineWidth.evaluate() + "; ");

        } else {
            sb.append("stroke-width:2");
        }


        sb.append("\"/>\n");

        return sb.toString();
    }
}
