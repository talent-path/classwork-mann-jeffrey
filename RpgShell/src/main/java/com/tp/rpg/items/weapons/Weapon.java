package com.tp.rpg.items.weapons;

import com.tp.rpg.items.Item;

public interface Weapon extends Item {
    //generate some amount of damage to be dealt
    int generateDamage();
}
