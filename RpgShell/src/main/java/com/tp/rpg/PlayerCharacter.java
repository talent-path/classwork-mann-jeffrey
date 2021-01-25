package com.tp.rpg;

import com.tp.rpg.items.Item;
import com.tp.rpg.items.armors.Armor;
import com.tp.rpg.items.weapons.Weapon;
import com.tp.rpg.items.armors.Shirt;
import com.tp.rpg.items.weapons.Fist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerCharacter extends RPGCharacter {
    List<Armor> armorInventory;
    List<Weapon> weaponInventory;
    Map<Item, Integer> itemInventory;

    public PlayerCharacter() {
        this.hitPoints = 50;
        this.name = "You";

        this.armorInventory = new ArrayList<>();
        this.armorInventory.add(new Shirt());

        this.weaponInventory = new ArrayList<>();
        this.weaponInventory.add(new Fist());

        this.itemInventory = new HashMap<>();
    }

    public void chooseArmor() {
        Console.print("Choose your armor: \n");
        String inventoryList = "";
        for (int i = 0; i < armorInventory.size(); i++) {
            inventoryList += "\u001B[34m(" + (i + 1) + ")\u001B[0m " + armorInventory.get(i).getName() + "\n";
        }
        int armorChoice = Console.readInt(inventoryList, 1, armorInventory.size());
        equipArmor(armorInventory.get(armorChoice - 1));
        Console.print("You are wearing " + armor.getName() + "\n");
    }

    public void chooseWeapon() {
        Console.print("Choose your weapon: \n");
        String inventoryList = "";
        for (int i = 0; i < weaponInventory.size(); i++) {
            inventoryList += "\u001B[34m(" + (i + 1) + ")\u001B[0m " + weaponInventory.get(i).getName() + "\n";
        }
        int weaponChoice = Console.readInt(inventoryList, 1, weaponInventory.size());
        equipWeapon(weaponInventory.get(weaponChoice - 1));
        Console.print("You are wielding " + weapon.getName() + "\n");
    }

    @Override
    public void chooseItem() {
        if (!itemInventory.keySet().isEmpty()) {
            Console.print("Choose an item: \n");
            String inventoryList = "";
            int iter = 1;
            for (Item item : itemInventory.keySet()) {
                inventoryList += String.format(
                        "\u001B[34m(%d)\u001B[0m %s: %d",
                        iter, item.getName(), itemInventory.get(item));
                iter++;
            }
            inventoryList += "\n";
            int itemChoice = Console.readInt(inventoryList, 1, itemInventory.keySet().size());
            Console.print(String.format("You chose item %d\n", itemChoice));
        } else Console.print("You have no items\n");
    }

    //use scanner here to get something from the user
    public Choice makeChoice() {
        int choice = Console.readInt("Will you attack \u001B[34m(1)\u001B[0m, " +
                "change your equipment \u001B[34m(2)\u001B[0m, " +
                "or use an item \u001B[34m(3)\u001B[0m?", 1, 3);
        switch (choice) {
            case 1:
                return Choice.ATTACK;
            case 2:
                return Choice.CHANGEEQUIPMENT;
            case 3:
                return Choice.USEITEM;
        }
        return null;
    }

    public void pickupItem(Item loot) {
        if (loot instanceof Weapon) {
            this.weaponInventory.add((Weapon) loot);
        } else if (loot instanceof Armor) {
            this.armorInventory.add((Armor) loot);
        } else {
            if (this.itemInventory.containsKey(loot)) {
                this.itemInventory.put(loot, this.itemInventory.get(loot) + 1);
            } else {
                this.itemInventory.put(loot, 1);
            }
        }
    }

    @Override
    public void attack(RPGCharacter defender) {
        int damage = defender.armor.reduceDamage(this.weapon.generateDamage());
        Console.print(String.format("%s attack %s for ", this.name, defender.name, damage));
        Console.printRed(String.format("%d damage!\n", damage));
        defender.takeDamage(damage);
    }

    @Override
    public void takeDamage(int damage) {
        this.hitPoints -= damage;
        Console.print(String.format("%sr hit points are at ", this.name, this.hitPoints));
        Console.printGreen(String.format("%d\n", this.hitPoints));
    }
}
