package com.tp.rpg;

import com.tp.rpg.items.armors.Armor;
import com.tp.rpg.items.armors.Shirt;
import com.tp.rpg.items.weapons.Fist;
import com.tp.rpg.items.weapons.Sword;
import com.tp.rpg.items.weapons.Weapon;

import java.util.ArrayList;
import java.util.List;

public class PlayerCharacter extends Character {
    List<Armor> armorInventory;
    List<Weapon> weaponInventory;

    public PlayerCharacter() {
        this.hitPoints = 50;
        this.name = "You";

        this.armorInventory = new ArrayList<>();
        this.armorInventory.add(new Shirt());

        this.weaponInventory = new ArrayList<>();
        this.weaponInventory.add(new Fist());
    }

    public void chooseArmor() {
        Console.print("Choose your armor: \n");
        String inventoryList = "";
        for (int i = 0; i < armorInventory.size(); i++) {
            inventoryList += "\u001B[34m("+ (i+1) + ")\u001B[0m " + armorInventory.get(i).getName() + "\n";
        }
        int armorChoice = Console.readInt(inventoryList, 1, armorInventory.size());
        equipArmor(armorInventory.get(armorChoice-1));
        Console.print("You are wearing " + armor.getName() + "\n");
    }
    
    public void chooseWeapon() {
        Console.print("Choose your weapon: \n");
        String inventoryList = "";
        for (int i = 0; i < weaponInventory.size(); i++) {
            inventoryList += "\u001B[34m("+ (i+1) + ")\u001B[0m " + weaponInventory.get(i).getName() + "\n";
        }
        int weaponChoice = Console.readInt(inventoryList, 1, weaponInventory.size());
        equipWeapon(weaponInventory.get(weaponChoice-1));
        Console.print("You are wielding " + weapon.getName() + "\n");
    }

    //use scanner here to get something from the user
    public Choice makeChoice() {
        int choice = Console.readInt("Will you attack \u001B[34m(1)\u001B[0m or change your equipment \u001B[34m(2)\u001B[0m?", 1, 2);
        switch (choice) {
            case 1:
                return Choice.ATTACK;
            case 2:
                return Choice.CHANGEEQUIPMENT;
        }
        return null;
    }

    @Override
    public void attack(Character defender) {
        int damage = this.weapon.generateDamage();
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
