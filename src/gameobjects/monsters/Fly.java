package gameobjects.monsters;

import gameWorld.room.Room;
import gameobjects.Hero;
import gameobjects.Larme;
import gameobjects.obstacles.Obstacles;
import libraries.Vector2;
import resources.ImagePaths;
import resources.MonstersInfo;

import java.util.ArrayList;

/**
 * The type Fly.
 */
public class Fly extends Monster {

	private String imagePath;
	private int damage;
	private int projectilDamage;
	private double projectilSpeed;
	private double projectilRange;
	private int projecilWait;
	private Vector2 direction;

	/**
	 * Instantiates a new Fly.
	 *
	 * @param position        the position
	 * @param size            the size
	 * @param speed           the speed
	 * @param life            the life
	 * @param damage          the damage
	 * @param projectilRange  the projectil range
	 * @param projectilDamage the projectil damage
	 * @param projectilSpeed  the projectil speed
	 */
	public Fly(Vector2 position, Vector2 size, double speed, double life, int damage, double projectilRange, int projectilDamage, double projectilSpeed) {
		super(position, size, speed, life);
		this.imagePath = ImagePaths.FLY;
		this.damage = damage;
		this.projectilDamage = projectilDamage;
		this.projectilRange = projectilRange;
		this.projectilSpeed = projectilSpeed;
		this.projecilWait = (int) (30 +  Math.random() * 40);
		this.direction = new Vector2();
	}

	public void move(Hero h, ArrayList<Obstacles> obstacles){
		shoot(h);
		projecilWait -= 1;
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
		}
	}

	/**
	 * Permet de faire tirer la mouche
	 *
	 * @param h Le hero
	 */
	public void shoot(Hero h){
		if(projecilWait == 0){
			Vector2 positionHero = h.getPosition();
			double dirY = positionHero.getY() - super.getPosition().getY();
			double dirX = positionHero.getX() - super.getPosition().getX();
			direction = new Vector2(dirX, dirY);
			Larme l = new Larme(super.getPosition(), projectilDamage, direction, projectilRange);
			Room.addLarme(l);
			projecilWait = MonstersInfo.FLY_PROJECTIL_WAIT;
		}
	}

	public String getImagePath() {
		return imagePath;
	}

	/**
	 * Sets image path.
	 *
	 * @param imagePath the image path
	 */
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public int getDamage() {
		return damage;
	}

	/**
	 * Sets damage.
	 *
	 * @param damage the damage
	 */
	public void setDamage(int damage) {
		this.damage = damage;
	}

	/**
	 * Gets projectil damage.
	 *
	 * @return the projectil damage
	 */
	public int getProjectilDamage() {
		return projectilDamage;
	}

	/**
	 * Sets projectil damage.
	 *
	 * @param projectilDamage the projectil damage
	 */
	public void setProjectilDamage(int projectilDamage) {
		this.projectilDamage = projectilDamage;
	}

	/**
	 * Gets projectil speed.
	 *
	 * @return the projectil speed
	 */
	public double getProjectilSpeed() {
		return projectilSpeed;
	}

	/**
	 * Sets projectil speed.
	 *
	 * @param projectilSpeed the projectil speed
	 */
	public void setProjectilSpeed(double projectilSpeed) {
		this.projectilSpeed = projectilSpeed;
	}

	/**
	 * Gets projectil range.
	 *
	 * @return the projectil range
	 */
	public double getProjectilRange() {
		return projectilRange;
	}

	/**
	 * Sets projectil range.
	 *
	 * @param projectilRange the projectil range
	 */
	public void setProjectilRange(double projectilRange) {
		this.projectilRange = projectilRange;
	}

	/**
	 * Gets normalized direction.
	 *
	 * @return the normalized direction
	 */
	public Vector2 getNormalizedDirection()
	{
		Vector2 normalizedVector = new Vector2(super.getDirection());
		normalizedVector.euclidianNormalize(super.getSpeed());
		return normalizedVector;
	}
	
}
