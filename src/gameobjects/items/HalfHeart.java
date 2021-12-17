package gameobjects.items;

import libraries.Vector2;
import resources.ImagePaths;

/**
 * The type Half heart.
 */
public class HalfHeart extends Hearts{

    /**
     * Instantiates a new Half heart.
     *
     * @param position the position
     */
    public HalfHeart(Vector2 position) {
        super(position, 1, ImagePaths.HALF_HEART_PICKABLE);
    }

}
