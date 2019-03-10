package words;

import tree.ComparableWithValue;

public class Word implements ComparableWithValue<Word> {

    private Integer key;
    private Integer frequency;
    private String word;
    private Double p;
    private Double q;

    public Word(String word) {
        this.word = word;
    }

    public Word(Integer frequency, String word) {
        this.frequency = frequency;
        this.word = word;
    }

    public Double getP() {
        return p;
    }

    public void setP(Double p) {
        this.p = p;
    }

    public Double getQ() {
        return q;
    }

    public void setQ(Double q) {
        this.q = q;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    @Override
    public int compareTo(Word compared) {
        return word.compareTo(compared.word);
    }

    @Override
    public boolean compareValues(Word word) {
        return word.getWord().equals(this.getWord());
    }

    @Override
    public String toString() {
        return "Word{" +
                "key=" + key +
                ", frequency=" + frequency +
                ", word='" + word + '\'' +
                '}';
    }
}
