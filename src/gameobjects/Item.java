package gameobjects;

import libraries.Vector2;

public class Item {

	private String name;
	private Vector2 position;
	private String imagePath;

	//Caracterique possible d'evolution de Issac

	private boolean heal;
	private int addLifeMax;
	private int addDamage;
	private int healLife;
	
	public Item(String name, Vector2 position, String imagePath) {
		this.name = name;
		this.position = position;
		this.imagePath = imagePath;
		this.heal = false;
		this.addLifeMax = 0;
		this.addDamage = 0;
		this.healLife = 0;
	}

	public boolean isHeal() {
		return heal;
	}

	public void setHeal(boolean heal) {
		this.heal = heal;
	}

	public int getAddDamage() {
		return addDamage;
	}

	public void setAddDamage(int addDamage) {
		this.addDamage = addDamage;
	}

	public int getAddLifeMax() {
		return addLifeMax;
	}

	public void setAddLifeMax(int addLifeMax) {
		this.addLifeMax = addLifeMax;
	}

	public void setPosition(Vector2 position){
		this.position = position;
	}

	public Vector2 getPosition() {
		return position;
	}

	public String getName() {
		return name;
	}

	public String getImagePath() {
		return imagePath;
	}

	public int getHealLife() {
		return healLife;
	}

	public void setHealLife(int healLife) {
		this.healLife = healLife;
	}
}
