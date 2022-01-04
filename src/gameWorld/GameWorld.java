package gameWorld;

import gameWorld.room.*;
import gameobjects.Hero;
import libraries.StdDraw;
import libraries.Vector2;
import resources.Controls;

import java.util.ArrayList;
import java.util.Random;

public class GameWorld
{
	private Room currentRoom;
	private Hero hero;
	private Floor currentFloor;

	// A world needs a hero
	public GameWorld(Hero hero, int nbFloor)
	{
		this.hero = hero;
		currentFloor = FloorGenerator.generateDonjon(hero, nbFloor);
		currentRoom = currentFloor.getCurrentRoom();
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

	public boolean isGameWin(){
		if(currentRoom.isGoInHole(hero)){
			if(currentFloor.getNextFloor() == null){
				return true;
			} else {
				currentFloor = currentFloor.getNextFloor();
				currentRoom = currentFloor.getCurrentRoom();
			}
		}
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
		if(StdDraw.isKeyPressed(Controls.giveCoin)){
			hero.setGold(hero.getGold() + 10);
		}
		if(StdDraw.isKeyPressed(Controls.killMonster)){
			currentRoom.killAllMonster();
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
		}
		if(currentRoom.heroHitRightDoor()){
			currentRoom = currentRoom.getRightDoor().getNextRoom();
			hero.setPosition(newPosition);
		}
	}

}
