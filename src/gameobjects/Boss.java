package gameobjects;

import gameWorld.room.Room;
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
	private int waitMove;
	private int waitShoot;
	private int nbMove;

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
		waitMove = 30;
		waitShoot = 40;
		nbMove = 0;
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
			Larme l = new Larme(super.getPosition(), (int) projectilDamage, direction, projectilLength, ImagePaths.TEAR_BLOOD);
			Room.addLarme(l);
			waitShoot = 50;
		} else {
			waitShoot -= 1;
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
