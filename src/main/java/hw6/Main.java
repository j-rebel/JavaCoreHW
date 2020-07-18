package hw6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Integer> input = Arrays.asList(1, 2, 5, 16, -1, -2, 0, 32, 3, 5, 8, 23, 4);
        //Double tStart = (double) System.nanoTime();
        sortAndFilter(input);
        //Double tFinish = (double) System.nanoTime();
        //System.out.println("Evaluation time: " + (tFinish - tStart) / 1_000_000.0);
    }

    public static void sortAndFilter(List<Integer> input) {
        List<Integer> output = new ArrayList<>();
        for (Integer i : input) {
            if (i > 0 && i % 2 == 0) {
                output.add(i);
            }
        }

        output.sort(Comparator.naturalOrder());

        for (Integer i : output){
            System.out.println(i);
        }
    }
}