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

        HeightProp height = (HeightProp) this.properties.get("height");
        WidthProp width;
        PositionProp pos;
        ColorProp color;
        return null;
    }
}
