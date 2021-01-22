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
        throw new UnsupportedOperationException();
    }

    //a and b battle until one is dead
    private static void battle(Character a, Character b) {
        Character attacker = a;
        Character defender = b;

        while( a.isAlive() && b.isAlive() ){
            if( a.makeChoice().equals("Attack")) {
                attacker.attack(defender);
            } else {
                //TODO: consider other actions
                throw new UnsupportedOperationException();
            }

            Character temp = a;
            a = b;
            b = temp;

            //TODO: display HP status?
        }
    }

    //display some message
    private static void gameOverScreen() {
        throw new UnsupportedOperationException();
    }
}
