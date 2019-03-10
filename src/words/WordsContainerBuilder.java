package words;

import java.util.ArrayList;
import java.util.List;

public class WordsContainerBuilder {

    public static final String SEPARATOR = " ";

    public static WordsContainer fromLines(List<String> lines) {
        List<Word> words = new ArrayList<>();

        for (String line : lines) {
            String[] split = line.split(SEPARATOR);
            words.add(new Word(Integer.parseInt(split[0]), split[1]));
        }
        return new WordsContainer(words);
    }
}
