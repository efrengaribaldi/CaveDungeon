package src.character;

import src.character.npc.*;
import src.character.npc.enemy.*;
import src.item.Potion;
import src.item.potion.*;

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
        System.out.println("-=x=-=x=-=x=-=x=-=x=-=x=-=x=-=BATTLE START=-=x=-=x=-=x=-=x=-=x=-=x=-=x=-");
        previousExperience = player.getExperience();
        System.out.println("Your weapon equipped is: ");
        System.out.println(player.getInventory().showEquippedWeapon());
        System.out.println();

        do {

            System.out.println("v^v^v^v^v^v^v^v^-YOUR TURN-v^v^v^v^v^v^v^v^");
            System.out.println();
            previousHealth = player.getHealthPoints();
            System.out.println("--STATS--");
            System.out.println("Player HP: " + player.getHealthPoints() + " | Player Stamina: " + player.getStamina());
            System.out.println();
            System.out.println("Enemy  HP: " + enemy.getHealthPoints());
            System.out.println("----------");
            selectionSystem(player, enemy);

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
        switch (selection) {
        case 1:
            attackSystem(player, enemy);
            upgradeStamina(player);
            break;
        case 2:
            potionSystem(player, enemy);
            upgradeStamina(player);
            break;
        case 3:
            enemyAttack(player, enemy);
            upgradeStamina(player);
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
        try {
            if (player.getStamina() < player.getInventory().getEquippedWeapon().getAbility(indexAttack)
                    .getStaminaCost()) {
                System.out.println("You don't have enough stamina to use this ability");
                selectionSystem(player, enemy);
            } else {
                player.attack(enemy, indexAttack);
                enemyAttack(player, enemy);
            }
        } catch (ArrayIndexOutOfBoundsException exception) {
            System.out.println("Ability not found");
            selectionSystem(player, enemy);
        }
    }

    static void enemyAttack(Player player, Enemy enemy) {
        if (enemy.getHealthPoints() > 0) {
            System.out.println("v^v^v^v^v^v^v^v^-ENEMY TURN-v^v^v^v^v^v^v^v^");
            System.out.println();
            enemy.attack(player);
            System.out.println("The " + enemy.getName() + " attacks you and does "
                    + (previousHealth - player.getHealthPoints()) + " damage!");
        }
    }

    static void potionSystem(Player player, Enemy enemy) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(player.getInventory().printPotions());
        System.out.println("Which potion do you want to use?");
        indexPotion = scanner.nextInt();
        if (player.getInventory().getPotionIndex(indexPotion) != null) {
            if (player.getInventory().getPotionIndex(indexPotion) instanceof HealthPotion) {
                if (player.getHealthPoints()
                        + player.getInventory().getPotionIndex(indexPotion).getRecoveryPoints() > player.getLimitHp())
                    player.setHealthPoints(player.getLimitHp());
                else
                    player.setHealthPoints(player.getHealthPoints()
                            + player.getInventory().getPotionIndex(indexPotion).getRecoveryPoints());
            } else if (player.getInventory().getPotionIndex(indexPotion) instanceof StaminaPotion) {
                if (player.getStamina() + player.getInventory().getPotionIndex(indexPotion).getRecoveryPoints() > player
                        .getLimitStamina())
                    player.setStamina(player.getLimitStamina());
                else
                    player.setStamina(player.getStamina()
                            + player.getInventory().getPotionIndex(indexPotion).getRecoveryPoints());
            }
            player.getInventory().removePotionIndex(indexPotion);
        } else {
            System.out.println("You don't have anything in this position");
            selectionSystem(player, enemy);
        }

    }

    static void upgradeStamina(Player player) {
        if (player.getStamina() + 5 > player.getLimitStamina())
            player.setStamina(player.getLimitStamina());
        else
            player.setStamina(player.getStamina() + 5);
        System.out.println();
    }

    static void upgradeStats(Player player, Enemy enemy) {
        player.setExperience(player.getExperience() + enemy.getExperience());
        System.out.println("<*><*><*>-You won!-<*><*><*>");
        System.out.println("Your EXP: " + player.getExperience() + "| Your Level:" + player.getLevel());
        player.checkLevelUp();

    }
    // Battle system between player and boss

}
