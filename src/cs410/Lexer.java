package cs410;

import cs410.util.Util;
import java.util.ArrayList;
import java.util.Arrays;

public class Lexer {
    public static final String NULL_TOKEN = "NULL";

    private ArrayList<String> tokens;
    private ArrayList<String> literals;

    public Lexer() {
        tokens = new ArrayList<>();
        literals = new ArrayList<>();
    }

    public void tokenize(String sourceFilename, String litFilename) {
        this.readLiteralsFile(litFilename);
        String input = Util.readFile(sourceFilename);


        input = input.replaceAll("(?<!\\w)((-+)?\\d+(?:\\.\\d+)?)","_$1_");
        input = input.replace("\n","").replace("\r","");

        for (String literal : literals) {
            // FIXME: hacky way to fix "at" literal interfering with "path" literal, handle this properly
            if (literal.equals("path ")) {
                input = input.replace(literal,"_P!A!T!H_");
                continue;
            }
            input = input.replace(literal,"_"+literal+"_");
        }

        System.out.println(input);

        input = input.replace("P!A!T!H","path");

        //FIXME: replace with one regexp for performance
        input = input.replace(" ","");
        input = input.replace(",","");
        input = input.replace(":", "");
        input = input.replaceAll("__","_");

        this.tokens = new ArrayList<>(Arrays.asList(input.split("_")));
        this.tokens.remove(0);
        removeCommentsFromTokens();
    }

    private void removeCommentsFromTokens() {
        ArrayList<String> comments = new ArrayList<>();
        int i = 0;

        for (String t: tokens) {
            if (t.length() > 1 && t.substring(0,2).equals("//")) {
                comments.add(t);
            } else if (t.contains("//")){
                tokens.set(i, t.substring(0, t.indexOf("//")));
            }
            i++;
        }
        tokens.removeAll(comments);
    }

    private void readLiteralsFile(String filename) {
        String literalsString = Util.readFile(filename);
        literalsString = literalsString.replace("\n","").replace("\r","");

        this.literals = new ArrayList<>(Arrays.asList(literalsString.split(",")));
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

    public String getNext(String regexp) {
        if (!checkToken(regexp)) {
            System.out.println("Failed to parse at token: " + this.peek());
            System.exit(1);
        }
        return this.getNext();
    }

    public boolean empty() {
        return this.tokens.size() == 0;
    }

    public void printTokens() {
        System.out.println(this.tokens.toString());
    }
}
