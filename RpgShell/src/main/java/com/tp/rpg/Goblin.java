package com.tp.rpg;

import com.tp.rpg.armors.Shirt;
import com.tp.rpg.weapons.Fist;

//goblins always attack?
public class Goblin extends NonPlayerCharacter {
    public Goblin() {
        this.hitPoints = 30;
        this.name = "Gormlock";
        Console.print("An unarmed Goblin appears, ready to fight!\n");
    }

    @Override
    public Choice makeChoice() {
        return Choice.ATTACK;
    }

    @Override
    public void chooseArmor() {
        equipArmor(new Shirt());
    }

    @Override
    public void chooseWeapon() {
        equipWeapon(new Fist());
    }
}
