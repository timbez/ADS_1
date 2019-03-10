package tree;

public class Tree<T extends ComparableWithValue<T>> {

    private Node<T> root;

    public Tree(Node<T> root) {
        this.root = root;
    }

    public void add(T toAdd) {
        Node current = root;
        Node last;
        boolean goLeft;

        do {
            last = current;
            if (current.getValue().compareTo(toAdd) < 0) {
                current = current.getRight();
                goLeft = false;
            } else {
                current = current.getLeft();
                goLeft = true;
            }
        } while (current != null);

        Node newNode = new Node(toAdd);
        if (goLeft) {
            last.setLeft(newNode);
        } else {
            last.setRight(newNode);
        }
    }

    public SearchResult search(T searched) {
        Node current = root;
        int comparisons = 1;

        while (current != null && !current.getValue().compareValues(searched)) {

            if (current.getValue().compareTo(searched) < 0) {
                current = current.getRight();
            } else {
                current = current.getLeft();
            }

            comparisons++;
        }

        return new SearchResult(current, comparisons);
    }
}
