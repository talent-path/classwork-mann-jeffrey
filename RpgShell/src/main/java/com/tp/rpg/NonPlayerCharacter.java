package com.tp.rpg;

import com.tp.rpg.items.Item;
import com.tp.rpg.items.potions.HealthPotion;

public abstract class NonPlayerCharacter extends RPGCharacter {
    private Item[] lootItems = new Item[]{new HealthPotion()};

    public Item lootDrop() {
        Item drop = lootItems[RNG.randInt(0, lootItems.length - 1)];
        Console.print(String.format("%s dropped a new \u001B[35m%s\u001B[0m\n", this.name, drop.getName()));
        return drop;
    }

    @Override
    public void chooseItem() {
        return;
    }
}
