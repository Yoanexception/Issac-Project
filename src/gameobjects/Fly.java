package gameobjects;

import libraries.Vector2;
import resources.ImagePaths;

public class Fly extends Monster {

	private String imagePath;
	private double damage;
	private double projectilDamage;
	private double projectilSpeed;
	private double projectilLength;
	
	public Fly(Vector2 position, Vector2 size, double speed, double life, double projectilLength, double damage, double projectilDamage, double projectilSpeed) {
		super(position, size, speed, life);
		this.imagePath = ImagePaths.FLY;
		this.damage = damage;
		this.projectilDamage = projectilDamage;
		this.projectilLength = projectilLength;
		this.projectilSpeed = projectilSpeed;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public double getDamage() {
		return damage;
	}

	public void setDamage(double damage) {
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

	public double getProjectilLength() {
		return projectilLength;
	}

	public void setProjectilLength(double projectilLength) {
		this.projectilLength = projectilLength;
	}
	
}
