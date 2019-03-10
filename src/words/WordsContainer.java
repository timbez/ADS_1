package words;

import utils.IntegerUtils;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class WordsContainer {

    private List<Word> words;
    private List<Word> keyWords;

    public WordsContainer(List<Word> words) {
        this.words = words;
    }

    public void sort(Comparator<? super Word> comparator) {
        words.sort(comparator);
    }

    public void initKeyWordsByFrequency(int minimalFrequency) {
        keyWords = new LinkedList<>();
        int totalFrequency = frequenciesSum();

        int sumOfNonKeyFrequencies = 0;
        for (Word word : words) {
            if (word.getFrequency() > minimalFrequency) {
                Double p = (double) word.getFrequency() / (double) totalFrequency;
                Double q = (double) sumOfNonKeyFrequencies / (double) totalFrequency;
                word.setP(p);
                word.setQ(q);
                keyWords.add(word);

                sumOfNonKeyFrequencies = 0;
            } else {
                sumOfNonKeyFrequencies += word.getFrequency();
            }
        }
    }

    public List<Word> getKeyWords() {
        return keyWords;
    }

    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }

    public Word get(int idx) {
        return keyWords.get(idx);
    }

    public Word findByKey(int key) {
        Optional<Word> found = keyWords.stream()
                .filter(word -> word.getKey() == key )
                .findFirst();
        return found.orElse(null);
    }

    public void enumerate(int startingFrom) {
        int key = startingFrom;
        for (Word word : keyWords) {
            word.setKey(key);
            key++;
        }
    }

    public int countKeyWords() {
        return keyWords.size();
    }

    public Integer frequenciesSum() {
        List<Integer> freqs = words.stream().map(Word::getFrequency).collect(Collectors.toList());
        return IntegerUtils.sumList(freqs);
    }
}
