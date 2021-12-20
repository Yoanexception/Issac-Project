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

	// A world needs a hero
	public GameWorld(Hero hero)
	{
		this.hero = hero;
		currentRoom = generateLevel(hero, 5);
	}

	/**
	 * Renvoie la salle de spawn d'un etage généré aleatoirement
	 * @param hero Le hero du jeux
	 * @param nbMonstersRoom Le nombre de salle de monstre présent dans l'etage (minimun 2)
	 * @return Une salle de spawn
	 */
	private Room generateLevel(Hero hero, int nbMonstersRoom){
		SpawnRoom spawn = new SpawnRoom(hero, null, null, null, null);
		BossRoom bossRoom = new BossRoom(hero, null, null, null, null);
		ShopRoom shopRoom = new ShopRoom(hero, null, null, null, null);
		ArrayList<MonsterRoom> monstersRoom = new ArrayList<>();
		for(int i = 0; i < nbMonstersRoom; i++){
			int nbMonsters = (int) (2 + Math.random() * 6);
			MonsterRoom monsterRoom = new MonsterRoom(hero, null, null,null,null, nbMonsters);
			monstersRoom.add(monsterRoom);
		}
		Room[][] map = new Room[7][7];
		int x = map.length - 1;
		int y = map[x].length / 2;
		map[x][y] = spawn;
		drawMap(map);
		x -= 1;
		for(Room room : monstersRoom){
			map[x][y] = room;
			boolean roomPlaced = false;
			//On place la prochaine salle
			while(!roomPlaced){
				int proba = new Random().nextInt(4);
				switch (proba) {
					case 0 -> {
						if(y != 0 && map[x][y-1] == null){
							y -= 1;
							roomPlaced = true;
						}
					}
					case 1 -> {
						if(y != map[x].length - 1 && map[x][y+1] == null){
							y += 1;
							roomPlaced = true;
						}
					}
					case 2 -> {
						if(x != 0 && map[x-1][y] == null){
							x -= 1;
							roomPlaced = true;
						}
					}
					case 3 -> {
						if(x < map.length - 2 && map[x+1][y] == null){
							x += 1;
							roomPlaced = true;
						}
					}
				}
			}
			drawMap(map);
		}

		map[x][y] = bossRoom;
		drawMap(map);

		//on place la salle du shop
		boolean shopPlaced = false;

		while(!shopPlaced){
			x = new Random().nextInt(map.length);
			y = new Random().nextInt(map[x].length);

			if(map[x][y] == null && isNextMonsterRoom(x,y,map)){
				map[x][y] = shopRoom;
				shopPlaced = true;
			}
		}

		drawMap(map);

		for (int i = 0; i < map.length; i++) {

			for (int j = 0; j < map[i].length; j++) {
				if(map[i][j] != null){
					boolean doorClose = !(map[i][j].getClass().getSimpleName().equals("MonsterRoom"));
					if(i!= map.length - 1 && map[i + 1][j] != null){
						Door newDoor = new Door(map[i+1][j], doorClose);
						map[i][j].setDownDoor(newDoor);
					}
					if(i != 0 && map[i - 1][j] != null){
						Door newDoor = new Door(map[i-1][j], doorClose);
						map[i][j].setUpDoor(newDoor);
					}
					if(j != map[x].length - 1 && map[i][j + 1] != null){
						Door newDoor = new Door(map[i][j+1], doorClose);
						map[i][j].setRightDoor(newDoor);
					}
					if(j != 0 && map[i][j - 1] != null){
						Door newDoor = new Door(map[i][j-1], doorClose);
						map[i][j].setLeftDoor(newDoor);
					}
				}
			}
		}
		return map[map.length - 1][map[0].length / 2];
	}

	private boolean isNextMonsterRoom(int x, int y, Room[][] map) {
		if(x < map.length - 2 && map[x + 1][y] != null && map[x + 1][y].getClass().getSimpleName().equals("MonsterRoom")){
			return true;
		} else if(x != 0 && map[x - 1][y] != null && map[x - 1][y].getClass().getSimpleName().equals("MonsterRoom")){
			return true;
		} else if(y < map.length - 1 && map[x][y + 1] != null && map[x][y + 1].getClass().getSimpleName().equals("MonsterRoom")){
			return true;
		} else return y != 0 && map[x][y - 1] != null &&  map[x][y - 1].getClass().getSimpleName().equals("MonsterRoom");
	}

	private void drawMap(Room[][] map) {
		for (Room[] rooms : map) {
			for (Room room : rooms) {
				if (room == null) {
					System.out.print(" ");
				} else {
					switch (room.getClass().getSimpleName()) {
						case "SpawnRoom" -> {
							System.out.print("S");
						}
						case "ShopRoom" -> {
							System.out.print("$");
						}
						case "MonsterRoom" -> {
							System.out.print("M");
						}
						case "BossRoom" -> {
							System.out.print("B");
						}
					}
				}
				System.out.print("|");
			}
			System.out.println("");
		}
		System.out.println("");
		System.out.println("");
		System.out.println("");
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

	public boolean isBossDead(){
		return currentRoom.isBossDead();
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
