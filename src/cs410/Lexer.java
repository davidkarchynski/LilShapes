package cs410;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lexer {
    private ArrayList<String> tokens;
    private ArrayList<String> literals;

    public Lexer() {
        tokens = new ArrayList<>();
        literals = new ArrayList<>();
    }

    public void tokenize(String sourceFilename, String litFilename) {
        this.readLiteralsFile(litFilename);
        String input = readFile(sourceFilename);

        input = input.replace("\n","");
        input = input.replaceAll("(\\d+(?:\\.\\d+)?)","_$1_");

        for (String literal : literals) {
            input = input.replace(literal,"_"+literal+"_");
        }

        input = input.replace(" ","");
        input = input.replace(",","");
        input = input.replaceAll("__","_");

        this.tokens = new ArrayList<>(Arrays.asList(input.split("_")));
        this.tokens.remove(0);
    }

    private static String readFile(String filename) {
        String inputString = "";
        try {
            inputString = new String(Files.readAllBytes(Paths.get(filename)), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Could not read file " + filename);
            System.exit(0);
        }
        return inputString;
    }

    private void readLiteralsFile(String filename) {
        String literalsString = readFile(filename);
        literalsString = literalsString.replace("\n", "");

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
        return "NULL";
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
