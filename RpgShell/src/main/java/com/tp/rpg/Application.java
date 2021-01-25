package com.tp.rpg;

import com.tp.rpg.items.Item;

public class Application {
    public static void main(String[] args) {
        PlayerCharacter pc = setUpPlayer();

        while (pc.isAlive()) {
            NonPlayerCharacter enemy = setUpEnemy();
            battle(pc, enemy);
        }
        gameOverScreen();
    }

    //walk the user through setting up their character
    private static PlayerCharacter setUpPlayer() {
        PlayerCharacter player = new PlayerCharacter();
        player.chooseArmor();
        player.chooseWeapon();
        return player;
    }

    //create some NPC object (with armor & weapons?)
    private static NonPlayerCharacter setUpEnemy() {
        Goblin enemy = new Goblin();
        enemy.chooseArmor();
        enemy.chooseWeapon();
        return enemy;
    }

    //a and b battle until one is dead
    private static void battle(RPGCharacter a, RPGCharacter b) {
        RPGCharacter attacker = a;
        RPGCharacter defender = b;

        while( a.isAlive() && b.isAlive() ){
            Choice attackerChoice = attacker.makeChoice();
            if( attackerChoice.equals(Choice.ATTACK)) {
                attacker.attack(defender);
            } else if (attackerChoice.equals(Choice.CHANGEEQUIPMENT)){
                attacker.chooseArmor();
                attacker.chooseWeapon();
            } else if (attackerChoice.equals(Choice.USEITEM)) {
                attacker.chooseItem();
            }

            RPGCharacter temp = attacker;
            attacker = defender;
            defender = temp;
        }
        if (!b.isAlive()) {
            PlayerCharacter player = (PlayerCharacter) a;
            NonPlayerCharacter defeated = (NonPlayerCharacter) b;
            Console.print(String.format("\n%s has been defeated!\n\n", defeated.name));
            Item loot = defeated.lootDrop();
            player.pickupItem(loot);
        }
        else if (!a.isAlive()) Console.print(String.format("\n%s have been defeated!\n\n", a.name));
    }

    //display some message
    private static void gameOverScreen() {
        Console.print("    ");
        Console.printBlack("-");
        Console.printBlue("<<");
        Console.printYellow("{{");
        Console.printRed("((");
        Console.print("000");
        Console.printRed("))");
        Console.printYellow("}}");
        Console.printBlue(">>");
        Console.printBlack("-\n");
        Console.printBlack("-");
        Console.printBlue("<<<");
        Console.printYellow("{{{");
        Console.printRed("(((");
        Console.print("00000");
        Console.printRed(")))");
        Console.printYellow("}}}");
        Console.printBlue(">>>");
        Console.printBlack("-\n");
//        -<<{{((0O0))}}>>-
        Console.printYellow("You have been vanquished\nYour quest has come to an end\n");
        Console.printBlack("-");
        Console.printBlue("<<<");
        Console.printYellow("{{{");
        Console.printRed("(((");
        Console.print("00000");
        Console.printRed(")))");
        Console.printYellow("}}}");
        Console.printBlue(">>>");
        Console.printBlack("-\n");
        Console.print("    ");
        Console.printBlack("-");
        Console.printBlue("<<");
        Console.printYellow("{{");
        Console.printRed("((");
        Console.print("000");
        Console.printRed("))");
        Console.printYellow("}}");
        Console.printBlue(">>");
        Console.printBlack("-\n");
    }
}
