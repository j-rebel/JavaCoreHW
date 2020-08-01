package hw7;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
public @Data class Person {
    private String name;
    private String surname;
    private Integer age;
    private Sex sex;
    private Education education;

    private static List<String> namesList = Arrays.asList(
            "Petr",
            "Sergei",
            "Nikolai",
            "Ivan",
            "Denis",
            "Iliya",
            "Stas",
            "Maria",
            "Elena",
            "Anna",
            "Olga",
            "Natalia",
            "Galina",
            "Zoya"
    );
    private static List<String> surnamesList = Arrays.asList(
            "Petrov(-a)",
            "Ivanov(-a)",
            "Sidorov(-a)",
            "Popov(-a)",
            "Smirnov(-a)",
            "Kuznetsov(-a)"
    );

    public static Person createRandomPerson() {
        RandomInterval nameInterval = new RandomInterval(0, Person.namesList.size() - 1);
        RandomInterval surnameInterval = new RandomInterval(0, Person.surnamesList.size() - 1);
        RandomInterval sexInterval = new RandomInterval(0, Sex.values().length - 1);
        RandomInterval educationInterval = new RandomInterval(0, Education.values().length - 1);
        RandomInterval ageInterval = new RandomInterval(0, 100);

        return new Person(
                Person.namesList.get(nameInterval.getRandomWithinInterval()),
                Person.surnamesList.get(surnameInterval.getRandomWithinInterval()),
                ageInterval.getRandomWithinInterval(),
                Sex.values()[sexInterval.getRandomWithinInterval()],
                Education.values()[educationInterval.getRandomWithinInterval()]
        );
    }
}
