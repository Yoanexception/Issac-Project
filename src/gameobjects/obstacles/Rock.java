package gameobjects.obstacles;

import libraries.Vector2;
import resources.ImagePaths;
import resources.ObstaclesInfo;

/**
 * The type Rock.
 */
public class Rock extends Obstacles{

    /**
     * Instantiates a new Rock.
     *
     * @param position the position
     */
    public Rock(Vector2 position){
        super(position, ImagePaths.ROCK, 0, ObstaclesInfo.SIZE_ROCK, true);
    }

}
