package gameobjects;

import libraries.Vector2;
import resources.ImagePaths;

public class Larme {

    private Vector2 position;
    private Vector2 positionInitial;
    private int damage;
    private double speed;
    private Vector2 direction;
    private String imagePath;
    private double range;
    private boolean dead;
    private boolean shootByHero;

    public Larme(Vector2 position, int damage, Vector2 direction, double range){
        this.positionInitial = position;
        this.position = position;
        this.damage = damage;
        this.speed = 0.03;
        this.direction = direction;
        this.imagePath = ImagePaths.TEAR;
        this.range = range;
        this.dead = false;
        this.shootByHero = false;
    }

    public Larme(Vector2 position, int damage, Vector2 direction, double range, boolean shootByHero){
        this.positionInitial = position;
        this.position = position;
        this.damage = damage;
        this.speed = 0.03;
        this.direction = direction;
        this.imagePath = ImagePaths.TEAR;
        this.range = range;
        this.dead = false;
        this.shootByHero = shootByHero;
    }

    public void updateGameObject()
    {
        move();
    }

    public void move(){

        Vector2 normalizedDirection = getNormalizedDirection();
        Vector2 positionAfterMoving = getPosition().addVector(normalizedDirection);
        if(positionInitial.distance(positionAfterMoving) < range && positionInitial.distance(positionAfterMoving) < range){
            setPosition(positionAfterMoving);
        } else {
            dead = true;
        }
    }

    public Vector2 getNormalizedDirection()
    {
        Vector2 normalizedVector = new Vector2(direction);
        normalizedVector.euclidianNormalize(speed);
        return normalizedVector;
    }

    public Vector2 getPosition(){
        return this.position;
    }

    public void setPosition(Vector2 position) { this.position = position; }

    public String getImagePath() { return this.imagePath; }

    public Boolean getDead() { return this.dead; }

    public Boolean isShootByHero() { return this.shootByHero; }
}
