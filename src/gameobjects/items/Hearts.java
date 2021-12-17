package gameobjects.items;

import libraries.Vector2;
import resources.ImagePaths;

/**
 * The type Hearts.
 */
public class Hearts extends Item{

    private int addLife;

    /**
     * Instantiates a new Hearts.
     *
     * @param position  the position
     * @param addLife   the add life
     * @param imagePath the image path
     */
    public Hearts(Vector2 position, int addLife, String imagePath) {
        super("Heart / Half Heart", position, imagePath);
        this.addLife = addLife;
    }

    /**
     * Gets add life.
     *
     * @return the add life
     */
    public int getAddLife() {
        return addLife;
    }

    /**
     * Sets add life.
     *
     * @param addLife the add life
     */
    public void setAddLife(int addLife) {
        this.addLife = addLife;
    }
}
