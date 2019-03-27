package old.character;

import old.character.npc.*;
import old.character.npc.enemy.*;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Battle {
    // Battle system between player and normal enemy
    public static void startBattle(Player player, Enemy enemy) {
        Scanner scanner = new Scanner(System.in);
        int indexAttack;
        int previousHealth;
        int previousExperience = player.getExperience();
        System.out.println("Your weapon equipped is: ");
        System.out.println(player.getInventory().showEquippedWeapon());

        do {
            previousHealth = player.getHealthPoints();
            System.out.println("_______STATS_________");
            System.out.println("Player HP: " + player.getHealthPoints());
            System.out.println("Enemy HP: " + enemy.getHealthPoints());
            System.out.println("Select your ability to attack: ");
            System.out.println(player.printPlayerAbilities());
            indexAttack = scanner.nextInt();
            player.attack(enemy, indexAttack);
            if (enemy.getHealthPoints() > 0) {
                System.out.println("______ENEMY TURN______");
                enemy.attack(player);
                System.out.println("Enemy attack: " + (previousHealth - player.getHealthPoints()));
            }
            System.out.println();
        } while (player.getHealthPoints() > 0 && enemy.getHealthPoints() > 0);
        if (player.getHealthPoints() > 0) {
            // Add drop items
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
                player.setHealthPoints(35);
                player.setLimitHp(35);
                System.out.println("Congratulations! You are now level 2");
                System.out.println("Your attack damage has increased by 25%");
                System.out.println("Your limit HP has increased to: 35 HP");
            } else if (player.getExperience() >= 150 && player.getLevel() < 3) {
                player.setLevel(3);
                player.setHealthPoints(70);
                player.setLimitHp(70);
                System.out.println("Congratulations! You are now level 3");
                System.out.println("Your attack damage has increased by 75%");
                System.out.println("Your limit HP has increased to: 70 HP");
            } else if (player.getExperience() >= 300 && player.getLevel() < 4) {
                player.setLevel(4);
                player.setHealthPoints(100);
                player.setLimitHp(100);
                System.out.println("Congratulations! You are now level 4");
                System.out.println("Your attack damage has increased by 150%");
                System.out.println("Your limit HP has increased to: 100 HP");
            }
        } else {
            System.out.println("GAME OVER");
            System.exit(0);
        }
    }

    // Battle system between player and boss

}
