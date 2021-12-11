package gameobjects;

import libraries.Vector2;

public class Monster {
	
	private Vector2 position;
	private Vector2 size;
	private double speed;
	private double life;
	
	public Monster(Vector2 position, Vector2 size, double speed, double life) {
		this.position = position;
		this.size = size;
		this.speed = speed;
		this.life = life;
	}

	public boolean isHit(Larme l){
		if(
				l.getPosition().getX() > position.getX() - size.getX() / 2
				&& l.getPosition().getX() < position.getX() + size.getX() / 2
				&& l.getPosition().getY() > position.getY() - size.getY() / 2
				&& l.getPosition().getY() < position.getY() + size.getY() / 2
		) {
			life -= 1;
			return true;
		}
		return false;
	}

	public boolean isDead () { return life == 0; }

	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public Vector2 getSize() {
		return size;
	}

	public void setSize(Vector2 size) {
		this.size = size;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getLife() {
		return life;
	}

	public void setLife(double life) {
		this.life = life;
	}

	public String getImagePath() {
		return "";
	}


}
