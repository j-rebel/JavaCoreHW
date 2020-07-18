package hw6;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class StreamMain {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 5, 16, -1, -2, 0, 32, 3, 5, 8, 23, 4);
        //Double tStart = (double) System.nanoTime();
        sortAndFilter(list);
        //Double tFinish = (double) System.nanoTime();
        //System.out.println("Evaluation time: " + (tFinish - tStart) / 1_000_000.0);
    }

    public static void sortAndFilter(List<Integer> list) {
        Stream<Integer> stream = list.stream();
        stream.filter(x -> x > 0)
                .filter(x -> x % 2 == 0)
                .sorted(Comparator.naturalOrder())
                .forEach(System.out::println);
    }
}