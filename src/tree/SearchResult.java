package tree;

public class SearchResult {

    private Node node;
    private int comparisons;

    public SearchResult(Node node, int comparisons) {
        this.node = node;
        this.comparisons = comparisons;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public int getComparisons() {
        return comparisons;
    }

    public void setComparisons(int comparisons) {
        this.comparisons = comparisons;
    }
}
