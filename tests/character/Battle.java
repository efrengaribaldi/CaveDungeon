package tests.character;

import tests.character.npc.*;
import tests.character.npc.enemy.*;
import tests.item.Potion;
import tests.item.potion.*;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Battle {
    // Battle system between player and normal enemy
    static int previousHealth;
    static int indexAttack;
    static int indexPotion;
    static int selection;
    static int previousExperience;

    public static void startBattle(Player player, Enemy enemy) {
        Scanner scanner = new Scanner(System.in);

        previousExperience = player.getExperience();
        System.out.println("Your weapon equipped is: ");
        System.out.println(player.getInventory().showEquippedWeapon());

        do {
            previousHealth = player.getHealthPoints();
            System.out.println("_______STATS_________");
            System.out.println("Player HP: " + player.getHealthPoints() + " | Player Stamina: " + player.getStamina());
            System.out.println("Enemy HP: " + enemy.getHealthPoints());
            selectionSystem(player, enemy);
            upgradeStamina(player);
        } while (player.getHealthPoints() > 0 && enemy.getHealthPoints() > 0);
        if (player.getHealthPoints() > 0) {
            // Add drop items
            upgradeStats(player, enemy);
        } else {
            System.out.println("GAME OVER");
            System.exit(0);
        }
    }

    static void selectionSystem(Player player, Enemy enemy) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nWhat do you want to do?\nAttack(1) | Use potion(2) | Pass turn(3)");
        selection = scanner.nextInt();
        switch(selection) {
            case 1:
                attackSystem(player, enemy);
                break;
            case 2:
                potionSystem(player, enemy);
                break;
            case 3:
                enemyAttack(player, enemy);
                break;
            default:
                break;
        }
    }

    static void attackSystem(Player player, Enemy enemy) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select your ability to attack: ");
        System.out.println(player.printPlayerAbilities());
        indexAttack = scanner.nextInt();
        if (player.getStamina() < player.getInventory().getEquippedWeapon().getAbility(indexAttack).getStaminaCost()) {
            System.out.println("You don't have enough stamina to use this ability");
            selectionSystem(player, enemy);
        }
        else {
            player.attack(enemy, indexAttack);
            enemyAttack(player, enemy);
        }
    }

    static void enemyAttack(Player player, Enemy enemy) {
        if (enemy.getHealthPoints() > 0) {
            System.out.println("______ENEMY TURN______");
            enemy.attack(player);
            System.out.println("Enemy attack: " + (previousHealth - player.getHealthPoints()));
        }
    }

    static void potionSystem(Player player, Enemy enemy) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(player.getInventory().printPotions());
        System.out.println("Which potion do you want to use?");
        indexPotion = scanner.nextInt();
        if(player.getInventory().getPotionIndex(indexPotion) != null) {
            if(player.getInventory().getPotionIndex(indexPotion) instanceof HealthPotion) {
                if(player.getHealthPoints() + player.getInventory().getPotionIndex(indexPotion).getRecoveryPoints() > player.getLimitHp())
                    player.setHealthPoints(player.getLimitHp());
                else
                    player.setHealthPoints(player.getHealthPoints() + player.getInventory().getPotionIndex(indexPotion).getRecoveryPoints());
            }
            else if(player.getInventory().getPotionIndex(indexPotion) instanceof StaminaPotion) {
                if(player.getStamina() + player.getInventory().getPotionIndex(indexPotion).getRecoveryPoints() > player.getLimitStamina())
                    player.setStamina(player.getLimitStamina());
                else
                    player.setStamina(player.getStamina() + player.getInventory().getPotionIndex(indexPotion).getRecoveryPoints());
            }
            player.getInventory().removePotionIndex(indexPotion);
        }
        else {
            System.out.println("You don't have anything in this position");
            selectionSystem(player, enemy);
        }

    }

    static void upgradeStamina(Player player) {
        if(player.getStamina() + 5 > player.getLimitStamina())
            player.setStamina(player.getLimitStamina());
        else
            player.setStamina(player.getStamina() + 5);
        System.out.println();
    }

    static void upgradeStats(Player player, Enemy enemy) {
        if (enemy instanceof Zombie) {
            player.setExperience(player.getExperience() + ThreadLocalRandom.current().nextInt(4, 5 + 1));
        } else if (enemy instanceof Skeleton) {
            player.setExperience(player.getExperience() + ThreadLocalRandom.current().nextInt(8, 10 + 1));
        } else if (enemy instanceof Chort) {
            player.setExperience(player.getExperience() + ThreadLocalRandom.current().nextInt(20, 25 + 1));
        } else if (enemy instanceof Swampy) {
            player.setExperience(player.getExperience() + ThreadLocalRandom.current().nextInt(40, 50 + 1));
        } else if (enemy instanceof Necromancer) {
            player.setExperience(player.getExperience() + ThreadLocalRandom.current().nextInt(60, 75 + 1));
        }
        System.out.println("You won!");
        System.out.println("Previous EXP: " + previousExperience + " | New EXP: " + player.getExperience());
        if (player.getExperience() >= 50 && player.getLevel() < 2) {
            player.setLevel(2);
            player.setLimitHp(35);
            player.setHealthPoints(35);
            player.setLimitStamina(15);
            player.setStamina(15);
            System.out.println("Congratulations! You are now level 2");
            System.out.println("Your attack damage has increased by 25%");
            System.out.println("Your HP limit has increased to: 35 HP");
            System.out.println("Your Stamina limit has increased to: 15");
        } else if (player.getExperience() >= 150 && player.getLevel() < 3) {
            player.setLevel(3);
            player.setLimitHp(70);
            player.setHealthPoints(70);
            player.setLimitStamina(30);
            player.setStamina(30);
            System.out.println("Congratulations! You are now level 3");
            System.out.println("Your attack damage has increased by 75%");
            System.out.println("Your HP limit has increased to: 70 HP");
            System.out.println("Your Stamina limit has increased to: 30");
        } else if (player.getExperience() >= 300 && player.getLevel() < 4) {
            player.setLevel(4);
            player.setLimitHp(100);
            player.setHealthPoints(100);
            player.setLimitStamina(50);
            player.setStamina(50);
            System.out.println("Congratulations! You are now level 4");
            System.out.println("Your attack damage has increased by 150%");
            System.out.println("Your HP limit has increased to: 100 HP");
            System.out.println("Your Stamina limit has increased to: 50");
        }
    }
    // Battle system between player and boss

}
