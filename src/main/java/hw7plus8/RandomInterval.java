package hw7plus8;

import java.util.Random;

public class RandomInterval {
    private final int min;
    private final int max;
    private final int diff;

    public RandomInterval(int min, int max) {
        this.min = min;
        this.max = max;
        this.diff = this.max - this.min;
    }

    public int getRandomWithinInterval() {
        Random random = new Random();
        int i = random.nextInt(this.diff + 1);
        i += this.min;
        return i;
    }
}
