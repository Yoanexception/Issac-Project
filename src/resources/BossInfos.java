package resources;

import libraries.Vector2;

public class BossInfos {

    public static String STEVEN_IMAGE_PATH = ImagePaths.BOSS_STEVEN;
    public static Vector2 STEVEN_POSITION = new Vector2(0.5, 0.8);
    public static Vector2 STEVEN_SIZE = RoomInfos.TILE_SIZE.scalarMultiplication(2);
    public static double STEVEN_SPEED = 0.001;
    public static int STEVEN_DAMAGE = 1;
    public static int STEVEN_LIFE = 10;
    public static double STEVEN_PROJECTILE_RANGE = 0.4;
    public static int STEVEN_PROJECTILE_DAMAGE = 1;
    public static double STEVEN_PROJECTILE_SPEED = 0.05;


}
