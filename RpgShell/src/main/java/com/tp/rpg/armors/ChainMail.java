package com.tp.rpg.armors;

public class ChainMail implements Armor {

    @Override
    public String getName() {
        return "Chain Mail";
    }

    @Override
    public int reduceDamage(int startingDamage) {
        return 0;
    }
}
