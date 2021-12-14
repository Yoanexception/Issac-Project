package resources;

import libraries.Vector2;

public class MonstersInfo {
    public static Vector2 SPIDER_SIZE = new Vector2(0.07, 0.04);
    public static double SPIDER_SPEED = 0.02;
    public static int SPIDER_DAMAGE = 1;
    public static int SPIDER_LIFE = 5;

    public static Vector2 FLY_SIZE = new Vector2(0.05,0.03);
    public static double FLY_SPEED = HeroInfos.ISAAC_SPEED / 8;
    public static int FLY_DAMAGE = 1;
    public static int FLY_LIFE = 3;
    public static int FLY_PROJECTIL_DAMAGE = 1;
    public static double FLY_PROJECTIL_SPEED = 0.03;
    public static double FLY_PROJECTIL_RANGE = 0.4;
    public static int FLY_PROJECTIL_WAIT = 50;

}
