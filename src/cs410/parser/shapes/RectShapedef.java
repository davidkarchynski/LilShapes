package cs410.parser.shapes;

import cs410.parser.properties.*;

import java.util.Arrays;
import java.util.HashSet;

public class RectShapedef extends ShapedefNode {

    public static final String TOKEN_NAME = "rectangle";

    @Override
    public String name() {
        return RectShapedef.TOKEN_NAME;
    }

    @Override
    public void parse() {
        super.parse();
    }

    public RectShapedef() {
        this.supportedProps = new HashSet<>(Arrays.asList("width", "height", "pos", "color"));
        this.requiredProps = new HashSet<>(Arrays.asList("width", "height", "pos"));
    }

    @Override
    public String evaluate() {
        // Make sure we have all the required properties
        this.verifyRequiredProps();

        HeightProp height = (HeightProp) this.properties.get(HeightProp.TOKEN_NAME);
        WidthProp width = (WidthProp) this.properties.get(WidthProp.TOKEN_NAME);
        PositionProp pos = (PositionProp) this.properties.get(PositionProp.TOKEN_NAME);

        StringBuilder sb = new StringBuilder();

        sb.append("<rect ").append(pos.evaluate())
                .append(" ")
                .append(width)
                .append(" ")
                .append(height);

        if (properties.containsKey(ColorProp.TOKEN_NAME)) {
            ColorProp color = (ColorProp) this.properties.get(ColorProp.TOKEN_NAME);

            sb.append("style=\"").append(color.evaluate()).append("\"");
        }

        sb.append("/>\n");
        return sb.toString();
    }
}
