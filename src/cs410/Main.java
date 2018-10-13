package cs410;

import cs410.parser.Parser;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static List<String> errorsList = new ArrayList<>();

    public static void main(String[] args) {
        Lexer lexer = new Lexer();

        lexer.tokenize("files/input_2.lils", "files/literals.txt");
        lexer.printTokens();

        Parser parser = new Parser(lexer, "files/html_template.html", "files/output.html", true);
        parser.parse();
    }
}