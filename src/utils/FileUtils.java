package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class FileUtils {

    public static List<String> readFile(File file) throws IOException {
        List<String> content = new LinkedList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.add(line);
            }

            return content;

        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }

    }
}
