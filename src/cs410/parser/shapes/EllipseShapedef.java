package cs410.parser.shapes;


import cs410.Lexer;
import cs410.parser.properties.singleValue.HeightProp;
import cs410.parser.properties.singleValue.LineWidthProp;
import cs410.parser.properties.singleValue.WidthProp;
import cs410.parser.properties.stringValue.ColorProp;
import cs410.parser.properties.stringValue.LineColorProp;

import java.util.Arrays;
import java.util.HashSet;

public class EllipseShapedef extends ShapedefNode {
    public static final String TOKEN_NAME = "ellipse";

    public EllipseShapedef(Lexer lexer) {
        super(lexer);
        this.supportedProps = new HashSet<>(Arrays.asList("width", "height", "color", "lineColor", "lineWidth"));
        this.requiredProps = new HashSet<>(Arrays.asList("width", "height"));
    }

    @Override
    public String name() {
        return EllipseShapedef.TOKEN_NAME;
    }

    @Override
    public String evaluate() {
        // Make sure we have all the required properties
        this.verifyRequiredProps();

        WidthProp width = (WidthProp) this.properties.get(WidthProp.TOKEN_NAME);
        HeightProp height = (HeightProp) this.properties.get(HeightProp.TOKEN_NAME);

        StringBuilder sb = new StringBuilder();

        sb.append("<ellipse cx=\"%s\" cy=\"%s\"")
                .append(" ")
                .append(String.format("rx=\"%s\" ry=\"%s\"", width.evaluate(), height.evaluate()))
                .append(" ");

        // Optional properties
        String evaluatedFill = "";
        if (properties.containsKey(ColorProp.TOKEN_NAME)) {
            ColorProp color = (ColorProp) this.properties.get(ColorProp.TOKEN_NAME);

            evaluatedFill += "fill:" + color.evaluate() + "; ";
        }

        if (properties.containsKey(LineColorProp.TOKEN_NAME)) {
            LineColorProp lineColor = (LineColorProp) this.properties.get(LineColorProp.TOKEN_NAME);

            evaluatedFill += "stroke:" + lineColor.evaluate() + "; ";
        }

        if (properties.containsKey(LineWidthProp.TOKEN_NAME)) {
            LineWidthProp lineWidth = (LineWidthProp) this.properties.get(LineWidthProp.TOKEN_NAME);

            evaluatedFill += "stroke-width:" + lineWidth.evaluate() + "; ";
        }

        sb.append(" style=\"").append(evaluatedFill).append("\"");
        sb.append("/>\n");
        return sb.toString();
    }
}
