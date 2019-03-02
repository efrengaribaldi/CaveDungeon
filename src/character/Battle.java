import java.util.Scanner;

import src.character.player.*;
import src.character.npc.*;
import src.item.*;
import src.item.weapon.Weapon;

public class Battle {

    public static void startBattle(Player player, NPC NPC) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Your weapon equipped is: ");
        System.out.println(player.getInventory().showEquippedWeapon());

        while(player.getHealthPoints() > 0 || NPC.getHealthPoints() > 0) {
            System.out.println("Select your ability to attack: ");
            System.out.println(player.getInventory().getEquippedWeapon().printAbilities());
            switch (scanner.nextInt()) {
                case 0:
                    NPC.setHealthPoints(NPC.getHealthPoints() - player.getInventory().getEquippedWeapon().getAbility(0).getBaseDamage());
                    break;
                case 1:
                    NPC.setHealthPoints(NPC.getHealthPoints() - player.getInventory().getEquippedWeapon().getAbility(1).getBaseDamage());
                    break;
                case 2:
                    NPC.setHealthPoints(NPC.getHealthPoints() - player.getInventory().getEquippedWeapon().getAbility(2).getBaseDamage());
                    break;
                default:
                    System.out.println("Ability not found");
                    break;
            }
            System.out.println();
        }

    }
}
