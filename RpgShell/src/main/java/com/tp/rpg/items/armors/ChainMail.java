package com.tp.rpg.items.armors;

import com.tp.rpg.Console;
import com.tp.rpg.RNG;

public class ChainMail implements Armor {

    @Override
    public String getName() {
        return "Chain Mail";
    }

    @Override
    public int reduceDamage(int startingDamage) {
        int buff = (int) Math.floor(RNG.randGaussian(0.5, 0.5));
        int actualDamage = startingDamage * buff;
        Console.print(String.format("Your chain mail protected you for %d damage\n", startingDamage - actualDamage));
        return actualDamage;
    }
}
