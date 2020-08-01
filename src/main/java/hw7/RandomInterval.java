package hw7;

import java.util.Random;

public class RandomInterval {
    private int min;
    private int max;
    private int diff;

    public RandomInterval(int min, int max) {
        this.min = min;
        this.max = max;
        this.diff = max - min;
    }

    public int getRandomWithinInterval() {
        Random random = new Random();
        int i = random.nextInt(this.diff + 1);
        return i += this.min;
    }
}
