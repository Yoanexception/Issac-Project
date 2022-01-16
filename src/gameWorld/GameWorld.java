package gameWorld;

import gameWorld.room.*;
import gameobjects.Hero;
import libraries.MusicController;
import libraries.StdDraw;
import libraries.Vector2;
import resources.Controls;
import resources.MusicPaths;
import resources.RoomInfos;

import java.awt.*;

public class GameWorld
{
	private Room currentRoom;
	private Hero hero;
	private Floor currentFloor;

	private int transitionFloor;
	private int lastTransitionAlpha;

	private Thread music;

	// A world needs a hero
	public GameWorld(Hero hero, int nbFloor)
	{
		this.hero = hero;
		currentFloor = FloorGenerator.generateDonjon(hero, nbFloor);
		currentRoom = currentFloor.getCurrentRoom();
		music = new MusicController(MusicPaths.MONSTER_MUSIC);
		transitionFloor = 0;
		lastTransitionAlpha = 0;
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

	public boolean isGameWin() {
		if(currentRoom.isGoInHole(hero)){
			music.stop();
			if(currentFloor.getNextFloor() == null){
				return true;
			} else {
				transitionFloor = 17;
				hero.setPosition(new Vector2(0.5,0.5));
				currentFloor = currentFloor.getNextFloor();
				currentRoom = currentFloor.getCurrentRoom();
			}
		}
		return false;
	}

	public void updateGameObjects()
	{
		verifyMusic();
		if(transitionFloor != 0){
			transitionFloor -= 1;
			return;
		}
		currentRoom.updateRoom();
	}

	public void drawGameObjects()
	{
		if(transitionFloor != 0){
			System.out.println(lastTransitionAlpha);
			StdDraw.clear();
			if(transitionFloor > 8){
				if(transitionFloor == 17){
					lastTransitionAlpha = 50;
				}
				lastTransitionAlpha += 25;
			} else {
				lastTransitionAlpha -= 25;
			}
			currentRoom.drawRoom();
			Color c = new Color(0,0,0, lastTransitionAlpha);
			StdDraw.setPenColor(c);
			StdDraw.filledRectangle(0.5,0.5,0.5,0.5);
			return;
		}
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
		if(StdDraw.isKeyPressed(Controls.isInvincible)){
			hero.setInvincible(true);
		}
		if(StdDraw.isKeyPressed(Controls.superSpeed)){
			hero.setSpeed(0.03);
		}
		if(StdDraw.isKeyPressed(Controls.superDamage)){
			hero.setDamageAttack(1000);
		}
		if(StdDraw.isKeyPressed(Controls.bombs)){
			hero.bomb();
		}
	}

	public void ifHeroHitDoor(){
		Vector2 newPosition = new Vector2(0.5,0.5);
		if(currentRoom.heroHitUpDoor()){
			setRoomMusic(currentRoom.getUpDoor());
			currentRoom = currentRoom.getUpDoor().getNextRoom();
			hero.setPosition(newPosition);
		}
		if(currentRoom.heroHitDownDoor()){
			setRoomMusic(currentRoom.getDownDoor());
			currentRoom = currentRoom.getDownDoor().getNextRoom();
			hero.setPosition(newPosition);
		}
		if(currentRoom.heroHitLeftDoor()){
			setRoomMusic(currentRoom.getLeftDoor());
			currentRoom = currentRoom.getLeftDoor().getNextRoom();
			hero.setPosition(newPosition);

		}
		if(currentRoom.heroHitRightDoor()){
			setRoomMusic(currentRoom.getRightDoor());
			currentRoom = currentRoom.getRightDoor().getNextRoom();
			hero.setPosition(newPosition);
		}
	}

	private void setRoomMusic(Door door){
		if(currentRoom.getClass().getSimpleName().equals(getDoorRoomName(door))){

		} else if(getDoorRoomName(door).equals("MonsterRoom")){
			music.stop();
			music = new MusicController(MusicPaths.MONSTER_MUSIC);
			music.start();
		} else if(getDoorRoomName(door).equals("ShopRoom")){
			music.stop();
			music = new MusicController(MusicPaths.STORE_MUSIC);
			music.start();
		} else if(getDoorRoomName(door).equals("BossRoom")){
			music.stop();
			music = new MusicController(MusicPaths.BOSS_MUSIC);
			music.start();
		}
	}

	private String getDoorRoomName(Door d){
		return switch (d.getType()){
			case 0 -> "MonsterRoom";
			case 1 -> "ShopRoom";
			case 2 -> "BossRoom";
			default -> "";
		};
	}

	private void verifyMusic(){
		if(currentRoom.getClass().getSimpleName().equals("MonsterRoom")){
			if(music.getState().equals(Thread.State.TERMINATED)){
				music = new MusicController(MusicPaths.MONSTER_MUSIC);
				music.start();
			}
		} else if(currentRoom.getClass().getSimpleName().equals("ShopRoom")){
			if(music.getState().equals(Thread.State.TERMINATED)){
				music = new MusicController(MusicPaths.STORE_MUSIC);
				music.start();
			}
		} else if(currentRoom.getClass().getSimpleName().equals("BossRoom")){
			if(music.getState().equals(Thread.State.TERMINATED)){
				music = new MusicController(MusicPaths.BOSS_MUSIC);
				music.start();
			}
		}
	}

	/**
	 * Convert a tile index to a 0-1 position.
	 *
	 * @param indexX
	 * @param indexY
	 * @return
	 */
	private static Vector2 positionFromTileIndex(int indexX, int indexY)
	{
		return new Vector2(indexX * RoomInfos.TILE_WIDTH + RoomInfos.HALF_TILE_SIZE.getX(),
				indexY * RoomInfos.TILE_HEIGHT + RoomInfos.HALF_TILE_SIZE.getY());
	}

}
