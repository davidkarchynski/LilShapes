package cs410;

import cs410.parser.Parser;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Lexer lexer = new Lexer();

        List<String> literals = Arrays.asList("get ","set ","print ","new ");

        lexer.tokenize("files/input.lils", "files/literals.txt");
        lexer.printTokens();

        Parser parser = new Parser(lexer);
        parser.parse();
    }
}