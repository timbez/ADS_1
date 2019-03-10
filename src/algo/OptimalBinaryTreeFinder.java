package algo;

import matrix.Matrix;
import tree.Node;
import tree.Tree;
import words.Word;
import words.WordsContainer;

import java.util.*;

public class OptimalBinaryTreeFinder {

    private WordsContainer wordsContainer;
    private Matrix<WordCostMatrixEntry> matrix;
    private Matrix<Double> weights;
    private Tree<Word> tree;
    private List<Double> q;
    private List<Double> p;

    public OptimalBinaryTreeFinder(WordsContainer wordsContainer) {
        this.wordsContainer = wordsContainer;
        int dim = wordsContainer.countKeyWords();
        matrix = new Matrix<>(dim, dim, WordCostMatrixEntry::new);
        weights = new Matrix<>(dim, dim);
        initQ();
        initP();
    }

    public Tree<Word> constructTree() {
        fillWeightMatrix();
        fillMatrix();
        return makeTreeFromMatrix();
    }

    private void fillWeightMatrix() {
        int offset = 0;

        while (offset < matrix.columnsCount()) {
            int i = 0;
            int j = offset;
            while (j < weights.columnsCount() && i < weights.linesCount()) {

                if (i == j) {
                    weights.set(i, j, wordsContainer.get(i).getQ());
                } else {
                    weights.set(i, j, w(i, j));

                }
                i++;
                j++;
            }
            offset++;
        }
    }

    private void fillMatrix() {
        int offset = 0;

        while (offset < matrix.columnsCount()) {
            int i = 0;
            int j = offset;
            while (j < matrix.columnsCount() && i < matrix.linesCount()) {

                if (offset == 0) {
                    matrix.get(i, j).setCost(w(i, j));
                    matrix.get(i, j).setWordKey(null);

                } else {
                    WordCostMatrixEntry newEntry = cost(i, j);
                    matrix.set(i, j, newEntry);
                }
                i++;
                j++;
            }
            offset++;
        }
    }

    private Tree<Word> makeTreeFromMatrix() {
        WordCostMatrixEntry lastEntry = matrix.get(0, matrix.columnsCount() - 1);
        int wordKey = lastEntry.getWordKey();
        Word rootWord = wordsContainer.get(wordKey - 1);
        Node<Word> root = new Node<>(rootWord);

        tree = new Tree<>(root);

        addNextLeftNode(root, lastEntry.getWordKey(), 0);
        addNextRightNode(root, lastEntry.getWordKey(), matrix.columnsCount() - 1);

        return tree;
    }

    private void addNextLeftNode(Node current, int currentWordKey, int start) {
        int end = currentWordKey - 1;

        if (start == end)
            return;

        WordCostMatrixEntry entry = matrix.get(start, end);
        Word word = wordsContainer.get(entry.getWordKey() - 1);

        Node nextLeft = new Node<>(word);
        current.setLeft(nextLeft);

        addNextLeftNode(nextLeft, entry.getWordKey(), start);
        addNextRightNode(nextLeft, entry.getWordKey(), end);
    }

    private void addNextRightNode(Node current, int currentWordKey, int end) {
        int start = currentWordKey;

        if (start == end)
            return;

        WordCostMatrixEntry entry = matrix.get(start, end);
        Word word = wordsContainer.get(entry.getWordKey() - 1);

        Node nextRight = new Node<>(word);
        current.setRight(nextRight);

        addNextLeftNode(nextRight, entry.getWordKey(), start);
        addNextRightNode(nextRight, entry.getWordKey(), end);
    }

    private WordCostMatrixEntry cost(int start, int end) {
        WordCostMatrixEntry potential = matrix.get(start, end);
        if (potential.getCost() != null) {
            return potential;
        }

        double weight = w(start, end);
        List<Integer> relevantKeys = getRelevantKeys(start, end);

        List<WordCostMatrixEntry> costEntriesToSelectFrom = new ArrayList<>();
        for (Integer key : relevantKeys) {
            double cost =
                    cost(start, key - 1).getCost()
                    + cost(key, end).getCost()
                    + weight;
            costEntriesToSelectFrom.add(new WordCostMatrixEntry(key, cost));
        }

        // get minimum of possible options
        costEntriesToSelectFrom.sort(Comparator.comparing(WordCostMatrixEntry::getCost));
        return costEntriesToSelectFrom.get(0);
    }

    private List<Integer> getRelevantKeys(int start, int end) {
        List<Integer> keys = new LinkedList<>();
        for (int i = start + 1; i <= end; i++) {
           keys.add(i);
        }
        return keys;
    }

    private double w(int i, int j) {
        Double weight = weights.get(i, j);
        if (weight != null) {
            return weight;

        } else {
            if (i == j + 1) {
                return q(i) + q(j) + p(j);

            } else {
                return w(i, j - 1) + q(j) + p(j);
            }
        }
    }

    private double p(int key) {
        return p.get(key - 1);
    }

    private double q(int key) {
        return q.get(key);
    }

    private void initQ() {
        q = new LinkedList<>();
        for (Word word : wordsContainer.getKeyWords()) {
            q.add(word.getQ());
        }
    }

    private void initP() {
        p = new LinkedList<>();
        for (Word word : wordsContainer.getKeyWords()) {
            p.add(word.getP());
        }
    }

}
