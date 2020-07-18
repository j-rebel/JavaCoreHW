package hw4;

public class Main {

    public static void main(String[] args) {
        Calculator calc = Calculator.instance.get();

        Integer a = calc.plus.apply(1, 2);
        Integer b = calc.minus.apply(5,1);
        Float c = calc.divide.apply(a.floatValue(), b.floatValue());
        // В примере из задания код работал некорректно, так как деление было типизировано Integer, а значит дробная
        // часть усекалась, возвращая только целый результат.
        // Возможно также получать результаты Nan, -Infinity, Infinity при делении на нуль, но эти случаи не
        // обрабатывал.
        calc.println.accept(c);
    }
}
