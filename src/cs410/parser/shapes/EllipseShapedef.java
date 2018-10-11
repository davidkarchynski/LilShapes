package cs410.parser.shapes;


import cs410.Lexer;
import cs410.parser.properties.singleValue.HeightProp;
import cs410.parser.properties.singleValue.WidthProp;
import cs410.parser.properties.stringValue.ColorProp;

import java.util.Arrays;
import java.util.HashSet;

public class EllipseShapedef extends ShapedefNode {
    public static final String TOKEN_NAME = "ellipse";

    public EllipseShapedef(Lexer lexer) {
        super(lexer);
        this.supportedProps = new HashSet<>(Arrays.asList("width", "height", "color"));
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
        if (properties.containsKey(ColorProp.TOKEN_NAME)) {
            ColorProp color = (ColorProp) this.properties.get(ColorProp.TOKEN_NAME);

            sb.append(" style=\"fill:").append(color.evaluate()).append("\"");
        }

        sb.append("/>\n");
        return sb.toString();
    }
}
