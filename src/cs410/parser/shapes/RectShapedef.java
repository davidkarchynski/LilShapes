package cs410.parser.shapes;

import java.util.Arrays;
import java.util.HashSet;

public class RectShapedef extends ShapedefNode {
    @Override
    public void parse() {
        super.parse();
    }

    public RectShapedef() {
        this.supportedProps = new HashSet<>(Arrays.asList("width:", "height:", "pos:", "color:"));
        this.name = "Rectangle";
    }

    @Override
    public String evaluate() {
        return null;
    }
}
