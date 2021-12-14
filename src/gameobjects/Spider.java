package gameobjects;

import libraries.Vector2;
import resources.ImagePaths;
import resources.MonstersInfo;

public class Spider extends Monster {
	
	private String imagePath;
	private int damage;
	private int timeWait;
	private int movement;
	
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

	public void move(Hero h){
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
				super.setPosition(positionAfterMoving);
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

	public boolean hitHero(Vector2 heroPosition){
		if(
				heroPosition.getY() > super.getPosition().getY() - MonstersInfo.SPIDER_SIZE.getY() / 2
				&& heroPosition.getY() < super.getPosition().getY() + MonstersInfo.SPIDER_SIZE.getY() / 2
				&& heroPosition.getX() > super.getPosition().getX() - MonstersInfo.SPIDER_SIZE.getX() / 2
				&& heroPosition.getX() < super.getPosition().getX() + MonstersInfo.SPIDER_SIZE.getX() / 2
		){
			return true;
		}
		return false;
	}

	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public int getDamage() {
		return damage;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}

	public Vector2 getNormalizedDirection()
	{
		Vector2 normalizedVector = new Vector2(super.getDirection());
		normalizedVector.euclidianNormalize(super.getSpeed());
		return normalizedVector;
	}

}
