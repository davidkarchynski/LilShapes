package cs410;

import cs410.util.ParseErrorException;
import cs410.util.Util;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Lexer {
    public static final String NULL_TOKEN = "NULL";

    private ArrayList<String> tokens;
    private final ArrayList<String> literals = new ArrayList<String>(Arrays.asList("circle ", "rectangle ", "ellipse ", "line ", "path ", "pos:", "width:", "height:", "radius:", "color:", "move:", "to", "from", "draw ", "at", "lineColor:", "lineWidth:"));
    private String code;

    public Lexer(String codeInput) {
        this.tokens = new ArrayList<>();

        this.code = codeInput;
    }

    public void tokenize() {
        String input = this.code;

        input = input.replaceAll("(?<!\\w)((-+)?\\d+(?:\\.\\d+)?)","_$1_");
        input = removeAllComments(input);
        input = input.replace("\n","").replace("\r","");

        for (String literal : literals) {
            // FIXME: hacky way to fix "at" literal interfering with "path" literal, handle this properly
            if (literal.equals("path ")) {
                input = input.replace(literal,"_P!A!T!H_");
                continue;
            }
            input = input.replace(literal,"_"+literal+"_");
        }

        input = input.replace("P!A!T!H","path");

        //FIXME: replace with one regexp for performance
        input = input.replace(" ","");
        input = input.replace(",","");
        input = input.replace(":", "");
        input = input.replaceAll("__","_");

        this.tokens = new ArrayList<>(Arrays.asList(input.split("_")));
        this.tokens.remove(0);
    }

    private String removeAllComments(String input) {
        List<String> lines = new ArrayList<String>(Arrays.asList(input.split("\n")));

        return lines.stream().map(line -> line.contains("//") ?
                line.substring(0, line.indexOf("//")) : line).collect(Collectors.joining("\n"));
    }

    public String peek() {
        if (!this.empty()) {
            return tokens.get(0);
        }
        return "";
    }

    public boolean checkToken(String regexp){
        String s = peek();
        return (s.matches(regexp));
    }

    public String getNext() {
        if (!this.empty()) {
            return tokens.remove(0);
        }
        return Lexer.NULL_TOKEN;
    }

    public String getNext(String regexp) throws ParseErrorException {
        if (!checkToken(regexp)) {
            throw new ParseErrorException("Failed to parse at token: " + this.peek());
        }
        return this.getNext();
    }

    public boolean empty() {
        return this.tokens.size() == 0;
    }
}
