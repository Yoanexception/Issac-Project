package gameobjects.monsters;

import gameobjects.Hero;
import gameobjects.Larme;
import gameobjects.obstacles.Obstacles;
import libraries.Physics;
import libraries.Vector2;

import java.util.ArrayList;

/**
 * The type Monster.
 */
public class Monster {
	
	private Vector2 position;
	private Vector2 size;
	private double speed;
	private double life;
	private Vector2 direction;
	private int wait;

	/**
	 * Instantiates a new Monster.
	 *
	 * @param position the position
	 * @param size     the size
	 * @param speed    the speed
	 * @param life     the life
	 */
	public Monster(Vector2 position, Vector2 size, double speed, double life) {
		this.position = position;
		this.size = size;
		this.speed = speed;
		this.life = life;
		this.direction = new Vector2();
		this.wait = 0;
	}

	/**
	 * Rend si le monstre a été touche par une larme ou non
	 *
	 * @param l la liste des larmes present dans la salle
	 * @return the boolean
	 */
	public boolean isHit(Larme l){
		if(l.isShootByHero() && Physics.rectangleCollision(position, size, l.getPosition(), l.getSize())) {
			life -= l.getDamage();
			return true;
		}
		return false;
	}

	/**
	 * Est ce que le hero a toucher le monstre
	 *
	 * @param hero the hero
	 * @return the boolean
	 */
	public boolean hitHero(Hero hero){
		if(Physics.rectangleCollision(hero.getPosition(), hero.getSize(), position, size)){
			return true;
		}
		return false;
	}

	/**
	 * Permet de faire bouger le monstre
	 *
	 * @param h         the hero
	 * @param obstacles La liste des obstacles dans la salle courante
	 */
	public void move(Hero h, ArrayList<Obstacles> obstacles) {}

	/**
	 * Renvoie si le monstre est mort ou pas (mort = vie a 0)
	 *
	 * @return the boolean
	 */
	public boolean isDead () { return life <= 0; }

	/**
	 * Gets position.
	 *
	 * @return the position
	 */
	public Vector2 getPosition() {
		return position;
	}

	/**
	 * Sets position.
	 *
	 * @param position the position
	 */
	public void setPosition(Vector2 position) {
		this.position = position;
	}

	/**
	 * Gets size.
	 *
	 * @return the size
	 */
	public Vector2 getSize() {
		return size;
	}

	/**
	 * Sets size.
	 *
	 * @param size the size
	 */
	public void setSize(Vector2 size) {
		this.size = size;
	}

	/**
	 * Gets speed.
	 *
	 * @return the speed
	 */
	public double getSpeed() {
		return speed;
	}

	/**
	 * Sets speed.
	 *
	 * @param speed the speed
	 */
	public void setSpeed(double speed) {
		this.speed = speed;
	}

	/**
	 * Gets life.
	 *
	 * @return the life
	 */
	public double getLife() {
		return life;
	}

	/**
	 * Sets life.
	 *
	 * @param life the life
	 */
	public void setLife(double life) {
		this.life = life;
	}

	/**
	 * Gets image path.
	 *
	 * @return the image path
	 */
	public String getImagePath() {
		return "";
	}

	/**
	 * Gets direction.
	 *
	 * @return the direction
	 */
	public Vector2 getDirection() { return direction; }

	/**
	 * Set direction.
	 *
	 * @param direction the direction
	 */
	public void setDirection(Vector2 direction){ this.direction = direction; }

	/**
	 * Gets damage.
	 *
	 * @return the damage
	 */
	public int getDamage() { return 0; }

	/**
	 * Get wait int.
	 *
	 * @return the int
	 */
	public int getWait(){ return wait; }

	/**
	 * Set wait.
	 *
	 * @param wait the wait
	 */
	public void setWait(int wait){ this.wait = wait; }

}
