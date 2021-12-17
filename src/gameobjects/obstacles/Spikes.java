package gameobjects.obstacles;

import libraries.Vector2;
import resources.ImagePaths;
import resources.ObstaclesInfo;

/**
 * The type Spikes.
 */
public class Spikes extends Obstacles{


    /**
     * Instantiates a new Spikes.
     *
     * @param position the position
     */
    public Spikes(Vector2 position) {
        super(position, ImagePaths.SPIKES, 1, ObstaclesInfo.SIZE_SPIKES, false);
    }
}
