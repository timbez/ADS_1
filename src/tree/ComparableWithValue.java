package tree;

public interface ComparableWithValue<T> {

    int compareTo(T object);

    boolean compareValues(T object);
}
