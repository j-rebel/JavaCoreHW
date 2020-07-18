package hw5;

import hw5.listeners.OnErrorListener;
import hw5.listeners.OnTaskDoneListener;

public class Worker {

    private OnTaskDoneListener callbackDone;
    private OnErrorListener callbackError;

    public Worker(OnTaskDoneListener callbackDone, OnErrorListener callbackError) {
        this.callbackDone = callbackDone;
        this.callbackError = callbackError;
    }

    public void start() {
        for (int i = 0; i < 100; i++) {
            if (i == 33) {
                callbackError.onError("Task " + i + " HAS FAILED");
            } else {
                callbackDone.onDone("Task " + i + " is done");
            }
        }
    }
}
