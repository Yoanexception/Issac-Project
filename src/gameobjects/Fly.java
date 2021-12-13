package gameobjects;

import libraries.Vector2;
import resources.ImagePaths;

public class Fly extends Monster {

	private String imagePath;
	private int damage;
	private double projectilDamage;
	private double projectilSpeed;
	private double projectilRange;
	
	public Fly(Vector2 position, Vector2 size, double speed, double life, int damage, double projectilRange, double projectilDamage, double projectilSpeed) {
		super(position, size, speed, life);
		this.imagePath = ImagePaths.FLY;
		this.damage = damage;
		this.projectilDamage = projectilDamage;
		this.projectilRange = projectilRange;
		this.projectilSpeed = projectilSpeed;
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

	public double getProjectilDamage() {
		return projectilDamage;
	}

	public void setProjectilDamage(double projectilDamage) {
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
	
}
