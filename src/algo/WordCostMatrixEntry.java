package algo;

public class WordCostMatrixEntry {

    private Integer wordKey;
    private Double cost;

    public WordCostMatrixEntry() {
    }

    public WordCostMatrixEntry(Integer wordKey, Double cost) {
        this.wordKey = wordKey;
        this.cost = cost;
    }

    public Integer getWordKey() {
        return wordKey;
    }

    public void setWordKey(Integer wordKey) {
        this.wordKey = wordKey;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        if (cost != null && wordKey == null) {
            return cost.toString();

        } else if (cost == null) {
            return "N";

        } else {
            return cost + "^" + wordKey;
        }
    }
}
