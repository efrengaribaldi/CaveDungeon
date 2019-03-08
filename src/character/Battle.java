package src.character;

import java.util.Scanner;

import src.character.player.*;
import src.character.npc.*;
import src.character.npc.enemy.Enemy;
import src.item.*;
import src.item.weapon.Weapon;

public class Battle {

    public static void startBattle(Player player, NPC Npc) {
        Scanner scanner = new Scanner(System.in);
        int indexAttack;
        System.out.println("Your weapon equipped is: ");
        System.out.println(player.getInventory().showEquippedWeapon());

        while(player.getHealthPoints() > 0 && Npc.getHealthPoints() > 0) {
            System.out.println("Select your ability to attack: ");
            System.out.println(player.printPlayerAbilities());
            indexAttack = scanner.nextInt();
            player.attack(Npc, indexAttack);
            System.out.println();

            System.out.println("_______STATS_________");
            System.out.println("Player HP: " + player.getHealthPoints());
            System.out.println("Enemy HP: " + Npc.getHealthPoints());
        }
        if (player.getHealthPoints() > 0)
            System.out.println("You won!");
        else
            System.out.println("GAME OVER");
    }
}
