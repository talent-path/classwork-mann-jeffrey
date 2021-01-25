package com.tp.rpg.items.potions;

import com.tp.rpg.Console;
import com.tp.rpg.RPGCharacter;

public class HealthPotion implements Potion {

    @Override
    public String getName() {
        return "Health Potion";
    }

    @Override
    public void potionEffect(RPGCharacter user) {
        user.hitPoints = user.hitPoints + 20;
        Console.print(String.format("%s was healed for 20 points", user.name));
    }
}
