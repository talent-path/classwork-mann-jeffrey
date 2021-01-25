package com.tp.rpg;

// TODO:
//      * add concept of HP
//      * add concept of armor
//      * add concept of weapons
// Stretch Goals:
//      add a potion class/interface that the character can drink instead of attacking
//      let the character "disarm" the opponent instead of attacking
//          base this on the weapons used?
//      let the character choose to "block" or "defend"
//          could stun the opponent if they attack?
//          could give us TWO attacks on the next round?

import com.tp.rpg.items.armors.Armor;
import com.tp.rpg.items.weapons.Weapon;

public abstract class RPGCharacter implements Chooser {
    //TODO: add fields for armor(s) and weapon(s)

    public String name;
    public int hitPoints;
    Armor armor;
    Weapon weapon;

    public abstract void chooseArmor();

    public void equipArmor(Armor choice) {
        armor = choice;
    }

    public abstract void chooseWeapon();

    public void equipWeapon(Weapon choice) {
        weapon = choice;
    }

    public abstract void chooseItem();

    public void attack(RPGCharacter defender) {
        int damage = defender.armor.reduceDamage(this.weapon.generateDamage());
        Console.print(String.format("%s attacks %s for ", this.name, defender.name));
        Console.printRed(String.format("%d damage!\n", damage));
        defender.takeDamage(damage);
    }

    public void takeDamage(int damage) {
        this.hitPoints -= damage;
        Console.print(String.format("%s's hit points are at ", this.name));
        Console.printGreen(String.format("%d\n", this.hitPoints));
    }

    public boolean isAlive() {
        return hitPoints > 0;
    }
}
