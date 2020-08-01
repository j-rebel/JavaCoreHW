package hw7plus8;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        /*
        Evaluation time normal:   0.0722107 seconds
        Evaluation time parallel: 4.743E-4 seconds
        Parallel was faster for:  99.0%
         */
        filterAndPrintEvalTime(10);

        /*
        Evaluation time normal:   0.084125 seconds
        Evaluation time parallel: 8.469E-4 seconds
        Parallel was faster for:  98.0%
         */
        //filterAndPrintEvalTime(100);

        /*
        Evaluation time normal:   0.0930492 seconds
        Evaluation time parallel: 0.0057151 seconds
        Parallel was faster for:  93.0%
         */
        //filterAndPrintEvalTime(1_000);

        /*
        Evaluation time normal:   0.1077865 seconds
        Evaluation time parallel: 0.0258606 seconds
        Parallel was faster for:  76.0%
         */
        //filterAndPrintEvalTime(10_000);

        /*
        Evaluation time normal:   0.3213521 seconds
        Evaluation time parallel: 0.1863551 seconds
        Parallel was faster for:  42.0%
         */
        //filterAndPrintEvalTime(100_000);

        /*
        Evaluation time normal:   2.4159289 seconds
        Evaluation time parallel: 2.227164 seconds
        Parallel was faster for:  7.0%
         */
        //filterAndPrintEvalTime(1_000_000);

        /*
        Evaluation time normal:   26.7274945 seconds
        Evaluation time parallel: 25.2431592 seconds
        Parallel was faster for:  5.0%
         */
        //filterAndPrintEvalTime(10_000_000);

        /*
        Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
         */
        //filterAndPrintEvalTime(100_000_000);

    }

    public static List<Person> fillPeopleList(int limit) {
        List<Person> list = new ArrayList<>();
        for (int i = 0; i < limit; i++) {
            list.add(Person.createRandomPerson());
        }
        return list;
    }

    public static void filterAndPrintEvalTime (int peopleNumber) {
        List<Person> people = fillPeopleList(peopleNumber);
        Double tStartNormal = (double) System.nanoTime();
        StreamFilter streamFilterNormal = new StreamFilter(people, false);
        streamFilterNormal.filterPeople();
        Double tFinishNormal = (double) System.nanoTime();
        double resNormal = (tFinishNormal - tStartNormal) / 1_000_000_000.0;

        Double tStartParallel = (double) System.nanoTime();
        StreamFilter streamFilterParallel = new StreamFilter(people, true);
        streamFilterParallel.filterPeople();
        Double tFinishParallel = (double) System.nanoTime();
        double resParallel = (tFinishParallel - tStartParallel) / 1_000_000_000.0;

        double percentageNormalToParallel = Math.floor((resNormal - resParallel) / (resNormal / 100.0));

        System.out.println("\nEvaluation time normal:   " + resNormal + " seconds");
        System.out.println("Evaluation time parallel: " + resParallel + " seconds");
        System.out.println("Parallel was faster for:  " + percentageNormalToParallel + "%");
    }
}


