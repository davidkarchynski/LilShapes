package cs410.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Util {
    private static Util ourInstance = new Util();

    public static Util getInstance() {
        return ourInstance;
    }

    private Util() {
    }

    public static String readFile(String filename) {
        String inputString = "";
        try {
            inputString = new String(Files.readAllBytes(Paths.get(filename)), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Could not read file " + filename);
            System.exit(0);
        }
        return inputString;
    }

}
