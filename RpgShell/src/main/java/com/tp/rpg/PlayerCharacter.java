package com.tp.rpg;

import com.tp.rpg.armors.Armor;
import com.tp.rpg.armors.ChainMail;
import com.tp.rpg.armors.Shirt;
import com.tp.rpg.weapons.Fist;
import com.tp.rpg.weapons.Weapon;

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

    @Override
    public void chooseArmor() {
        Console.print("Choose your armor: \n");
        String inventoryList = "";
        for (int i = 0; i < armorInventory.size(); i++) {
            inventoryList += armorInventory.get(i).getName() + " ("+ (i+1) + ")\n";
        }
        int armorChoice = Console.readInt(inventoryList, 1, armorInventory.size()+1);
        equipArmor(armorInventory.get(armorChoice-1));
        Console.print("You are wearing " + armor.getName() + "\n");
    }
    
    @Override
    public void chooseWeapon() {
        Console.print("Choose your weapon: \n");
        String inventoryList = "";
        for (int i = 0; i < weaponInventory.size(); i++) {
            inventoryList += weaponInventory.get(i).getName() + " (" + (i+1) + ")\n";
        }
        int weaponChoice = Console.readInt(inventoryList, 1, weaponInventory.size()+1);
        equipWeapon(weaponInventory.get(weaponChoice-1));
        Console.print("You are wielding " + weapon.getName() + "\n");
    }

    //use scanner here to get something from the user
    @Override
    public Choice makeChoice() {
        int choice = Console.readInt("Will you attack (1) or change your equipment (2)?", 1, 2);
        switch (choice) {
            case 1:
                return Choice.ATTACK;
            case 2:
                return Choice.CHANGEEQUIPMENT;
        }
        return null;
    }
}
