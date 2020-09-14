package persistence;

import model.Beach;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


// a reader to read the list of beaches data from a file (from UBC 210 class)
public class Reader {
    public static final String DELIMITER = ",";

    // EFFECTS: returns a list of accounts parsed from file; throws
    // IOException if an exception is raised when opening / reading from file
    public static List<Beach> readBeaches(File file) throws IOException {
        List<String> fileContent = readFile(file);
        return parseContent(fileContent);
    }


    // EFFECTS: returns content of file as a list of strings, each string
    // containing the content of one row of the file
    private static List<String> readFile(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    // EFFECTS: returns a list of accounts parsed from list of strings
    // where each string contains data for one account
    private static List<Beach> parseContent(List<String> fileContent) {
        List<Beach> beachList = new ArrayList<>();

        for (String line : fileContent) {
            ArrayList<String> lineComponents = splitString(line);
            beachList.add(parseBeach(lineComponents));
        }

        return beachList;
    }

    // EFFECTS: returns a list of strings obtained by splitting line on DELIMITER
    private static ArrayList<String> splitString(String line) {
        String[] splits = line.split(DELIMITER);
        return new ArrayList<>(Arrays.asList(splits));
    }

    // REQUIRES: components has size 4 where element 0 represents the
    // name of the beach to be constructed, element 1 represents
    // the direction
    // EFFECTS: returns a beach constructed from components
    private static Beach parseBeach(List<String> components) {
        int direction = Integer.parseInt(components.get(1));
        String name = components.get(0);
        return new Beach(name, direction);
    }

}
