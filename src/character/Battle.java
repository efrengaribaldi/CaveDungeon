package src.character;

import src.character.npc.*;
import src.item.Weapon;
import src.item.weapon.Ability;

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
        boolean hasDoneSomething = false;
        do {
            System.out.println("\nWhat do you want to do?");
            System.out.println("Attack(1) | Use potion(2) | Change weapon(3)");
            switch (sc.nextInt()) {
            case 1:
                hasDoneSomething = attackSystem();
                break;
            case 2:
                hasDoneSomething = potionSystem();
                break;
            case 3:
                player.selectWeapon();
                enemyAttack();
                hasDoneSomething = true;
                break;
            default:
                break;
            }
        } while (!hasDoneSomething);
        upgradeStamina();

    }

    private void dropItemsSystem() {
        Weapon weaponDropped;
        int index;
        // Drop item ? (50%)
        if (ThreadLocalRandom.current().nextInt(1, 3) == 1) {
            weaponDropped = enemy.dropWeapon(player);
            System.out.println("\n| Enemy has dropped a weapon! |");
            System.out.println("Weapon Name: " + weaponDropped.getName() + "\n" + weaponDropped.printAbilities());
            System.out.println("Do you want to get this weapon (Y / N)?");
            if (sc.next().charAt(0) == 'Y' || sc.next().charAt(0) == 'y') {
                System.out.println("Which position do you want to save (0 or 1)?");
                index = sc.nextInt();
                player.getNewWeapon(weaponDropped, index);
            }
        }
    }

    private boolean attackSystem() {
        System.out.println("How do you want to attack?");
        System.out.println(player.printPlayerAbilities());
        System.out.println("Select your weapon: ");
        int weaponIndex = sc.nextInt();
        if (weaponIndex != 0 && weaponIndex != 1) {
            System.out.println("Weapon not found");
            return false;
        }
        System.out.println("Select your ability: ");
        int abilityIndex = sc.nextInt();
        Ability ability;
        try {
            ability = player.getInventory().getWeapon(weaponIndex).getAbility(abilityIndex);
        } catch (ArrayIndexOutOfBoundsException exception) {
            System.out.println("Ability not found");
            return false;
        }
        if (player.getStamina() < ability.getStaminaCost()) {
            System.out.println("You don't have enough stamina to use this ability");
            return false;
        } else {
            int totalAttack = player.attack(enemy, weaponIndex, abilityIndex);
            System.out.println("You did " + totalAttack + " damage!");
            enemyAttack();
            return true;
        }
    }


    private void enemyAttack() {
        if (enemy.getHealthPoints() <= 0)
            return;
        System.out.println("v^v^v^v^v^v^v^v^-ENEMY TURN-v^v^v^v^v^v^v^v^\n");
        int totalAttack = enemy.attack(player);
        System.out.println("The " + enemy.getName() + " attacks you and does " + totalAttack + " damage!");

    }

    private boolean potionSystem() {
        System.out.println(player.getInventory().printPotions());
        System.out.println("Which potion do you want to use?");

        int index = sc.nextInt();
        try {
            if (player.getInventory().getPotion(index) != null) {
                player.usePotion(index);
                return true;
            } else {
                System.out.println("You don't have anything in this position\n");
                return false;
            }
        } catch (ArrayIndexOutOfBoundsException exception) {
            System.out.println("Potion not found");
            return false;

        }
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