package com.tp.rpg.items.armors;

import com.tp.rpg.items.Item;

public interface Armor extends Item {
    //takes in amt of damage dealt
    //outputs amount of damage to actually take
    int reduceDamage(int startingDamage);
}
