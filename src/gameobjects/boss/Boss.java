package gameobjects.boss;

import gameWorld.room.Room;
import gameobjects.Hero;
import gameobjects.Larme;
import gameobjects.monsters.Monster;
import libraries.Physics;
import libraries.Vector2;
import resources.ImagePaths;
import resources.MonstersInfo;

/**
 * The type Boss.
 */
public class Boss extends Monster {
	
	private String imagePath;
	private int damage;
	private double projectilDamage;
	private double projectilSpeed;
	private double projectilLength;

	/**
	 * Instantiates a new Boss.
	 *
	 * @param position        the position
	 * @param size            the size
	 * @param speed           the speed
	 * @param life            the life
	 * @param projectilLength the projectil length
	 * @param damage          the damage
	 * @param projectilDamage the projectil damage
	 * @param projectilSpeed  the projectil speed
	 * @param imagePath       the image path
	 */
	public Boss(Vector2 position, Vector2 size, double speed, int life, double projectilLength, int damage, double projectilDamage, double projectilSpeed, String imagePath) {
		super(position, size, speed, life);
		this.imagePath = imagePath;
		this.damage = damage;
		this.projectilDamage = projectilDamage;
		this.projectilLength = projectilLength;
		this.projectilSpeed = projectilSpeed;
	}

	/**
	 * Permet de faire bouger le boss selon son caractere
	 *
	 * @param h the h
	 */
	public void move(Hero h){}

	/**
	 * Permet de faire tirer le monstre vers le hero toute les 50 cycles de jeux
	 *
	 * @param h the hero
	 */
	public void shoot(Hero h){}

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
	public double getProjectilDamage() {
		return projectilDamage;
	}

	/**
	 * Sets projectil damage.
	 *
	 * @param projectilDamage the projectil damage
	 */
	public void setProjectilDamage(double projectilDamage) {
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
	 * Gets projectil length.
	 *
	 * @return the projectil length
	 */
	public double getProjectilLength() {
		return projectilLength;
	}

	/**
	 * Sets projectil length.
	 *
	 * @param projectilLength the projectil length
	 */
	public void setProjectilLength(double projectilLength) {
		this.projectilLength = projectilLength;
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
