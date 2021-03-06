package gameWorld.room;

import gameWorld.Door;
import gameobjects.Bomb;
import gameobjects.boss.Boss;
import gameobjects.Hero;
import gameobjects.Larme;
import gameobjects.boss.Loki;
import gameobjects.boss.Steven;
import gameobjects.boss.TheFallen;
import libraries.Physics;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.RoomInfos;

import java.util.ArrayList;

/**
 * The type Boss room.
 */
public class BossRoom extends Room {
	
	private Boss boss;

	/**
	 * Instantiates a new Boss room.
	 *
	 * @param hero      the hero
	 * @param leftRoom  the left room
	 * @param rightRoom the right room
	 * @param upRoom    the up room
	 * @param downRoom  the down room
	 */
	public BossRoom(Hero hero, Door leftRoom, Door rightRoom, Door upRoom, Door downRoom) {
		super(hero, leftRoom, rightRoom, upRoom, downRoom);
		generateBoss();
	}


	/**
	 * Generate boss.
	 */
	public void generateBoss() {
		boss = new TheFallen();
	}

	/**
	 * Permet de mettre a jour la room en appelant celui de la classe Room, on rajoute juste le fait de faire bouger le boss, et de la mettre a jour.
	 */
	public void updateRoom(){
		super.updateRoom();
		boss.updateGameObject(super.getHero());
		updateBoss();
	}

	/**
	 * Mettre a jour le boss en regardant si il a été touché par une larme ou bien si il a touche le hero.
	 */
	public void updateBoss(){
		if(isBossDead()){return;}
		ArrayList<Larme> larmeToDelete = new ArrayList<>();
			boss.setWait(boss.getWait() > 0 ? boss.getWait() - 1 : 0);
			if(boss.hitHero(super.getHero()) && boss.getWait() == 0){
				super.getHero().setLife(super.getHero().getLife() - boss.getDamage());
				boss.setWait(10);
			}
			for(Larme l : super.getLarme()){
				if(boss.isHit(l)){
					larmeToDelete.add(l);
				}
			}
		super.deleteLarme(larmeToDelete);
	}

	public void explodeBomb(Bomb b) {
		super.explodeBomb(b);
		if(Physics.CircleToPointCollision(boss.getPosition(), b.getPosition(), b.getRange(), boss.getSize())){
			boss.setLife(boss.getLife() - b.getDamage());
		}
	}

	/**
	 * Permet de dessiner la salle, on rajoute a celle de la classe Room le fait de dessiner le boss.
	 */
	public void drawRoom(){
		super.drawRoom();
		drawBoss();
	}

	/**
	 * Draw boss.
	 */
	public void drawBoss() {
		if (!isBossDead()) {
			StdDraw.picture(boss.getPosition().getX(), boss.getPosition().getY(), boss.getImagePath(), boss.getSize().getX(), boss.getSize().getY());
		}
	}

	private final Vector2 positionHole = new Vector2(0.5,0.5);
	private final Vector2 sizeHole = new Vector2(0.2,0.2);

	public boolean isBossDead(){
		if(boss.getLife() == 0){
			StdDraw.picture(positionHole.getX(), positionHole.getY(), ImagePaths.HOLE, sizeHole.getX(), sizeHole.getY());
			return true;
		}
		return false;
	}

	public boolean isGoInHole(Hero h){
		if(isBossDead()) return Physics.rectangleCollision(h.getPosition(), h.getSize(), positionHole, sizeHole);
		return false;
	}
	
	
}
