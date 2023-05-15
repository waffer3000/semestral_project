package cz.cvut.anokhver.enteties;

import cz.cvut.anokhver.GameLauncher;
import cz.cvut.anokhver.additional.Configuration;
import cz.cvut.anokhver.additional.PlayerConfigutations;
import cz.cvut.anokhver.contollers.InventoryController;
import cz.cvut.anokhver.movement.Coordinates;
import cz.cvut.anokhver.movement.Direction;

import java.util.List;

import static cz.cvut.anokhver.additional.FileManagement.create_proper_path;
import static cz.cvut.anokhver.movement.Coordinates.minus;

public class Player extends Movable{
    private float damage;
    private float health;
    private float speed_damage;
    private double damage_radius;
    private Integer coins = 0;
    private final InventoryController inventory;
    private int star_counter = 0;

    public Player(float damage, float walkSpeed, float health, float speedDamage, double damageRadius, InventoryController inventory) {
        this.damage = damage;
        this.health = health;
        this.speed_damage = speedDamage;
        this.damage_radius = damageRadius;
        this.inventory = inventory;

        this.setWalk_speed(walkSpeed);
        loadAllTextures(Configuration.getPlayerWidth(), Configuration.getPlayerHeight());
        setCurTextureDirection(Direction.STOP);
        setTexture(getTextures().get("anim4"));
    }

    public Player() {
        GameLauncher.log.info("Creating default player...");
        PlayerConfigutations.init(create_proper_path("con_player.json"));
        this.damage = PlayerConfigutations.getDamage();
        this.health = PlayerConfigutations.getHealth();
        speed_damage = PlayerConfigutations.getSpeedDamage();
        damage_radius = PlayerConfigutations.getDamageRadius();
        this.inventory = new InventoryController();

        setWalk_speed(PlayerConfigutations.getWalkSpeed());
        loadAllTextures(PlayerConfigutations.getTextureWidth(), PlayerConfigutations.getTextureHeight());
        setCurTextureDirection(Direction.STOP);
    }

    public int checkForStars(List<Star> stars){
        int playerCenterX = (int) (getPosition().getX() + Configuration.getPlayerWidth() / 2.0);
        int playerCenterY = (int) (getPosition().getY() + Configuration.getPlayerHeight() / 2.0);

        for (int i = 0; i < stars.size(); i++) {
            Star star = stars.get(i);
            int starCenterX = (int) (star.getPosition().getX() + Configuration.getTileSize() / 2.0);
            int starCenterY = (int) (star.getPosition().getY() + Configuration.getTileSize() / 2.0);

            double distance = minus(new Coordinates(playerCenterX, playerCenterY), new Coordinates(starCenterX, starCenterY));
            if (distance <= Configuration.getPickUp()) {
                return  i;
            }
        }
        return -1;
    }

    public int getStar_counter() {
        return star_counter;
    }

    public void setStar_counter(int star_counter) {
        this.star_counter = star_counter;
    }

    public float getDamage() {
        return damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public float getSpeed_damage() {
        return speed_damage;
    }

    public void setSpeed_damage(float speed_damage) {
        this.speed_damage = speed_damage;
    }

    public Integer getCoins() {
        return coins;
    }

    public void setCoins(Integer coins) {
        this.coins = coins;
    }

    public InventoryController getInventory() {
        return inventory;
    }


    public double getDamage_radius() {
        return damage_radius;
    }

    public void setDamage_radius(double damage_radius) {
        this.damage_radius = damage_radius;
    }
}

