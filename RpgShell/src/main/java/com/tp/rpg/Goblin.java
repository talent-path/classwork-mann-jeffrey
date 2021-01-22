package com.tp.rpg;

//goblins always attack?
public class Goblin extends NonPlayerCharacter {
    @Override
    public String makeChoice() {
        return "attack";
    }

    @Override
    public void chooseArmor() {

    }

    @Override
    public void chooseWeapon() {

    }
}
