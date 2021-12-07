package gameWorld;

import gameobjects.Boss;
import gameobjects.Hero;

public class BossRoom extends Room{
	
	private Boss boss;

	public BossRoom(Hero hero, Door leftRoom, Door rightRoom, Door upRoom, Door downRoom) {
		super(hero, leftRoom, rightRoom, upRoom, downRoom);
		generateBoss();
	}

	
	public void generateBoss() {
		
	}
	
	
}
