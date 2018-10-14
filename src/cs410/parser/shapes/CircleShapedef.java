package cs410.parser.shapes;


import cs410.Lexer;
import cs410.parser.properties.singleValue.LineWidthProp;
import cs410.parser.properties.singleValue.RadiusProp;
import cs410.parser.properties.stringValue.ColorProp;
import cs410.parser.properties.stringValue.LineColorProp;
import cs410.parser.properties.twoValue.PositionProp;
import cs410.util.ParseErrorException;

import java.util.Arrays;
import java.util.HashSet;

public class CircleShapedef extends ShapedefNode {

    public CircleShapedef(Lexer lexer) {
        super(lexer);
        this.TOKEN_NAME = "circle";
        this.supportedProps = new HashSet<>(Arrays.asList("radius", "color", "lineColor", "lineWidth"));
        this.requiredProps = new HashSet<>(Arrays.asList("radius"));
    }

    @Override
    public String name() {
        return CircleShapedef.TOKEN_NAME;
    }

    @Override
    public String evaluate() throws ParseErrorException {
        // Make sure we have all the required properties
        this.verifyRequiredProps();

        PositionProp pos = (PositionProp) this.properties.get(PositionProp.TOKEN_NAME);
        RadiusProp radius = (RadiusProp) this.properties.get(RadiusProp.TOKEN_NAME);

        StringBuilder sb = new StringBuilder();

        sb.append("<circle cx=\"%s\" cy=\"%s\"")
                .append(" ")
                .append(radius.evaluate());

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
