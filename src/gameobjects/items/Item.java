package gameobjects.items;

import libraries.Vector2;

/**
 * The type Item.
 */
public class Item {

	private String name;
	private Vector2 position;
	private String imagePath;
	private Vector2 size;

	//Caracterique possible d'evolution de Issac

	private boolean heal;
	private int addLifeMax;
	private int addDamage;
	private int healLife;
	private double addSpeed;

	/**
	 * Instantiates a new Item.
	 *
	 * @param name      the name
	 * @param position  the position
	 * @param imagePath the image path
	 */
	public Item(String name, Vector2 position, String imagePath) {
		this.name = name;
		this.position = position;
		this.imagePath = imagePath;
		this.size = new Vector2(0.075, 0.075);
		this.heal = false;
		this.addLifeMax = 0;
		this.addDamage = 0;
		this.healLife = 0;
		this.addSpeed = 0;
	}

	/**
	 * Is heal boolean.
	 *
	 * @return the boolean
	 */
	public boolean isHeal() {
		return heal;
	}

	/**
	 * Sets heal.
	 *
	 * @param heal the heal
	 */
	public void setHeal(boolean heal) {
		this.heal = heal;
	}

	/**
	 * Gets add damage.
	 *
	 * @return the add damage
	 */
	public int getAddDamage() {
		return addDamage;
	}

	/**
	 * Sets add damage.
	 *
	 * @param addDamage the add damage
	 */
	public void setAddDamage(int addDamage) {
		this.addDamage = addDamage;
	}

	/**
	 * Gets add life max.
	 *
	 * @return the add life max
	 */
	public int getAddLifeMax() {
		return addLifeMax;
	}

	/**
	 * Sets add life max.
	 *
	 * @param addLifeMax the add life max
	 */
	public void setAddLifeMax(int addLifeMax) {
		this.addLifeMax = addLifeMax;
	}

	/**
	 * Set position.
	 *
	 * @param position the position
	 */
	public void setPosition(Vector2 position){
		this.position = position;
	}

	/**
	 * Gets position.
	 *
	 * @return the position
	 */
	public Vector2 getPosition() {
		return position;
	}

	/**
	 * Gets name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets image path.
	 *
	 * @return the image path
	 */
	public String getImagePath() {
		return imagePath;
	}

	/**
	 * Gets heal life.
	 *
	 * @return the heal life
	 */
	public int getHealLife() {
		return healLife;
	}

	/**
	 * Sets heal life.
	 *
	 * @param healLife the heal life
	 */
	public void setHealLife(int healLife) {
		this.healLife = healLife;
	}

	/**
	 * Gets add speed.
	 *
	 * @return the add speed
	 */
	public double getAddSpeed() {
		return addSpeed;
	}

	/**
	 * Sets add speed.
	 *
	 * @param addSpeed the add speed
	 */
	public void setAddSpeed(double addSpeed) {
		this.addSpeed = addSpeed;
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
}
