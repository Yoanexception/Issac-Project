package resources;

import libraries.Vector2;

public class MonstersInfo {
    public static Vector2 SPIDER_SIZE = RoomInfos.TILE_SIZE.scalarMultiplication(0.7);
    public static double SPIDER_SPEED = 0.01;
    public static int SPIDER_DAMAGE = 1;
    public static int SPIDER_LIFE = 5;

    public static Vector2 FLY_SIZE = RoomInfos.TILE_SIZE.scalarMultiplication(0.7);
    public static double FLY_SPEED = 0.01;
    public static int FLY_DAMAGE = 2;
    public static int FLY_LIFE = 3;
    public static int FLY_PROJECTIL_DAMAGE = 1;
    public static double FLY_PROJECTIL_SPEED = 0.03;
    public static double FLY_PROJECTIL_RANGE = 0.4;

}
