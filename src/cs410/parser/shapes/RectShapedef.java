package cs410.parser.shapes;

import cs410.Lexer;
import cs410.parser.properties.singleValue.HeightProp;
import cs410.parser.properties.singleValue.LineWidthProp;
import cs410.parser.properties.singleValue.WidthProp;
import cs410.parser.properties.stringValue.ColorProp;
import cs410.parser.properties.stringValue.LineColorProp;
import cs410.util.ParseErrorException;

import java.util.Arrays;
import java.util.HashSet;

public class RectShapedef extends ShapedefNode {

    @Override
    public String name() {
        return RectShapedef.TOKEN_NAME;
    }

    public RectShapedef(Lexer lexer) {
        super(lexer);
        this.TOKEN_NAME = "rectangle";
        this.supportedProps = new HashSet<>(Arrays.asList("width", "height", "color", "lineColor", "lineWidth"));
        this.requiredProps = new HashSet<>(Arrays.asList("width", "height"));
    }

    @Override
    public String evaluate() throws ParseErrorException {
        // Make sure we have all the required properties
        this.verifyRequiredProps();

        HeightProp height = (HeightProp) this.properties.get(HeightProp.TOKEN_NAME);
        WidthProp width = (WidthProp) this.properties.get(WidthProp.TOKEN_NAME);

        StringBuilder sb = new StringBuilder();

        sb.append("<rect x=\"%s\" y=\"%s\"")
                .append(" ")
                .append(String.format("width=\"%s\" height=\"%s\"", width.evaluate(), height.evaluate()))
                .append(" ");


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
