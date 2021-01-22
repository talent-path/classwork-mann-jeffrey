package com.tp.rpg;

import java.util.Random;

public class RNG {
    static Random rng = new Random();

    public static int randInt(int incMin, int incMax) {
        return incMin + rng.nextInt(incMax - incMin + 1);
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

    public static double randGaussian(double mean, double standardDeviation) {
        return rng.nextGaussian() * standardDeviation + mean;
    }
}
