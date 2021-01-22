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

import com.tp.rpg.armors.Armor;
import com.tp.rpg.armors.Shirt;
import com.tp.rpg.weapons.Weapon;

import java.util.List;

public abstract class Character implements Chooser {
    //TODO: add fields for armor(s) and weapon(s)

    int hitPoints;
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

    public void attack(Character defender) {
        throw new UnsupportedOperationException();
    }

    public void takeDamage(int damage) {
        throw new UnsupportedOperationException();
    }

    public boolean isAlive() {
        throw new UnsupportedOperationException();
    }

}
