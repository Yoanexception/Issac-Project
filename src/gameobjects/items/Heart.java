package gameobjects.items;

import libraries.Vector2;
import resources.ImagePaths;

/**
 * The type Heart.
 */
public class Heart extends Hearts{

    /**
     * Instantiates a new Heart.
     *
     * @param position the position
     */
    public Heart(Vector2 position) {
        super(position, 2, ImagePaths.HEART_PICKABLE);
    }
}
