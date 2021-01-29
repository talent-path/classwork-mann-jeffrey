package com.tp.hangman.services;

import java.util.Random;

public class RNG {
    static Random rng = new Random();

    public static int rollDice(int sideOfDice) {
        return rng.nextInt(sideOfDice+1);
    }

    public static void reseed(long seed) {
        rng = new Random(seed);
    }
}
