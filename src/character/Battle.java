package src.character;

import src.character.npc.*;
import src.item.potion.*;
import src.item.Weapon;

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
        Scanner sc = new Scanner(System.in);
        System.out.println("-=x=-=x=-=x=-=x=-=x=-=x=-=x=-=BATTLE START=-=x=-=x=-=x=-=x=-=x=-=x=-=x=-");
        previousExperience = player.getExperience();
        System.out.println("Which weapon do you want to equip?");
        System.out.println(player.getInventory().printWeapons());
        player.getInventory().equipWeapon(sc.nextInt());
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
            dropItemsSystem(player, enemy);
        } else {
            System.out.println("GAME OVER");
            System.exit(0);
        }
    }

    static void selectionSystem(Player player, Enemy enemy) {
        Scanner sc = new Scanner(System.in);

        System.out.println("\nWhat do you want to do?\nAttack(1) | Use potion(2) | Pass turn(3)");
        selection = sc.nextInt();
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

    static void dropItemsSystem(Player player, Enemy enemy) {
        Scanner sc = new Scanner(System.in);
        Weapon weaponDropped;
        int index;
        // Drop item ? (50%)
        if (ThreadLocalRandom.current().nextInt(1, 3) == 1) {
            weaponDropped = enemy.dropWeapon(player);
            System.out.println("\n| Enemy has dropped a weapon (!) |");
            System.out.println("Weapon Name: " + weaponDropped.getName() + "\n" + weaponDropped.printAbilities());
            System.out.println("Do you want to get this weapon (Y / N)?");
            if (sc.next().charAt(0) == 'Y') {
                System.out.println("Which position do you want to save (0 or 1)?");
                index = sc.nextInt();
                player.getNewWeapon(weaponDropped, index);
            }
        }
    }

    static void attackSystem(Player player, Enemy enemy) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Select your ability to attack: ");
        System.out.println(player.printPlayerAbilities());
        indexAttack = sc.nextInt();
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
        Scanner sc = new Scanner(System.in);
        System.out.println(player.getInventory().printPotions());
        System.out.println("Which potion do you want to use?");
        indexPotion = sc.nextInt();
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
        System.out.println("<*><*><*>-You won!-<*><*><*>");
        player.checkLevelUp(enemy.getExperience());
    }
    // Battle system between player and boss

}