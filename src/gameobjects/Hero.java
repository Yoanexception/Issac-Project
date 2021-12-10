package gameobjects;

import gameWorld.Room;
import libraries.StdDraw;
import libraries.Vector2;
import resources.HeroInfos;

public class Hero
{
	
	private Vector2 position;
	private Vector2 size;
	private String imagePath;
	private double speed;
	private Vector2 direction;
	private int life;
	private double speedAttack;
	private int damageAttack;
	

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
	}

	public void updateGameObject()
	{
		move();
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
			Larme l = new Larme(position, damageAttack, direction, HeroInfos.ISSAC_RANGE);
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
}
