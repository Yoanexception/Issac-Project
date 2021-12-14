package gameWorld;

import gameobjects.Hero;
import libraries.StdDraw;
import libraries.Vector2;
import resources.Controls;
import resources.ImagePaths;

public class GameWorld
{
	private Room currentRoom;
	private Hero hero;

	// A world needs a hero
	public GameWorld(Hero hero)
	{
		this.hero = hero;
		//MonstersRoom monster2Room = new MonstersRoom(hero, null,null,null,null, 3);
		//Door monstersDoor = new Door(monster2Room, false);
		//MonstersRoom monsterRoom = new MonstersRoom(hero, null, monstersDoor, null, null, 1);
		ShopRoom shop = new ShopRoom(hero, null, null, null, null);
		Door spawnDoor = new Door(shop, true);
		Spawn spawnRoom = new Spawn(hero, null, null, spawnDoor, null);
		currentRoom = spawnRoom;
	}

	public void processUserInput()
	{
		processKeysForMovement();
	}

	public boolean gameOver()
	{
		if(hero.getLife() == 0){
			return true;
		} else {
			return false;
		}
	}

	public void updateGameObjects()
	{
		currentRoom.updateRoom();
	}

	public void drawGameObjects()
	{
		currentRoom.drawRoom();
	}

	/*
	 * Keys processing
	 */

	private void processKeysForMovement()
	{
		if (StdDraw.isKeyPressed(Controls.goUp))
		{
			hero.goUpNext();
		}
		if (StdDraw.isKeyPressed(Controls.goDown))
		{
			hero.goDownNext();
		}
		if (StdDraw.isKeyPressed(Controls.goRight))
		{
			hero.goRightNext();
		}
		if (StdDraw.isKeyPressed(Controls.goLeft))
		{
			hero.goLeftNext();
		}
		if (StdDraw.isKeyPressed(Controls.shootUp)){
			hero.shoot(new Vector2(0, 1));
		}
		if (StdDraw.isKeyPressed(Controls.shootDown)){
			hero.shoot(new Vector2(0,-1));
		}
		if(StdDraw.isKeyPressed(Controls.shootLeft)){
			hero.shoot(new Vector2(-1, 0));
		}
		if(StdDraw.isKeyPressed(Controls.shootRight)){
			hero.shoot(new Vector2(1, 0));
		}
	}

	public void ifHeroHitDoor(){
		Vector2 newPosition = new Vector2(0.5,0.5);
		if(currentRoom.heroHitUpDoor()){
			currentRoom = currentRoom.getUpDoor().getNextRoom();
			hero.setPosition(newPosition);
		}
		if(currentRoom.heroHitDownDoor()){
			currentRoom = currentRoom.getDownDoor().getNextRoom();
			hero.setPosition(newPosition);
		}
		if(currentRoom.heroHitLeftDoor()){
			currentRoom = currentRoom.getLeftDoor().getNextRoom();
			hero.setPosition(newPosition);
			hero.setPosition(newPosition);
		}
		if(currentRoom.heroHitRightDoor()){
			currentRoom = currentRoom.getRightDoor().getNextRoom();
		}
	}

}
