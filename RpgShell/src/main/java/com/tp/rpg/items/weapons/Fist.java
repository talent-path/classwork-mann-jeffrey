package com.tp.rpg.items.weapons;

import com.tp.rpg.RNG;

public class Fist implements Weapon {

    @Override
    public String getName() {
        return "Fist";
    }

    @Override
    public int generateDamage() {
        return (int)Math.floor(RNG.randGaussian(2.0,0.5));
    }
}
