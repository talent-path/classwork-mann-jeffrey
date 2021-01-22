package com.tp.rpg.armors;

public interface Armor {
    //takes in amt of damage dealt
    //outputs amount of damage to actually take
    String getName();
    int reduceDamage(int startingDamage);
}
