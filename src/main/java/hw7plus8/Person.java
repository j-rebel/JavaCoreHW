package hw7plus8;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
public @Data class Person {

    private final static List<String> maleNamesList = Arrays.asList(
            "Petr",
            "Sergei",
            "Nikolai",
            "Ivan",
            "Denis",
            "Iliya",
            "Stas"
    );
    private final static List<String> femaleNamesList = Arrays.asList(
            "Maria",
            "Elena",
            "Anna",
            "Olga",
            "Natalia",
            "Galina",
            "Zoya"
    );
    private final static List<String> surnamesList = Arrays.asList(
            "Petrov",
            "Ivanov",
            "Sidorov",
            "Popov",
            "Smirnov",
            "Kuznetsov"
    );

    private final static RandomInterval maleNameInterval = new RandomInterval(0, maleNamesList.size() - 1);
    private final static RandomInterval femaleNameInterval = new RandomInterval(0, femaleNamesList.size() - 1);
    private final static RandomInterval surnameInterval = new RandomInterval(0, surnamesList.size() - 1);
    private final static RandomInterval sexInterval = new RandomInterval(0, Sex.values().length - 1);
    private final static RandomInterval educationInterval = new RandomInterval(0, Education.values().length - 1);
    private final static RandomInterval ageInterval = new RandomInterval(0, 100);

    private Sex sex;
    private String name;
    private String surname;
    private Integer age;
    private Education education;

    public static Person createRandomPerson() {
        Sex randomSex = Sex.values()[sexInterval.getRandomWithinInterval()];
        String randomName = randomSex == Sex.MALE
                ? maleNamesList.get(maleNameInterval.getRandomWithinInterval())
                : femaleNamesList.get(femaleNameInterval.getRandomWithinInterval());
        String randomSurname = randomSex == Sex.MALE
                ? surnamesList.get(surnameInterval.getRandomWithinInterval())
                : surnamesList.get(surnameInterval.getRandomWithinInterval()) + "a";
        int randomAge = ageInterval.getRandomWithinInterval();
        Education randomEducation = Education.values()[educationInterval.getRandomWithinInterval()];

        return new Person(
                randomSex,
                randomName,
                randomSurname,
                randomAge,
                randomEducation
        );
    }
}
