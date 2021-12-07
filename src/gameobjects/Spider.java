package gameobjects;

import libraries.Vector2;
import resources.ImagePaths;

public class Spider extends Monster {
	
	private String imagePath;
	private double damage;
	
	public Spider(Vector2 position, Vector2 size, double speed, double life, double damage) {
		super(position, size, speed, life);
		this.imagePath = ImagePaths.SPIDER;
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

}
