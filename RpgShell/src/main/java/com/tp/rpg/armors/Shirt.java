package com.tp.rpg.armors;

public class Shirt implements Armor  {

    @Override
    public String getName() {
        return "Shirt";
    }

    @Override
    public int reduceDamage(int startingDamage) {
        return startingDamage;
    }
}
