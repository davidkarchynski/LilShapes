package cs410;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Lexer lexer = new Lexer();

        List<String> literals = Arrays.asList("get ","set ","print ","new ");

        lexer.tokenize("files/input.lils", "files/literals.txt");

        lexer.printTokens();
    }
}