package com.tp.rpg.weapons;

public class Fist implements Weapon {

    @Override
    public String getName() {
        return "Fist";
    }

    @Override
    public int generateDamage() {
        throw new UnsupportedOperationException();
    }
}
