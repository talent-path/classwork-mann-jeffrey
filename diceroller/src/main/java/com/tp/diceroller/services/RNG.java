package com.tp.diceroller.services;

import java.util.Random;

public class RNG {
    static Random rng = new Random();

    public static int rollDice(int sideOfDice) {
        return rng.nextInt(sideOfDice)+1;
    }
}
