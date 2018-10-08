package cs410.parser.shapes;


import cs410.Lexer;
import cs410.parser.properties.singleValue.RadiusProp;
import cs410.parser.properties.stringValue.ColorProp;
import cs410.parser.properties.twoValue.PositionProp;

import java.util.Arrays;
import java.util.HashSet;

public class CircleShapedef extends ShapedefNode {
    public static final String TOKEN_NAME = "circle";

    public CircleShapedef(Lexer lexer) {
        super(lexer);
        this.supportedProps = new HashSet<>(Arrays.asList("radius", "pos", "color"));
        this.requiredProps = new HashSet<>(Arrays.asList("pos", "radius"));
    }

    @Override
    public String name() {
        return CircleShapedef.TOKEN_NAME;
    }

    @Override
    public String evaluate() {
        // Make sure we have all the required properties
        this.verifyRequiredProps();

        PositionProp pos = (PositionProp) this.properties.get(PositionProp.TOKEN_NAME);
        RadiusProp radius = (RadiusProp) this.properties.get(RadiusProp.TOKEN_NAME);

        StringBuilder sb = new StringBuilder();

        sb.append("<circle ").append(pos.evaluate())
                .append(" ")
                .append(radius.evaluate());

        // Optional properties
        if (properties.containsKey(ColorProp.TOKEN_NAME)) {
            ColorProp color = (ColorProp) this.properties.get(ColorProp.TOKEN_NAME);

            sb.append(" style=\"").append(color.evaluate()).append("\"");
        }

        sb.append("/>\n");
        return sb.toString();
    }
}
