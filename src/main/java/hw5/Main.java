package hw5;

import hw5.listeners.OnErrorListener;
import hw5.listeners.OnTaskDoneListener;

public class Main {

    public static void main(String[] args) {

        OnTaskDoneListener doneListener = System.out::println;
        OnErrorListener errorListener = System.out::println;

        Worker worker = new Worker(doneListener, errorListener);
        worker.start();
    }

}
