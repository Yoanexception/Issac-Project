package gameobjects;

import libraries.Vector2;
import resources.ImagePaths;

/**
 * The type Larme.
 */
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
    private Vector2 size;

    /**
     * Instantiates a new Larme.
     *
     * @param position  the position
     * @param damage    the damage
     * @param direction the direction
     * @param range     the range
     */
    public Larme(Vector2 position, int damage, Vector2 direction, double range){
        this.size = new Vector2(0.03, 0.03);
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

    /**
     * Instantiates a new Larme.
     *
     * @param position  the position
     * @param damage    the damage
     * @param direction the direction
     * @param range     the range
     * @param imagePath the image path
     */
    public Larme(Vector2 position, int damage, Vector2 direction, double range, String imagePath){
        this.size = new Vector2(0.03, 0.03);
        this.positionInitial = position;
        this.position = position;
        this.damage = damage;
        this.speed = 0.03;
        this.direction = direction;
        this.imagePath = imagePath;
        this.range = range;
        this.dead = false;
        this.shootByHero = false;
    }

    /**
     * Instantiates a new Larme.
     *
     * @param position    the position
     * @param damage      the damage
     * @param direction   the direction
     * @param range       the range
     * @param shootByHero the shoot by hero
     */
    public Larme(Vector2 position, int damage, Vector2 direction, double range, boolean shootByHero){
        this.size = new Vector2(0.03, 0.03);
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

    /**
     * Update game object.
     */
    public void updateGameObject()
    {
        move();
    }

    /**
     * Permet de faire bouger la larme en ligne droite dependant de ça direction ou de la tuer si elle est allé trop loin.
     */
    public void move(){

        Vector2 normalizedDirection = getNormalizedDirection();
        Vector2 positionAfterMoving = getPosition().addVector(normalizedDirection);
        if(positionInitial.distance(positionAfterMoving) < range && positionInitial.distance(positionAfterMoving) < range){
            setPosition(positionAfterMoving);
        } else {
            dead = true;
        }
    }

    /**
     * Gets normalized direction.
     *
     * @return the normalized direction
     */
    public Vector2 getNormalizedDirection()
    {
        Vector2 normalizedVector = new Vector2(direction);
        normalizedVector.euclidianNormalize(speed);
        return normalizedVector;
    }

    /**
     * Get position vector 2.
     *
     * @return the vector 2
     */
    public Vector2 getPosition(){
        return this.position;
    }

    /**
     * Sets position.
     *
     * @param position the position
     */
    public void setPosition(Vector2 position) { this.position = position; }

    /**
     * Gets image path.
     *
     * @return the image path
     */
    public String getImagePath() { return this.imagePath; }

    /**
     * Gets dead.
     *
     * @return the dead
     */
    public Boolean getDead() { return this.dead; }

    /**
     * Is shoot by hero boolean.
     *
     * @return the boolean
     */
    public Boolean isShootByHero() { return this.shootByHero; }

    /**
     * Gets size.
     *
     * @return the size
     */
    public Vector2 getSize() {
        return size;
    }

    /**
     * Sets size.
     *
     * @param size the size
     */
    public void setSize(Vector2 size) {
        this.size = size;
    }
}
