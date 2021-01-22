package com.tp.rpg;

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
    private static void battle(Character a, Character b) {
        Character attacker = a;
        Character defender = b;

        while( a.isAlive() && b.isAlive() ){
            Choice attackerChoice = attacker.makeChoice();
            if( attackerChoice.equals(Choice.ATTACK)) {
                attacker.attack(defender);
            } else if (attackerChoice.equals(Choice.CHANGEEQUIPMENT)){
                attacker.chooseArmor();
                attacker.chooseWeapon();
            }

            Character temp = attacker;
            attacker = defender;
            defender = temp;
        }
    }

    //display some message
    private static void gameOverScreen() {
        throw new UnsupportedOperationException();
    }
}
