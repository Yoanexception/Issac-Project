package gameobjects.items;

import libraries.Vector2;
import resources.ImagePaths;

public class Key extends Item{

    /**
     * Instantiates a new Item.
     *
     * @param position  the position
     */
    public Key(Vector2 position) {
        super("Key", position, ImagePaths.KEY);
    }
}
