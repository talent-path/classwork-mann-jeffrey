package com.tp.rpg.items.potions;

import com.tp.rpg.RPGCharacter;
import com.tp.rpg.items.Item;

public interface Potion extends Item {
    void potionEffect(RPGCharacter user);
}
