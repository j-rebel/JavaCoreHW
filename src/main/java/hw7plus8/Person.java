package hw7plus8;

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

    public static Person createRandomPerson() {
        List<String> maleNamesList = Arrays.asList(
                "Petr",
                "Sergei",
                "Nikolai",
                "Ivan",
                "Denis",
                "Iliya",
                "Stas"
        );
        List<String> femaleNamesList = Arrays.asList(
                "Maria",
                "Elena",
                "Anna",
                "Olga",
                "Natalia",
                "Galina",
                "Zoya"
        );
        List<String> surnamesList = Arrays.asList(
                "Petrov",
                "Ivanov",
                "Sidorov",
                "Popov",
                "Smirnov",
                "Kuznetsov"
        );

        RandomInterval maleNameInterval = new RandomInterval(0, maleNamesList.size() - 1);
        RandomInterval femaleNameInterval = new RandomInterval(0, femaleNamesList.size() - 1);
        RandomInterval surnameInterval = new RandomInterval(0, surnamesList.size() - 1);
        RandomInterval sexInterval = new RandomInterval(0, Sex.values().length - 1);
        RandomInterval educationInterval = new RandomInterval(0, Education.values().length - 1);
        RandomInterval ageInterval = new RandomInterval(0, 100);

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
                randomName,
                randomSurname,
                randomAge,
                randomSex,
                randomEducation
        );
    }
}
