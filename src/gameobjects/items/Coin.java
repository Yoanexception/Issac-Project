package gameobjects.items;

import libraries.Vector2;
import resources.ImagePaths;

/**
 * The type Coin.
 */
public class Coin extends Item {

    private int addCoin;

    /**
     * Instantiates a new Coin.
     *
     * @param position  the position
     * @param addCoin   the amount of coin add to player
     * @param imagePath the image path
     */
    public Coin(Vector2 position, int addCoin, String imagePath) {
        super("Coin",  position, imagePath);
        this.addCoin = addCoin;
    }

    /**
     * Gets the amount of coin add to player.
     *
     * @return the amount of coin add to player
     */
    public int getAddCoin() {
        return addCoin;
    }

    /**
     * Sets the amount of coin add to player
     *
     * @param addCoin the amount of coin add to player
     */
    public void setAddCoin(int addCoin) {
        this.addCoin = addCoin;
    }
}
