package cs410.parser;

import cs410.Lexer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Parser {
    private Lexer lexer;
    public static Set<String> supportedShapes = new HashSet<>(Arrays.asList("circle", "rectangle", "line", "ellipse"));

    public Parser(Lexer lexer) {
        this.lexer = lexer;
    }

    public void parse() {

    }
}
