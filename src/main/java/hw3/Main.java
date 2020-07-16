package hw3;

import hw3.boxes.FruitBox;
import hw3.boxes.VeggieBox;
import hw3.fruits.Apple;
import hw3.fruits.Banana;
import hw3.fruits.Fruit;
import hw3.vegies.Cabbage;
import hw3.vegies.Veggie;

public class Main {

    public static void main(String[] args) {
        FruitBox<String, Banana> bananaBox = new FruitBox<>("banana", new Banana());
        bananaBox.getValue().printClass();

        FruitBox<String, Apple> appleBox = new FruitBox<>("apple", new Apple());
        appleBox.getValue().printClass();

        FruitBox<String, Fruit> fruitBox = new FruitBox<>("fruit", new Fruit());
        fruitBox.getValue().printClass();

        /*
        Выдаст ошибку так как Cabbage не является потомком Fruit, а значит не удовлетворяет ограничению параметризации

        Box<String, Cabbage> cabbageBox = new Box<>("cabbage", new Cabbage());
        cabbageBox.getValue().printClass();
        */

        VeggieBox<String, Veggie> veggieBoxBox = new VeggieBox<>("veggie", new Veggie());
        veggieBoxBox.getValue().printClass();

        VeggieBox<String, Veggie> cabbageBox = new VeggieBox<>("cabbage", new Cabbage());
        cabbageBox.getValue().printClass();

    }

}
