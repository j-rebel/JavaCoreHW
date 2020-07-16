package hw2;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // параметризируем класс типом String для ключа и значения
        BiggerBox<String, String> sample1 = new BiggerBox<>("имя", "Нетология");
        System.out.println(sample1);
        // параметризируем класс типом Integer для ключа и Boolean для значения
        BiggerBox<Integer, Boolean> sample2 = new BiggerBox<>(1, Boolean.TRUE);
        System.out.println("Начальный вывод:");
        System.out.println(sample2);

        /* Такой список вызовет ошибку, так как ни один из экземпляров BiggerBox не обладает соответствующей
        параметризацией
        List<BiggerBox<String, Integer>> errorList = new ArrayList<>();
        errorList.add(sample1);
        errorList.add(sample2);
         */

        /*Решение 1: использовать сырым */

        List<BiggerBox> correctList1 = new ArrayList<>();
        correctList1.add(sample1);
        correctList1.add(sample2);
        System.out.println("Сырой вывод:");
        System.out.println(correctList1.get(0));
        System.out.println(correctList1.get(1));

        /*Решение 2: использовать только с подходящими по параметрам списками */

        List<BiggerBox<String, Integer>> correctList2 = new ArrayList<>();
        BiggerBox<String, Integer> sample3 = new BiggerBox<>("строка", 1);
        correctList2.add(sample3);
        System.out.println("Вывод из подхдящего по параметрам списка:");
        System.out.println(correctList2.get(0));


    }

}
