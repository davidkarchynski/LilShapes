package cs410.parser;

import cs410.Lexer;

public class Parser {
    private Lexer lexer;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
    }

    public void parse() {
        while (!lexer.empty()) {
            String nextToken = lexer.peek();
            switch (nextToken) {
                case "circle":
                case "":
            }
        }
    }
}
