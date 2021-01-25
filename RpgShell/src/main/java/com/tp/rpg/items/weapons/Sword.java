package com.tp.rpg.items.weapons;

import com.tp.rpg.RNG;

public class Sword implements Weapon {
    @Override
    public String getName() {
        return "Sword";
    }

    @Override
    public int generateDamage() {
        return (int) Math.floor(RNG.randGaussian(5.0, 1.0));
    }
}
