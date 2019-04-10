package src.character;

import src.character.npc.*;
import src.item.Weapon;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Battle {
    private Scanner sc;
    private Player player;
    private Enemy enemy;

    public Battle(Player player, Enemy enemy) {
        sc = new Scanner(System.in);
        this.player = player;
        this.enemy = enemy;
        System.out.println("-=x=-=x=-=x=-=x=-=x=-=x=-=x=-=BATTLE START=-=x=-=x=-=x=-=x=-=x=-=x=-=x=-");
        System.out.println("Which weapon do you want to equip?");
        System.out.println(player.getInventory().printWeapons());
        player.getInventory().equipWeapon(sc.nextInt());
        do {
            System.out.println("v^v^v^v^v^v^v^v^-YOUR TURN-v^v^v^v^v^v^v^v^\n");
            System.out.println("--STATS--");
            System.out.println("Player HP: " + player.getHealthPoints() + " | Player Stamina: " + player.getStamina());
            System.out.println("Enemy  HP: " + enemy.getHealthPoints());
            System.out.println("----------");
            selectionSystem();
        } while (player.getHealthPoints() > 0 && enemy.getHealthPoints() > 0);
        if (player.getHealthPoints() > 0) {
            // Add drop items
            upgradeStats();
            dropItemsSystem();
        } else {
            System.out.println("GAME OVER");
            System.exit(0);
        }
    }

    private void selectionSystem() {
        System.out.println("\nWhat do you want to do?");
        System.out.println("Attack(1) | Use potion(2) | Pass turn(3)");
        switch (sc.nextInt()) {
        case 1:
            attackSystem();
            break;
        case 2:
            potionSystem();
            break;
        case 3:
            enemyAttack();
            break;
        default:
            break;
        }
        upgradeStamina();
    }

    private void dropItemsSystem() {
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

    private void attackSystem() {
        int indexAttack;
        boolean hasAttacked = false;
        do {
            System.out.println("Select your ability to attack: ");
            System.out.println(player.printPlayerAbilities());
            indexAttack = sc.nextInt();
            try {
                if (player.getStamina() < player.getInventory().getEquippedWeapon().getAbility(indexAttack)
                        .getStaminaCost()) {
                    System.out.println("You don't have enough stamina to use this ability");
                } else {
                    player.attack(enemy, indexAttack);
                    hasAttacked = true;
                    enemyAttack();
                }
            } catch (ArrayIndexOutOfBoundsException exception) {
                System.out.println("Ability not found");
            }
        } while (!hasAttacked);
    }

    private void enemyAttack() {
        if (enemy.getHealthPoints() <= 0)
            return;
        System.out.println("v^v^v^v^v^v^v^v^-ENEMY TURN-v^v^v^v^v^v^v^v^\n");
        int totalAttack = enemy.attack(player);
        System.out.println("The " + enemy.getName() + " attacks you and does " + totalAttack + " damage!");
    }

    private void potionSystem() {
        boolean hasUsedPotion = false;
        do {
            System.out.println(player.getInventory().printPotions());
            System.out.println("Which potion do you want to use?");
            int index = sc.nextInt();
            if (player.getInventory().getPotion(index) != null) {
                player.usePotion(index);
                hasUsedPotion = true;
            } else {
                System.out.println("You don't have anything in this position\n");
            }
        } while (!hasUsedPotion);
    }

    private void upgradeStamina() {
        player.setStamina(player.getStamina() + 5);
        if (player.getStamina() > player.getLimitStamina())
            player.setStamina(player.getLimitStamina());
        System.out.println();
    }

    private void upgradeStats() {
        System.out.println("<*><*><*>-You won!-<*><*><*>");
        player.checkLevelUp(enemy.getExperience());
    }
    // Battle system between player and boss
}