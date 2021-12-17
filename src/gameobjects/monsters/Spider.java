package gameobjects.monsters;

import gameobjects.Hero;
import gameobjects.monsters.Monster;
import gameobjects.obstacles.Obstacles;
import libraries.Physics;
import libraries.Vector2;
import resources.ImagePaths;
import resources.MonstersInfo;

import java.util.ArrayList;

/**
 * The type Spider.
 */
public class Spider extends Monster {
	
	private String imagePath;
	private int damage;
	private int timeWait;
	private int movement;

	/**
	 * Instantiates a new Spider.
	 *
	 * @param position the position
	 * @param size     the size
	 * @param speed    the speed
	 * @param life     the life
	 * @param damage   the damage
	 */
	public Spider(Vector2 position, Vector2 size, double speed, double life, int damage) {
		super(position, size, speed, life);
		this.damage = damage;
		this.imagePath = ImagePaths.SPIDER;
		this.timeWait = 5;
		this.movement = 0;
		double randomX = (Math.random() * 2) - 1;
		double randomY = (Math.random() * 2) - 1;
		super.setDirection(new Vector2(randomX, randomY));
	}

	public void move(Hero h, ArrayList<Obstacles> obstacles){
		if(timeWait > 0){
			timeWait -= 1;
		}
		if(timeWait == 0) {
			double xmax = 0.8900000000000003;
			double xmin = 0.10999999999999968;
			double ymax = 0.9000000000000004;
			double ymin = 0.12999999999999967;
			Vector2 normalizedDirection = getNormalizedDirection();
			Vector2 positionAfterMoving = super.getPosition().addVector(normalizedDirection);
			if (positionAfterMoving.getX() < xmax && positionAfterMoving.getX() > xmin && positionAfterMoving.getY() < ymax && positionAfterMoving.getY() > ymin) {
				if(!hitRock(obstacles, positionAfterMoving)){
					setPosition(positionAfterMoving);
				}
			}
			movement += 1;
			if(movement == 15){
				movement = 0;
				timeWait = (int) (5 + Math.random() * 55);
				double randomX = (Math.random() * 2) - 1;
				double randomY = (Math.random() * 2) - 1;
				super.setDirection(new Vector2(randomX, randomY));
			}
		}
	}

	/**
	 * Renvoie si le monstre a rencontrer un obstacle de type roche
	 *
	 * @param obstacles the obstacles
	 * @param position  the position
	 * @return the boolean
	 */
	public boolean hitRock(ArrayList<Obstacles> obstacles, Vector2 position){
		for(Obstacles o : obstacles) {
			if (o.isOverride() && Physics.rectangleCollision(position, super.getSize(), o.getPosition(), o.getSize())) {
				return true;
			}
		}
		return false;
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
