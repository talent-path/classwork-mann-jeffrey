import java.util.Random;

// Singleton pattern - single static instance of something inside a class
//                     which we then use throughout

public class RNG {
    static Random rng = new Random();

    public static int randInt(int inclusiveMinimum, int inclusiveMaximum) {
//        this call (to Random.nextInt()) takes an exclusive upper bound (or max)
//        returns a number between 0 and the upper bound-1
        int output = inclusiveMinimum + rng.nextInt((inclusiveMaximum - inclusiveMinimum) + 1);

        return output;
    }

    public static double randDouble(int inclusiveMinimum, int inclusiveMaximum) {
//        this call (to Random.nextInt()) takes an exclusive upper bound (or max)
//        returns a number between 0 and the upper bound-1
        double output = inclusiveMinimum + rng.nextDouble() * (inclusiveMaximum - inclusiveMinimum);

        return output;
    }

    public static boolean coinFlip() {
        return rng.nextBoolean();
    }

}
