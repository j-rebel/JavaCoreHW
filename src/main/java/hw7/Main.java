package hw7;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    static List<Person> people = new ArrayList<>();

    public static void main(String[] args) {
        fillPeopleList(people, 120);
        printPeopleList(people);
        filterMinorsAndPrint(people);
        filterDutyReadyAndPrint(people);
        filterEducatedWorkersAndPrint(people);
    }

    public static void fillPeopleList(List<Person> list, int limit) {
        for (int i = 0; i < limit; i++) {
            list.add(Person.createRandomPerson());

        }
    }

    public static void printPeopleList(List<Person> list) {
        System.out.println("\n=====  ORIGINAL LIST BEGIN  =====");
        for (Person person : list) {
            System.out.println(person);
        }
        System.out.println("\nQuantity: " + list.size());
        System.out.println("=====  ORIGINAL LIST END  =====");
    }

    public static void filterMinorsAndPrint (List<Person> list) {
        System.out.println("\n=====  MINORS LIST BEGIN  =====");

        Stream<Person> minorsList = list.stream();
        minorsList.filter(x -> x.getAge() < 18)
                .forEach(System.out::println);

        Stream<Person> minorsCount = list.stream();
        long count = minorsCount.filter(x -> x.getAge() < 18)
                .count();
        System.out.println("\nQuantity: " + count);
        System.out.println("=====  MINORS LIST END  =====");
    }

    public static void filterDutyReadyAndPrint (List<Person> list) {
        System.out.println("\n=====  DUTY READY LIST BEGIN  =====");

        Stream<Person> dutyReadyList = list.stream();
        dutyReadyList.filter(x -> x.getAge() >= 18 && x.getAge() <= 27)
                .filter(x -> x.getSex() == Sex.MALE)
                .forEach(System.out::println);

        List<String> surnamesList = list.stream()
                .filter(x -> x.getAge() >= 18 && x.getAge() <= 27)
                .filter(x -> x.getSex() == Sex.MALE)
                .map(x -> x.getSurname())
                .collect(Collectors.toList());
        System.out.println("\nSurnames only:");
        for (String surname: surnamesList) {
            System.out.println("- " + surname);
        }

        Stream<Person> dutyReadyCount = list.stream();
        long count = dutyReadyCount.filter(x -> x.getAge() >= 18 && x.getAge() <= 27)
                .filter(x -> x.getSex() == Sex.MALE)
                .count();
        System.out.println("\nQuantity: " + count);
        System.out.println("=====  DUTY READY LIST END  =====");
    }

    public static void filterEducatedWorkersAndPrint(List<Person> list) {
        System.out.println("\n=====  EDUCATED WORKERS LIST BEGIN  =====");

        List<Person> educatedMalesList = list.stream().filter(x -> x.getAge() >= 18 && x.getAge() <= 65)
                .filter(x -> x.getSex() == Sex.MALE)
                .filter(x -> x.getEducation() == Education.HIGHER)
                .collect(Collectors.toList());

        List<Person> educatedFemalesList = list.stream().filter(x -> x.getAge() >= 18 && x.getAge() <= 60)
                .filter(x -> x.getSex() == Sex.FEMALE)
                .filter(x -> x.getEducation() == Education.HIGHER)
                .collect(Collectors.toList());

        List<Person> allWorkers = Stream.concat(educatedMalesList.stream(), educatedFemalesList.stream())
                .sorted(Comparator.comparing(Person::getSurname))
                .collect(Collectors.toList());

        for (Person person : allWorkers) {
            System.out.println(person);
        }

        List<String> surnamesList = allWorkers.stream()
                .map(x -> x.getSurname())
                .collect(Collectors.toList());
        System.out.println("\nSurnames only:");
        for (String surname: surnamesList) {
            System.out.println("- " + surname);
        }

        long count = allWorkers.stream().count();

        System.out.println("\nQuantity: " + count);
        System.out.println("=====  EDUCATED WORKERS LIST END  =====");
    }

}


