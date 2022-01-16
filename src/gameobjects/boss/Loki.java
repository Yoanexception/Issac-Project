package gameobjects.boss;

import gameWorld.room.Room;
import gameobjects.Hero;
import gameobjects.Larme;
import libraries.Vector2;
import resources.BossInfos;
import resources.ImagePaths;

public class Loki extends Boss{

    private int statut;
    private int timer;

    /**
     * Instantiates a new Loki Boss.
     */
    public Loki() {
        super(BossInfos.LOKI_POSITION, BossInfos.LOKI_SIZE, BossInfos.LOKI_SPEED, BossInfos.LOKI_LIFE, BossInfos.LOKI_PROJECTILE_RANGE, BossInfos.LOKI_DAMAGE, BossInfos.LOKI_PROJECTILE_DAMAGE, BossInfos.LOKI_PROJECTILE_SPEED, BossInfos.LOKI_IMAGE_PATH);
        this.statut = 0;
        this.timer = 20;
    }

    public void updateGameObject(Hero h){
        if(timer > 0) timer--;
        if(timer == 0){
            if(statut < 4) statut++;
            else statut = 0;
        }
        move();
        shoot();
    }

    public void move(){
        if(statut == 4 && timer == 0){
            double x = 0.2 + Math.random() * 0.6;
            double y = 0.2 + Math.random() * 0.6;
            Vector2 position = new Vector2(x, y);
            super.setPosition(position);
            timer = 60;
            statut = 0;
        }
    }

    public void shoot(){
        if(statut <= 3 && timer == 0){
            if (statut == 0 || statut == 2) {
                System.out.println("Je tire aux points cardinaux");
                shootCardinals();
                timer = 80;
            }
            if (statut == 1){
                System.out.println("Je tire aux points diagonaux");
                shootDiagonals();
                timer = 80;
            }
            if (statut == 3){
                System.out.println("Je tire partout");
                shootCardinals();
                shootDiagonals();
                timer = 80;
            }
        }
    }

    public void shootCardinals(){
        Vector2 directionNorth = new Vector2(0,1);
        Vector2 directionSouth = new Vector2(0,-1);
        Vector2 directionEast = new Vector2(1,0);
        Vector2 directionWest = new Vector2(-1,0);

        Room.addLarme(generateLarme(directionNorth));
        Room.addLarme(generateLarme(directionSouth));
        Room.addLarme(generateLarme(directionEast));
        Room.addLarme(generateLarme(directionWest));
    }

    public void shootDiagonals(){
        Vector2 directionNorthWest = new Vector2(-1,1);
        Vector2 directionNorthEast = new Vector2(1,1);
        Vector2 directionSouthWest = new Vector2(-1,-1);
        Vector2 directionSouthEast = new Vector2(1,-1);

        Room.addLarme(generateLarme(directionNorthWest));
        Room.addLarme(generateLarme(directionNorthEast));
        Room.addLarme(generateLarme(directionSouthEast));
        Room.addLarme(generateLarme(directionSouthWest));
    }

    public Larme generateLarme(Vector2 direction){
        return new Larme(super.getPosition(), (int) super.getProjectilDamage(), direction, super.getProjectilLength(), ImagePaths.TEAR_BLOOD);
    }


}
