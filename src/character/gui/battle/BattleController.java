package src.character.gui.battle;

import src.character.Player;
import src.character.NPC;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.control.ProgressBar;

public class BattleController {
    private Player player;
    private NPC npc;
    private int totalAttack;
    private int initNpcHealth;

    @FXML
    private Pane wrapperPane;
    @FXML
    private Pane selectOption, selectAttack, battleView, selectPotion, playerAttack, enemyAttack;
    @FXML
    private GridPane abilityGrid;
    @FXML
    private ProgressBar healthPBar, healthEBar;
    @FXML 
    private Label playerName, playerLevel, playerStats, enemyName, enemyHealth;
    @FXML
    private Label weaponEquipped, attackMessage;
    @FXML
    private Button basicAttack, abilityOne, abilityTwo, abilityThree;
    @FXML
    private ImageView imgPlayer, imgNpc, imgWeapon;

    public BattleController() {
        FXMLLoader[] loader = new FXMLLoader[4];
        for (int i = 0; i < loader.length; ++i) {
            loader[i] = new FXMLLoader();
            loader[i].setBuilderFactory(new JavaFXBuilderFactory());
            loader[i].setController(this);
        }
        try {
            selectAttack = (Pane) loader[0].load(loadFXML("./selectAttack.fxml"));
            selectPotion = (Pane) loader[1].load(loadFXML("./selectPotion.fxml"));
            playerAttack = (Pane) loader[2].load(loadFXML("./playerAttack.fxml"));
            enemyAttack = (Pane) loader[3].load(loadFXML("./enemyAttack.fxml"));
        } catch (Exception e) {
            System.out.println("ERROR: Battle Controller couldn't start");
        }
    }

    public FileInputStream loadFXML(String path) throws URISyntaxException, FileNotFoundException {
        URI fxmlDocPath = getClass().getResource(path).toURI();
        FileInputStream fxmlStream = new FileInputStream(new File(fxmlDocPath));
        return fxmlStream;
    }

    @FXML
    public void initialize(Player player, NPC npc) {
        this.player = player;
        this.npc = npc;
        initNpcHealth = npc.getHealthPoints();
        renderBattleView();
        renderSelectAttack();
    }

    @FXML
    private void attack(ActionEvent event) {
        changePane(selectOption, selectAttack);
    }

    @FXML
    private void usePotion(ActionEvent event) {

    }

    @FXML
    private void equipWeapon(ActionEvent event) {

    }

    @FXML
    private void useBasicAttack(ActionEvent event) {
        totalAttack = player.attack(npc);
        changePane(selectAttack, playerAttack);
    }

    @FXML
    private void useAbilityOne(ActionEvent event) {
        attackSystem(0);
    }

    @FXML
    private void useAbilityTwo(ActionEvent event) {
        attackSystem(1);
    }

    @FXML
    private void useAbilityThree(ActionEvent event) {
        attackSystem(2);
    }

    private void attackSystem(int index) {
        if (player.getStamina() < player.getInventory().getEquippedWeapon().getAbility(index).getStaminaCost())
            attackMessage.setText("You don't have enought stamina");
        else {
            totalAttack = player.attack(npc, index);
            changePane(selectAttack, playerAttack);
        }
    }

    private void renderSelectAttack() {
        String[] abilityName = new String[3];
        int[] damage = new int[3];
        int[] stamina = new int[3];
        for (int i = 0; i < damage.length; ++i) {
            abilityName[i] = player.getInventory().getEquippedWeapon().getAbility(i).getName();
            damage[i] = (int)(player.getInventory().getEquippedWeapon().getAbility(i).getBaseDamage()
                    * player.getAttack());
            stamina[i] = (int)(player.getInventory().getEquippedWeapon().getAbility(i).getStaminaCost());
        }
        basicAttack.setText("Basic Attack\nDamage: " + (int)(player.getAttack()) + "  Stamina: 0");
        abilityOne.setText(abilityName[0] + "\nDamage: " + damage[0] + "  Stamina: " + stamina[0]);
        abilityTwo.setText(abilityName[1] + "\nDamage: " + damage[1] + "  Stamina: " + stamina[1]);
        abilityThree.setText(abilityName[2] + "\nDamage: " + damage[2] + "  Stamina: " + stamina[2]);
        imgWeapon.setImage(player.getInventory().getEquippedWeapon().render());
    }

    public void renderBattleView() {
        playerName.setText(player.getName());
        playerLevel.setText("Level: " + Integer.toString(player.getLevel()));
        enemyName.setText(npc.getName());
        imgPlayer = player.render();
        imgNpc = npc.render();
        upgradeStats();
    }

    private void upgradeStats() {
        playerStats.setText("HP: " + Integer.toString(player.getHealthPoints()) + "/" + Integer.toString(player.getLimitHp())
                + "  Stamina: " + Integer.toString(player.getStamina()) + "/" + Integer.toString(player.getLimitStamina()));
        enemyHealth.setText("HP: " + Integer.toString(npc.getHealthPoints()) + "/" + Integer.toString(initNpcHealth));
        healthPBar.setProgress((double) player.getHealthPoints() / player.getLimitHp());
        healthEBar.setProgress((double) npc.getHealthPoints() / initNpcHealth);
    }

    private void changePane(Pane paneRemoved, Pane paneAdded) {
        wrapperPane.getChildren().remove(paneRemoved);
        wrapperPane.getChildren().add(paneAdded);
    }
}
