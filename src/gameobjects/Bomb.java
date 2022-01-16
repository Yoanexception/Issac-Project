package gameobjects;

import libraries.Vector2;
import resources.ImagePaths;

public class Bomb {

    private Vector2 position;
    private Vector2 size;
    private double range;
    private int damage;
    private int timeExplosion;
    private String imagePath;

    public Bomb(Vector2 position, int damage){
        this.position = position;
        this.size = new Vector2(0.05,0.05);
        this.range = 0.15;
        this.damage = damage;
        this.timeExplosion = 120;
        this.imagePath = ImagePaths.BOMB;
    }

    public void updateGameObject(){
        timeExplosion -= 1;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public double getRange() {
        return range;
    }

    public void setRange(double range) {
        this.range = range;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getTimeExplosion() {
        return timeExplosion;
    }

    public void setTimeExplosion(int timeExplosion) {
        this.timeExplosion = timeExplosion;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Vector2 getSize() {
        return size;
    }

    public void setSize(Vector2 size) {
        this.size = size;
    }
}
