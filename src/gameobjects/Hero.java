package gameobjects;

import gameWorld.room.Room;
import gameobjects.obstacles.Obstacles;
import libraries.Physics;
import libraries.StdDraw;
import libraries.Vector2;
import resources.HeroInfos;

import java.util.ArrayList;

/**
 * The type Hero.
 */
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
	private int spikesWait;
	private boolean invincible;
	private int keys;
	private int bomb;
	private int bombWait;


	/**
	 * Instantiates a new Hero.
	 *
	 * @param position  the position
	 * @param size      the size
	 * @param speed     the speed
	 * @param imagePath the image path
	 */
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
		this.spikesWait = 0;
		this.invincible = false;
		this.keys = 0;
		this.bomb = 3;
		this.bombWait = 20;
	}

	/**
	 * Met a jour le hero
	 *
	 * @param l La liste des larmes de la salle
	 * @param o La liste des obstacles de la salle
	 */
	public void updateGameObject(ArrayList<Larme> l, ArrayList<Obstacles> o) {
		move(o);
		if (!invincible) {
			isHitByLarme(l);
			hitSpikes(o);
		}
		if (bombWait > 0) {
			bombWait -= 1;
		}
	}

	/**
	 * Fais bouger le hero par rapport a ca direction
	 *
	 * @param o liste des obstacles de la salle
	 */
	private void move(ArrayList<Obstacles> o)
	{
		double xmax = 0.8900000000000003;
		double xmin = 0.10999999999999968;
		double ymax = 0.9000000000000004;
		double ymin = 0.12999999999999967;
		Vector2 normalizedDirection = getNormalizedDirection();
		Vector2 positionAfterMoving = getPosition().addVector(normalizedDirection);
		if(positionAfterMoving.getX() < xmax && positionAfterMoving.getX() > xmin && positionAfterMoving.getY() < ymax && positionAfterMoving.getY() > ymin){
			if(!hitRock(o, positionAfterMoving)){
				setPosition(positionAfterMoving);
			}
		}
		direction = new Vector2();

		if (speedAttack != 0) {
			speedAttack -= 0.005;
			if(speedAttack < 0){
				speedAttack = 0;
			}
		}

		if (spikesWait != 0) {
			spikesWait -= 1;
			if(spikesWait < 0){
				spikesWait = 0;
			}
		}
	}

	/**
	 * Renvoie si le hero a touche un obstacles
	 *
	 * @param obstacles La liste des obstacles de la salle
	 * @param position La postion du hero pour le test
	 * @return le booleen
	 */
	private boolean hitRock(ArrayList<Obstacles> obstacles, Vector2 position){
		for(Obstacles o : obstacles) {
			if (o.isOverride() && Physics.rectangleCollision(o.getPosition(), o.getSize(), position, size)
			) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Renvoie si le hero a toucher des piques
	 *
	 * @param obstacles La liste des obstacles de la salle
	 */
	private void hitSpikes(ArrayList<Obstacles> obstacles){
		for(Obstacles o : obstacles) {
			if (o.getDamage() >= 1 && spikesWait == 0 && Physics.rectangleCollision(o.getPosition(), o.getSize(), position, size)){
				spikesWait = 30;
				life -= o.getDamage();
			}
		}
	}

	/**
	 * Renvoie si le hero a ete touché par une larme
	 *
	 * @param larmes La liste des larmes de la salle
	 */
	public void isHitByLarme(ArrayList<Larme> larmes){
		ArrayList<Larme> toDelete = new ArrayList<>();
		for (Larme l : larmes) {
			if(!l.isShootByHero() && Physics.rectangleCollision(l.getPosition(), l.getSize(), position, size)){
				toDelete.add(l);
				life -= l.getDamage();
			}
		}
		Room.deleteLarme(toDelete);
	}

	/**
	 * Permet de dessiner le hero
	 */
	public void drawGameObject()
	{
		StdDraw.picture(getPosition().getX(), getPosition().getY(), getImagePath(), getSize().getX(), getSize().getY(),
				0);
	}

	/**
	 * Go up next.
	 */
	/*
	 * Moving from key inputs. Direction vector is later normalised.
	 */
	public void goUpNext()
	{
		getDirection().addY(1);
	}

	/**
	 * Go down next.
	 */
	public void goDownNext()
	{
		getDirection().addY(-1);
	}

	/**
	 * Go left next.
	 */
	public void goLeftNext()
	{
		getDirection().addX(-1);
	}

	/**
	 * Go right next.
	 */
	public void goRightNext()
	{
		getDirection().addX(1);
	}

	/**
	 * Permet de faire tirer le hero selon la direction donnée par le joueur
	 *
	 * @param direction la direction
	 */
	public void shoot(Vector2 direction) {
		if(speedAttack == 0){
			Larme l = new Larme(position, damageAttack, direction, HeroInfos.ISSAC_RANGE, true);
			Room.addLarme(l);
			speedAttack = HeroInfos.ISSAC_SPEED_ATTACK;
		}
	}

	public void bomb(){
		if(bomb > 0 && bombWait == 0){
			Bomb b = new Bomb(position, 4);
			Room.addBomb(b);
			bomb -= 1;
			bombWait = 30;
		}
	}

	/**
	 * Gets normalized direction.
	 *
	 * @return the normalized direction
	 */
	public Vector2 getNormalizedDirection()
	{
		Vector2 normalizedVector = new Vector2(direction);
		normalizedVector.euclidianNormalize(speed);
		return normalizedVector;
	}


	/**
	 * Gets position.
	 *
	 * @return the position
	 */
	/*
	 * Getters and Setters
	 */
	public Vector2 getPosition()
	{
		return position;
	}

	/**
	 * Sets position.
	 *
	 * @param position the position
	 */
	public void setPosition(Vector2 position)
	{
		this.position = position;
	}

	/**
	 * Gets size.
	 *
	 * @return the size
	 */
	public Vector2 getSize()
	{
		return size;
	}

	/**
	 * Sets size.
	 *
	 * @param size the size
	 */
	public void setSize(Vector2 size)
	{
		this.size = size;
	}

	/**
	 * Gets image path.
	 *
	 * @return the image path
	 */
	public String getImagePath()
	{
		return imagePath;
	}

	/**
	 * Sets image path.
	 *
	 * @param imagePath the image path
	 */
	public void setImagePath(String imagePath)
	{
		this.imagePath = imagePath;
	}

	/**
	 * Gets speed.
	 *
	 * @return the speed
	 */
	public double getSpeed()
	{
		return speed;
	}

	/**
	 * Sets speed.
	 *
	 * @param speed the speed
	 */
	public void setSpeed(double speed)
	{
		this.speed = speed;
	}

	/**
	 * Gets direction.
	 *
	 * @return the direction
	 */
	public Vector2 getDirection()
	{
		return direction;
	}

	/**
	 * Sets direction.
	 *
	 * @param direction the direction
	 */
	public void setDirection(Vector2 direction)
	{
		this.direction = direction;
	}

	/**
	 * Sets life.
	 *
	 * @param life the life
	 */
	public void setLife(int life) { this.life = life;}

	/**
	 * Gets life.
	 *
	 * @return the life
	 */
	public int getLife() { return life; }

	/**
	 * Gets life max.
	 *
	 * @return the life max
	 */
	public int getLifeMax() { return lifeMax; }

	/**
	 * Sets life max.
	 *
	 * @param lifeMax the life max
	 */
	public void setLifeMax(int lifeMax) { this.lifeMax = lifeMax; }

	/**
	 * Gets gold.
	 *
	 * @return the gold
	 */
	public int getGold() { return gold; }

	/**
	 * Set gold.
	 *
	 * @param gold the gold
	 */
	public void setGold(int gold){ this.gold = gold; }

	/**
	 * Set damage attack.
	 *
	 * @param damageAttack the damage attack
	 */
	public void setDamageAttack(int damageAttack){ this.damageAttack = damageAttack; }

	/**
	 * Gets damage attack.
	 *
	 * @return the damage attack
	 */
	public int getDamageAttack() { return damageAttack; }

	public boolean isInvincible() {
		return invincible;
	}

	public void setInvincible(boolean invincible) {
		this.invincible = invincible;
	}

	public int getKeys() {
		return keys;
	}

	public void setKeys(int keys) {
		this.keys = keys;
	}

	public int getBomb() { return bomb; }
}
