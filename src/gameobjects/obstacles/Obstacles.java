package gameobjects.obstacles;

import libraries.Vector2;

/**
 * The type Obstacles.
 */
public class Obstacles {

    private String imagePath;
    private int damage;
    private Vector2 size;
    private Vector2 position;
    private boolean override;

    /**
     * Instantiates a new Obstacles.
     *
     * @param position  the position
     * @param imagePath the image path
     * @param damage    the damage
     * @param size      the size
     * @param override  the override
     */
    public Obstacles(Vector2 position, String imagePath, int damage, Vector2 size, boolean override){
        this.position = position;
        this.imagePath = imagePath;
        this.damage = damage;
        this.size = size;
        this.override = override;
    }


    /**
     * Gets image path.
     *
     * @return the image path
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * Sets image path.
     *
     * @param imagePath the image path
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    /**
     * Gets damage.
     *
     * @return the damage
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Sets damage.
     *
     * @param damage the damage
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }

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

    /**
     * Gets position.
     *
     * @return the position
     */
    public Vector2 getPosition() {
        return position;
    }

    /**
     * Sets position.
     *
     * @param position the position
     */
    public void setPosition(Vector2 position) {
        this.position = position;
    }

    /**
     * Is override boolean.
     *
     * @return the boolean
     */
    public boolean isOverride() {
        return override;
    }

    /**
     * Sets override.
     *
     * @param override the override
     */
    public void setOverride(boolean override) {
        this.override = override;
    }
}
