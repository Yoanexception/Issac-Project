package gameobjects.boss;

import gameWorld.room.Room;
import gameobjects.Hero;
import gameobjects.Larme;
import libraries.Vector2;
import resources.BossInfos;
import resources.ImagePaths;

public class Steven extends Boss{

    private int waitMove;
    private int waitShoot;
    private int nbMove;


    /**
     * Instantiates a new STEVEN.
     *
     */
    public Steven() {
        super(BossInfos.STEVEN_POSITION, BossInfos.STEVEN_SIZE, BossInfos.STEVEN_SPEED, BossInfos.STEVEN_LIFE, BossInfos.STEVEN_PROJECTILE_RANGE, BossInfos.STEVEN_DAMAGE, BossInfos.STEVEN_PROJECTILE_DAMAGE, BossInfos.STEVEN_PROJECTILE_SPEED, BossInfos.STEVEN_IMAGE_PATH);
        waitMove = 30;
        waitShoot = 40;
        nbMove = 0;
    }

    @Override
    public void updateGameObject(Hero h) {
        move(h);
        shoot(h);
    }

    /**
     * Permet de faire bouger le boss selon son caractere
     *
     * @param h the h
     */
    public void move(Hero h){
        if(nbMove <= 150){
            if(waitMove == 0){
                double xmax = 0.8900000000000003;
                double xmin = 0.10999999999999968;
                double ymax = 0.9000000000000004;
                double ymin = 0.12999999999999967;
                Vector2 positionHero = h.getPosition();
                double dirY = positionHero.getY() - super.getPosition().getY();
                double dirX = positionHero.getX() - super.getPosition().getX();
                super.setDirection(new Vector2(dirX, dirY));
                Vector2 normalizedDirection = getNormalizedDirection();
                Vector2 positionAfterMoving = super.getPosition().addVector(normalizedDirection);
                if (positionAfterMoving.getX() < xmax && positionAfterMoving.getX() > xmin && positionAfterMoving.getY() < ymax && positionAfterMoving.getY() > ymin) {
                    super.setPosition(positionAfterMoving);
                    nbMove += 1;
                    if(nbMove%40 == 0){
                        super.setSpeed(super.getSpeed() + 0.001);
                    }
                }
            } else {
                waitMove -= 1;
            }
        } else {
            nbMove = 0;
            waitMove = 60;
        }
    }

    /**
     * Permet de faire tirer le monstre vers le hero toute les 50 cycles de jeux
     *
     * @param h the hero
     */
    public void shoot(Hero h){
        if(waitShoot == 0){
            Vector2 positionHero = h.getPosition();
            double dirY = positionHero.getY() - super.getPosition().getY();
            double dirX = positionHero.getX() - super.getPosition().getX();
            Vector2 direction = new Vector2(dirX, dirY);
            Larme l = new Larme(super.getPosition(), (int) super.getProjectilDamage(), direction, super.getProjectilLength(), ImagePaths.TEAR_BLOOD);
            Room.addLarme(l);
            waitShoot = 50;
        } else {
            waitShoot -= 1;
        }
    }

}
