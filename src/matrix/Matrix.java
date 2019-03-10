package matrix;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

public class Matrix<T> {

    private List<List<T>> entries;

    public Matrix(int lineCount, int columnCount, Supplier<T> supplier) {
        prepare(lineCount, columnCount, supplier);
    }

    public Matrix(int lineCount, int columnCount) {
        prepare(lineCount, columnCount, null);
    }

    private void prepare(int lineCount, int columnCount, Supplier<T> supplier) {
        entries = new LinkedList<>();
        for (int i = 0; i < lineCount; i++) {
            List<T> column = new LinkedList<>();

            for (int j = 0; j < columnCount; j++) {
                if (supplier != null)
                    column.add(supplier.get());
                else
                    column.add(null);
            }
            entries.add(column);
        }

    }

    public int linesCount() {
        return entries.size();
    }

    public int columnsCount() {
        return entries.get(0).size();
    }

    public T get(int line, int column) {
        return entries.get(line).get(column);
    }

    public T set(int line, int column, T entry) {
        return entries.get(line).set(column, entry);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();

        for (List<T> line : entries) {
            for (T element : line) {
                s.append(element.toString());
                s.append(" ");
            }
            s.append("\n");
        }
        return s.toString();
    }
}
