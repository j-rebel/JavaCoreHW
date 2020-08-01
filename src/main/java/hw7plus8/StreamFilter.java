package hw7plus8;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamFilter {

    private List<Person> people;
    private Stream<Person> minorsCountStream;
    private Stream<Person> dutyReadySurnamesStream;
    private Stream<Person> dutyReadyCountStream;
    private Stream<Person> educatedMaleStream;
    private Stream<Person> educatedFemaleStream;
    private boolean useParallelMode;

    public StreamFilter (List<Person> people, boolean useParallelMode) {
        this.people = people;
        this.useParallelMode = useParallelMode;
        if (useParallelMode) {
            this.minorsCountStream = this.people.stream();
            this.dutyReadySurnamesStream = this.people.stream();
            this.dutyReadyCountStream = this.people.stream();
            this.educatedMaleStream = this.people.stream();
            this.educatedFemaleStream = this.people.stream();
        } else {
            this.minorsCountStream = this.people.parallelStream();
            this.dutyReadySurnamesStream = this.people.parallelStream();
            this.dutyReadyCountStream = this.people.parallelStream();
            this.educatedMaleStream = this.people.parallelStream();
            this.educatedFemaleStream = this.people.parallelStream();
        }
    }

    public void filterPeople() {
        System.out.println("\n=====  MINORS LIST BEGIN  =====");
        getMinorsCountAndPrint();
        System.out.println("=====  MINORS LIST END  =====");

        System.out.println("\n=====  DUTY READY LIST BEGIN  =====");
        filterDutyReadySurnamesAndPrint();
        System.out.println("=====  DUTY READY LIST END  =====");

        System.out.println("\n=====  EDUCATED WORKERS LIST BEGIN  =====");
        filterEducatedWorkersAndPrint();
        System.out.println("=====  EDUCATED WORKERS LIST END  =====");
    }

    private void getMinorsCountAndPrint () {
        long count = minorsCountStream.filter(x -> x.getAge() < 18)
                .count();
        System.out.println("\nQuantity: " + count);
    }

    private void filterDutyReadySurnamesAndPrint () {
        List<String> surnamesList = dutyReadySurnamesStream
                .filter(x -> x.getAge() >= 18 && x.getAge() <= 27)
                .filter(x -> x.getSex() == Sex.MALE)
                .map(Person::getSurname)
                .collect(Collectors.toList());
        System.out.println("\nSurnames only:");
        for (String surname: surnamesList) {
            System.out.println("- " + surname);
        }

        long count = dutyReadyCountStream.filter(x -> x.getAge() >= 18 && x.getAge() <= 27)
                .filter(x -> x.getSex() == Sex.MALE)
                .count();
        System.out.println("\nQuantity: " + count);
    }

    private void filterEducatedWorkersAndPrint() {
        List<Person> educatedMalesList = educatedMaleStream.filter(x -> x.getAge() >= 18 && x.getAge() <= 65)
                .filter(x -> x.getSex() == Sex.MALE)
                .filter(x -> x.getEducation() == Education.HIGHER)
                .collect(Collectors.toList());

        List<Person> educatedFemalesList = educatedFemaleStream.filter(x -> x.getAge() >= 18 && x.getAge() <= 60)
                .filter(x -> x.getSex() == Sex.FEMALE)
                .filter(x -> x.getEducation() == Education.HIGHER)
                .collect(Collectors.toList());

        List<Person> allWorkers = new ArrayList<>();

        if (useParallelMode) {
            allWorkers = Stream.concat(educatedMalesList.parallelStream(), educatedFemalesList.parallelStream())
                    .sorted(Comparator.comparing(Person::getSurname))
                    .collect(Collectors.toList());
        } else {
            allWorkers = Stream.concat(educatedMalesList.stream(), educatedFemalesList.stream())
                    .sorted(Comparator.comparing(Person::getSurname))
                    .collect(Collectors.toList());
        }

        List<String> surnamesList = allWorkers.stream()
                .map(Person::getSurname)
                .collect(Collectors.toList());
        System.out.println("\nSurnames only:");
        for (String surname: surnamesList) {
            System.out.println("- " + surname);
        }

        long count = allWorkers.size();

        System.out.println("\nQuantity: " + count);
    }

}
