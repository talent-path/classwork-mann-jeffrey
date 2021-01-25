package com.tp.rpg;

import com.tp.rpg.items.Item;
import com.tp.rpg.items.armors.ChainMail;
import com.tp.rpg.items.armors.Shirt;
import com.tp.rpg.items.weapons.Sword;

public abstract class NonPlayerCharacter extends Character {
    private Item[] lootItems = new Item[]{new Shirt(), new Sword(), new ChainMail()};
    public Item lootDrop() {
        Item drop = lootItems[RNG.randInt(0, lootItems.length-1)];
        Console.print(String.format("%s dropped a new %s\n", this.name, drop.getName()));
        return drop;
    }
}
