package gameobjects;

import gameWorld.Room;
import libraries.StdDraw;
import libraries.Vector2;
import resources.HeroInfos;

import java.util.ArrayList;

public class Hero
{
	
	private Vector2 position;
	private Vector2 size;
	private String imagePath;
	private double speed;
	private Vector2 direction;
	private int lifeMax;
	private int life;
	private double speedAttack;
	private int damageAttack;
	private int gold;
	

	public Hero(Vector2 position, Vector2 size, double speed, String imagePath)
	{
		this.position = position;
		this.size = size;
		this.speed = speed;
		this.imagePath = imagePath;
		this.direction = new Vector2();
		this.life = HeroInfos.ISSAC_LIFE;
		this.damageAttack = HeroInfos.ISSAC_DAMAGE_ATTACK;
		this.speedAttack = 0;
		this.gold = 0;
		this.lifeMax = HeroInfos.ISSAC_LIFE;
	}

	public void updateGameObject(ArrayList<Larme> l)
	{
		move();
		isHitByLarme(l);
	}

	private void move()
	{
		double xmax = 0.8900000000000003;
		double xmin = 0.10999999999999968;
		double ymax = 0.9000000000000004;
		double ymin = 0.12999999999999967;
		Vector2 normalizedDirection = getNormalizedDirection();
		Vector2 positionAfterMoving = getPosition().addVector(normalizedDirection);
		if(positionAfterMoving.getX() < xmax && positionAfterMoving.getX() > xmin && positionAfterMoving.getY() < ymax && positionAfterMoving.getY() > ymin){
			setPosition(positionAfterMoving);
		}
		direction = new Vector2();

		if (speedAttack != 0) {
			speedAttack -= 0.005;
			if(speedAttack < 0){
				speedAttack = 0;
			}
		}
	}

	public void isHitByLarme(ArrayList<Larme> larmes){
		ArrayList<Larme> toDelete = new ArrayList<>();
		for (Larme l : larmes) {
			if(
					l.getPosition().getX() > position.getX() - size.getX() / 2
							&& l.getPosition().getX() < position.getX() + size.getX() / 2
							&& l.getPosition().getY() > position.getY() - size.getY() / 2
							&& l.getPosition().getY() < position.getY() + size.getY() / 2
					&& !l.isShootByHero()
			){
				toDelete.add(l);
				life -= 1;
			}
		}
		Room.deleteLarme(toDelete);
	}

	public void drawGameObject()
	{
		StdDraw.picture(getPosition().getX(), getPosition().getY(), getImagePath(), getSize().getX(), getSize().getY(),
				0);
	}

	/*
	 * Moving from key inputs. Direction vector is later normalised.
	 */
	public void goUpNext()
	{
		getDirection().addY(1);
	}

	public void goDownNext()
	{
		getDirection().addY(-1);
	}

	public void goLeftNext()
	{
		getDirection().addX(-1);
	}

	public void goRightNext()
	{
		getDirection().addX(1);
	}

	public void shoot(Vector2 direction) {
		if(speedAttack == 0){
			Larme l = new Larme(position, damageAttack, direction, HeroInfos.ISSAC_RANGE, true);
			Room.addLarme(l);
			speedAttack = HeroInfos.ISSAC_SPEED_ATTACK;
		}
	}

	public Vector2 getNormalizedDirection()
	{
		Vector2 normalizedVector = new Vector2(direction);
		normalizedVector.euclidianNormalize(speed);
		return normalizedVector;
	}




	/*
	 * Getters and Setters
	 */
	public Vector2 getPosition()
	{
		return position;
	}

	public void setPosition(Vector2 position)
	{
		this.position = position;
	}

	public Vector2 getSize()
	{
		return size;
	}

	public void setSize(Vector2 size)
	{
		this.size = size;
	}

	public String getImagePath()
	{
		return imagePath;
	}

	public void setImagePath(String imagePath)
	{
		this.imagePath = imagePath;
	}

	public double getSpeed()
	{
		return speed;
	}

	public void setSpeed(double speed)
	{
		this.speed = speed;
	}

	public Vector2 getDirection()
	{
		return direction;
	}

	public void setDirection(Vector2 direction)
	{
		this.direction = direction;
	}

	public void setLife(int life) { this.life = life;}

	public int getLife() { return life; }

	public int getLifeMax() { return lifeMax; }

	public void setLifeMax(int lifeMax) { this.lifeMax = lifeMax; }

	public int getGold() { return gold; }

	public void setGold(int gold){ this.gold = gold; }
}
