package gameobjects.boss;

import gameWorld.room.Room;
import gameobjects.Hero;
import gameobjects.Larme;
import libraries.Vector2;
import resources.BossInfos;
import resources.ImagePaths;

import java.util.Random;

public class TheFallen extends Boss {

    private int timer;
    private int shootTimer;

    /**
     * Instantiates a new Boss.
     *
     */
    public TheFallen() {
        super(BossInfos.THE_FALLEN_POSITION, BossInfos.THE_FALLEN_SIZE, BossInfos.THE_FALLEN_SPEED, BossInfos.THE_FALLEN_LIFE, BossInfos.LOKI_PROJECTILE_RANGE, BossInfos.THE_FALLEN_DAMAGE, BossInfos.THE_FALLEN_PROJECTILE_DAMAGE, BossInfos.THE_FALLEN_PROJECTILE_SPEED, BossInfos.THE_FALLEN_IMAGE_PATH);
        timer = 0;
        shootTimer = 10;
    }

    public void updateGameObject(Hero h){
        if(timer > 0) timer--;
        if(shootTimer > 0) shootTimer--;
        move();
        shoot(h);
    }

    private void shoot(Hero h) {
        if(shootTimer == 0){
            Vector2 positionHero = h.getPosition();
            double dirY = positionHero.getY() - super.getPosition().getY();
            double dirX = positionHero.getX() - super.getPosition().getX();
            Vector2 direction = new Vector2(dirX, dirY);
            Larme l = new Larme(super.getPosition(), (int) super.getProjectilDamage(), direction, super.getProjectilLength(), ImagePaths.TEAR_BLOOD);
            Room.addLarme(l);
            shootTimer = 30;
        }
    }

    private void move() {
        if(timer == 0){
           Vector2 newDir = generateDirection();
           super.setDirection(newDir);
           timer = 45;
        }
        double xmax = 0.8900000000000003;
        double xmin = 0.10999999999999968;
        double ymax = 0.9000000000000004;
        double ymin = 0.12999999999999967;
        Vector2 normalizedDirection = getNormalizedDirection();
        Vector2 positionAfterMoving = super.getPosition().addVector(normalizedDirection);
        if (positionAfterMoving.getX() < xmax && positionAfterMoving.getX() > xmin && positionAfterMoving.getY() < ymax && positionAfterMoving.getY() > ymin) {
            super.setPosition(positionAfterMoving);
        }
    }

    private Vector2 generateDirection() {
        int random = new Random().nextInt(4);
        return switch (random) {
            case 1 -> new Vector2(-1,0);
            case 2 -> new Vector2(0, 1);
            case 3 -> new Vector2(0, -1);
            default -> new Vector2(1,0);
        };
    }


}
