package cs410.parser.shapes;

import cs410.Lexer;
import cs410.parser.properties.singleValue.HeightProp;
import cs410.parser.properties.singleValue.WidthProp;
import cs410.parser.properties.stringValue.ColorProp;

import java.util.Arrays;
import java.util.HashSet;

public class RectShapedef extends ShapedefNode {

    public static final String TOKEN_NAME = "rectangle";

    @Override
    public String name() {
        return RectShapedef.TOKEN_NAME;
    }

    public RectShapedef(Lexer lexer) {
        super(lexer);
        this.supportedProps = new HashSet<>(Arrays.asList("width", "height", "color"));
        this.requiredProps = new HashSet<>(Arrays.asList("width", "height"));
    }

    @Override
    public String evaluate() {
        // Make sure we have all the required properties
        this.verifyRequiredProps();

        HeightProp height = (HeightProp) this.properties.get(HeightProp.TOKEN_NAME);
        WidthProp width = (WidthProp) this.properties.get(WidthProp.TOKEN_NAME);

        StringBuilder sb = new StringBuilder();

        sb.append("<rect x=\"%s\" y=\"%s\"")
                .append(" ")
                .append(String.format("width=\"%s\" height=\"%s\"", width.evaluate(), height.evaluate()))
                .append(" ");

        if (properties.containsKey(ColorProp.TOKEN_NAME)) {
            ColorProp color = (ColorProp) this.properties.get(ColorProp.TOKEN_NAME);

            sb.append(" style=\"fill:").append(color.evaluate()).append("\"");
        }

        sb.append("/>\n");
        return sb.toString();
    }
}
