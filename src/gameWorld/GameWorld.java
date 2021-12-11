package gameWorld;

import gameobjects.Hero;
import libraries.StdDraw;
import libraries.Vector2;
import resources.Controls;

public class GameWorld
{
	private Room currentRoom;
	private Hero hero;

	// A world needs a hero
	public GameWorld(Hero hero)
	{
		this.hero = hero;
		MonstersRoom monsterTestRoom = new MonstersRoom(hero, null, null, null, null, 10);
		Door spawnDoor = new Door(monsterTestRoom, true);
		currentRoom = new Spawn(hero, null, spawnDoor, null, null);
	}

	public void processUserInput()
	{
		processKeysForMovement();
	}

	public boolean gameOver()
	{
		return false;
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
		if(currentRoom.heroHitUpDoor()){currentRoom = currentRoom.getUpDoor().getNextRoom();}
		if(currentRoom.heroHitDownDoor()){currentRoom = currentRoom.getDownDoor().getNextRoom();}
		if(currentRoom.heroHitLeftDoor()){currentRoom = currentRoom.getLeftDoor().getNextRoom();}
		if(currentRoom.heroHitRightDoor()){currentRoom = currentRoom.getRightDoor().getNextRoom();}
	}

}
