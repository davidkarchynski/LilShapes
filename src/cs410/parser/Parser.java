package cs410.parser;

import cs410.Lexer;
import cs410.parser.shapes.ShapedefNode;
import cs410.util.ParseErrorException;
import cs410.util.Util;

import java.io.PrintWriter;
import java.util.*;

public class Parser {
    public static Set<String> supportedShapes = new HashSet<>(Arrays.asList("circle", "rectangle", "line", "ellipse", "path"));
    public static Map<String, ShapedefNode> symbolTable = new HashMap<>();

    private Lexer lexer;
    private boolean isDebugMode;

    public Parser(String input, boolean isDebugMode) {
        this.lexer = new Lexer(input);
        this.isDebugMode = isDebugMode;
    }

    public String parse() throws ParseErrorException {
        lexer.tokenize();

        ProgramNode pn = new ProgramNode(lexer);
        pn.parse();

        String output = pn.evaluate();
        if (isDebugMode) {
            String debugInfo = pn.debugInfo();
            output = output + " \n" + debugInfo;
        }

        Parser.symbolTable = new HashMap<>();
        return output;
    }

    public int getLine() {
        return this.lexer.getLine();
    }
}
