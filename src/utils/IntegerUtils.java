package utils;

import java.util.List;

public class IntegerUtils {

    public static int sumList(List<Integer> ints) {
        int sum = 0;

        for (int i : ints)
            sum = sum + i;

        return sum;
    }
}
