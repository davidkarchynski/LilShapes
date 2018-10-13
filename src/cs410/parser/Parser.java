package cs410.parser;

import cs410.Lexer;
import cs410.parser.shapes.ShapedefNode;
import cs410.util.Util;

import java.io.PrintWriter;
import java.util.*;

public class Parser {
    public static Set<String> supportedShapes = new HashSet<>(Arrays.asList("circle", "rectangle", "line", "ellipse", "path"));
    public static Map<String, ShapedefNode> symbolTable = new HashMap<>();

    private Lexer lexer;
    private String templateFilename;
    private String outputFilename;
    private boolean isDebugMode;

    public Parser(Lexer lexer, String templateFilename, String outputFilename, boolean isDebugMode) {
        this.lexer = lexer;
        this.templateFilename = templateFilename;
        this.outputFilename = outputFilename;
        this.isDebugMode = isDebugMode;
    }

    public void parse() {
        ProgramNode pn = new ProgramNode(lexer);
        pn.parse();

        String output = pn.evaluate();
        String template = Util.readFile(this.templateFilename);

        // TODO: extract the tag into a static constant?
        template = template.replace("<comp_output/>", output);

        try {
            PrintWriter writer = new PrintWriter(this.outputFilename, "UTF-8");
            writer.println(template);
            writer.close();
        } catch (Exception e) {
            System.out.println("Could not write output file");
        }
    }
}
