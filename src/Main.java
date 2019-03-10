import algo.OptimalBinaryTreeFinder;
import tree.SearchResult;
import tree.Tree;
import utils.FileUtils;
import words.Word;
import words.WordsContainer;
import words.WordsContainerBuilder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static final String WORDS_RESOURCE_PATH = "resources\\dictionary.txt";

    public static final int MINIMAL_FREQUENCY = 50000;

    public static Tree<Word> optimal;

    public static void main(String[] args) throws IOException {
        File wordsResource = new File(WORDS_RESOURCE_PATH);
        if (!wordsResource.exists()) {
            throw new IllegalStateException("Words file not found in path" + WORDS_RESOURCE_PATH + "!");
        }

        List<String> content = FileUtils.readFile(wordsResource);
        WordsContainer wordsContainer = WordsContainerBuilder.fromLines(content);
        content = null;

        wordsContainer.sort(Comparator.comparing(Word::getWord));
        wordsContainer.initKeyWordsByFrequency(MINIMAL_FREQUENCY);
        wordsContainer.enumerate(0);

        OptimalBinaryTreeFinder optimalBinaryTreeFinder = new OptimalBinaryTreeFinder(wordsContainer);
        optimal = optimalBinaryTreeFinder.constructTree();


        // test if tree is correctly assembled
        List<Word> failed = new ArrayList<>();
        List<Integer> costs = new ArrayList<>();
        for (Word word : wordsContainer.getKeyWords()) {
            SearchResult found = optimal.search(word);
            if (found.getNode() == null) {
                failed.add(word);
            }

            costs.add(found.getComparisons());
        }

        assert failed.isEmpty();

        pocet_porovnani("the");
        pocet_porovnani("of");
        pocet_porovnani("and");
        pocet_porovnani("she");
        pocet_porovnani("a");
        pocet_porovnani("in");
        pocet_porovnani("to");
        pocet_porovnani("it");
        pocet_porovnani("is");
        pocet_porovnani("was");
        pocet_porovnani("i");
        pocet_porovnani("aaaaa");
        pocet_porovnani("from");
    }

    public static void pocet_porovnani(String word) {
        Word wrapper = new Word(word);
        SearchResult result = optimal.search(wrapper);

        if (result.getNode() == null) {
            System.out.println("Slovo " + word + " v strome nebolo možné nájsť!");

        } else {
            System.out.println("Slovo " + word + " bolo nájdené na " + result.getComparisons() + " porovnaní!");
        }
    }
}
