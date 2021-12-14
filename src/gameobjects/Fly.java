package gameobjects;

import gameWorld.Room;
import libraries.Vector2;
import resources.HeroInfos;
import resources.ImagePaths;
import resources.MonstersInfo;

public class Fly extends Monster {

	private String imagePath;
	private int damage;
	private int projectilDamage;
	private double projectilSpeed;
	private double projectilRange;
	private int projecilWait;
	private Vector2 direction;
	
	public Fly(Vector2 position, Vector2 size, double speed, double life, int damage, double projectilRange, int projectilDamage, double projectilSpeed) {
		super(position, size, speed, life);
		this.imagePath = ImagePaths.FLY;
		this.damage = damage;
		this.projectilDamage = projectilDamage;
		this.projectilRange = projectilRange;
		this.projectilSpeed = projectilSpeed;
		this.projecilWait = 20;
		this.direction = new Vector2();
	}

	public void move(Hero h){
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

	//TODO FUNCTION OF SHOOT

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

	public int getProjectilDamage() {
		return projectilDamage;
	}

	public void setProjectilDamage(int projectilDamage) {
		this.projectilDamage = projectilDamage;
	}

	public double getProjectilSpeed() {
		return projectilSpeed;
	}

	public void setProjectilSpeed(double projectilSpeed) {
		this.projectilSpeed = projectilSpeed;
	}

	public double getProjectilRange() {
		return projectilRange;
	}

	public void setProjectilRange(double projectilRange) {
		this.projectilRange = projectilRange;
	}

	public Vector2 getNormalizedDirection()
	{
		Vector2 normalizedVector = new Vector2(super.getDirection());
		normalizedVector.euclidianNormalize(super.getSpeed());
		return normalizedVector;
	}
	
}
