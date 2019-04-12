package src.item.gui;

import src.character.Player;
import src.item.Inventory;
import src.game.Game;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.scene.image.ImageView;

public class InventoryController {
    private Game game;
    private Player player;
    private Inventory inventory;

    @FXML
    private Label playerName, playerLevel, playerType, playerGender;
    @FXML
    private Label itemName, itemText;
    @FXML
    private ImageView imgWeaponOne, imgWeaponTwo;
    @FXML
    private ImageView imgArmorOne, imgArmorTwo, imgArmorThree;
    @FXML
    private ImageView imgHPotionOne, imgHPotionTwo, imgSPotionOne, imgSPotionTwo;

    @FXML
    private URL location;
    @FXML
    private ResourceBundle resources;

    public InventoryController() {
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    private void selectWeaponOne(ActionEvent event) {
        itemName.setText(player.getInventory().getWeapon(0).getName());
        itemText.setText(player.getInventory().getWeapon(0).printAbilities());
    }

    @FXML
    private void selectWeaponTwo(ActionEvent event) {
        itemName.setText(player.getInventory().getWeapon(1).getName());
        itemText.setText(player.getInventory().getWeapon(1).printAbilities());
    }

    @FXML
    private void selectArmorOne(ActionEvent event) {
        if (player.getInventory().getArmor(0) != null) {
            itemName.setText(player.getInventory().getArmor(0).getName());
            itemText.setText("Defense: " + player.getInventory().getArmor(0).getBaseDefense());
        }
    }

    @FXML
    private void selectArmorTwo(ActionEvent event) {
        itemName.setText(player.getInventory().getArmor(1).getName());
        itemText.setText("Defense: " + player.getInventory().getArmor(1).getBaseDefense());
    }

    @FXML
    private void selectArmorThree(ActionEvent event) {
        itemName.setText(player.getInventory().getArmor(2).getName());
        itemText.setText("Defense: " + player.getInventory().getArmor(2).getBaseDefense());
    }

    @FXML
    private void selectHPotionOne(ActionEvent event) {
        itemName.setText(player.getInventory().getPotion(0).getName());
        itemText.setText("Health points: " + player.getInventory().getPotion(0).getRecoveryPoints());
    }

    @FXML
    private void selectHPotionTwo(ActionEvent event) {
        itemName.setText(player.getInventory().getPotion(1).getName());
        itemText.setText("Health points: " + player.getInventory().getPotion(1).getRecoveryPoints());
    }

    @FXML
    private void selectSPotionOne(ActionEvent event) {
        itemName.setText(player.getInventory().getPotion(2).getName());
        itemText.setText("Stamina points: " + player.getInventory().getPotion(2).getRecoveryPoints());
    }

    @FXML
    private void selectSPotionTwo(ActionEvent event) {
        itemName.setText(player.getInventory().getPotion(3).getName());
        itemText.setText("Stamina points: " + player.getInventory().getPotion(3).getRecoveryPoints());
    }

    @FXML
    private void dropWeaponOne(ActionEvent event) {
        player.getInventory().removeWeapon(0);
        imgWeaponOne.setImage(null);
    }

    @FXML
    private void dropWeaponTwo(ActionEvent event) {
        player.getInventory().removeWeapon(1);
        imgWeaponTwo.setImage(null);
    }

    @FXML
    private void dropArmorOne(ActionEvent event) {
        player.getInventory().removeArmor(0);
        imgArmorOne.setImage(null);
    }

    @FXML
    private void dropArmorTwo(ActionEvent event) {
        player.getInventory().removeArmor(1);
        imgArmorTwo.setImage(null);
    }

    @FXML
    private void dropArmorThree(ActionEvent event) {
        player.getInventory().removeArmor(2);
        imgArmorThree.setImage(null);
    }

    @FXML
    private void dropHPotionOne(ActionEvent event) {
        player.getInventory().removePotion(0);
        imgHPotionOne.setImage(null);
    }

    @FXML
    private void dropHPotionTwo(ActionEvent event) {
        player.getInventory().removePotion(1);
        imgHPotionTwo.setImage(null);
    }

    @FXML
    private void dropSPotionOne(ActionEvent event) {
        player.getInventory().removePotion(2);
        imgSPotionOne.setImage(null);
    }

    @FXML
    private void dropSPotionTwo(ActionEvent event) {
        player.getInventory().removePotion(3);
        imgSPotionTwo.setImage(null);
    }

    @FXML
    private void exitInventory(ActionEvent event) throws Exception {
        game.setRoomScene();
    }

    public Label getPlayerName() {
        return playerName;
    }

    public Label getPlayerLevel() {
        return playerLevel;
    }

    public Label getPlayerType() {
        return playerType;
    }

    public Label getPlayerGender() {
        return playerGender;
    }

    public ImageView getImgWeaponOne() {
        return imgWeaponOne;
    }

    public ImageView getImgWeaponTwo() {
        return imgWeaponTwo;
    }

    public ImageView getImgArmorOne() {
        return imgArmorOne;
    }

    public ImageView getImgArmorTwo() {
        return imgArmorTwo;
    }

    public ImageView getImgArmorThree() {
        return imgArmorThree;
    }

    public ImageView getImgHPotionOne() {
        return imgHPotionOne;
    }

    public ImageView getImgHPotionTwo() {
        return imgHPotionTwo;
    }

    public ImageView getImgSPotionOne() {
        return imgSPotionOne;
    }

    public ImageView getImgSPotionTwo() {
        return imgSPotionTwo;
    }

    // To get the img create a indeximg and use getImgName(), for potion null set
    // emptypotion
    public void setGame(Game game) {
        System.out.println("Oh no");
        this.game = game;
        player = this.game.getPlayer();
        inventory = player.getInventory();
        playerName.setText(player.getName());
        playerLevel.setText("Level: " + Integer.toString(player.getLevel()));
        playerType.setText("Type: " + player.getClass().getSimpleName());
        playerGender.setText("Gender: " + Character.toString(player.getGender()).toUpperCase());
        // System.out.println(player.getInventory().getWeapon(0).getImgName());
        // imgWeaponOne.setImage(new
        // Image(getClass().getResource("src/item/weapon/img/arrow1.png").toExternalForm()));
        // getClass().getResource("/images/mpgafor_logo_16x16.png").toExternalForm()
        imgWeaponOne.setImage(inventory.getWeapon(0).render());
        /*
         * imgWeaponTwo.setImage(new Image(getClass().getResource()));
         * imgHPotionOne.setImage(new Image("src/item/potion/img/" +
         * player.getInventory().getPotion(0).getImgName())); imgSPotionOne.setImage(new
         * Image("src/item/potion/img/" +
         * player.getInventory().getPotion(1).getImgName()));
         */
    }
}
