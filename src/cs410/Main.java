package cs410;

import cs410.parser.Parser;
import cs410.util.ParseErrorException;
import cs410.util.Util;

public class Main {
    public static void main(String[] args) {
        String input = Util.readFile("files/input.lils");
        Parser parser = new Parser(input, true);

        try {
            System.out.println(parser.parse());
        } catch (ParseErrorException e) {
            System.out.println(e.getMessage());
        }
    }
}