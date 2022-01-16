package gameWorld.room;

import gameWorld.Door;
import gameobjects.Bomb;
import gameobjects.Hero;
import gameobjects.Larme;
import gameobjects.obstacles.Obstacles;
import libraries.Physics;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.RoomInfos;

import java.util.ArrayList;

/**
 * The type Room.
 */
public class Room
{
	private Hero hero;
	private Door leftDoor;
	private Door rightDoor;
	private Door upDoor;
	private Door downDoor;
	private static ArrayList<Larme> larme = new ArrayList<Larme>();
	private static ArrayList<Bomb> bombs = new ArrayList<>();

	/**
	 * Instantiates a new Room.
	 *
	 * @param hero      the hero
	 * @param leftDoor  the left door
	 * @param rightDoor the right door
	 * @param upDoor    the up door
	 * @param downDoor  the down door
	 */
	public Room(Hero hero, Door leftDoor, Door rightDoor, Door upDoor, Door downDoor)
	{
		this.hero = hero;
		this.downDoor = downDoor;
		this.leftDoor = leftDoor;
		this.rightDoor = rightDoor;
		this.upDoor = upDoor;
	}

	/*
	 * Make every entity that compose a room process one step
	 */
	public void updateRoom()
	{
		makeHeroPlay(new ArrayList<Obstacles>());
		makeLarmePlay();
		makeBombPlay();
	}

	/**
	 * Update room.
	 *
	 * @param o La liste des obsacles de la salle
	 */
	public void updateRoom(ArrayList<Obstacles> o)
	{
		makeHeroPlay(o);
		makeLarmePlay();
		makeBombPlay();
	}


	private void makeHeroPlay(ArrayList<Obstacles> o)
	{
		hero.updateGameObject(larme, o);
	}

	/**
	 * Met a jour les larmes.
	 */
	private void makeLarmePlay() {
		ArrayList<Larme> toRemove = new ArrayList<>();
		for(Larme l : larme){
			l.updateGameObject();
			if(l.getDead() || hitObstacles(l)){
				toRemove.add(l);
			}
		}
		larme.removeAll(toRemove);
	}

	private void makeBombPlay() {
		ArrayList<Bomb> toRemove = new ArrayList<>();
		for(Bomb b : bombs){
			b.updateGameObject();
			if(b.getTimeExplosion() <= 0){
				explodeBomb(b);
				toRemove.add(b);
			}
		}
		bombs.removeAll(toRemove);
	}

	public void explodeBomb(Bomb b) {
		if (Physics.CircleToPointCollision(hero.getPosition(), b.getPosition(), b.getRange(), hero.getSize()) && !hero.isInvincible()){
			hero.setLife(hero.getLife() - b.getDamage());
		}
	}

	/**
	 * Drawing
	 */
	public void drawRoom()
	{
		// For every tile, set background colors
		for (int i = 0; i < RoomInfos.NB_TILES; i++)
		{
			for (int j = 0; j < RoomInfos.NB_TILES; j++)
			{
				Vector2 position = positionFromTileIndex(i, j);
				if(!(j == 0 || i == 0 || j == RoomInfos.NB_TILES - 1 || i == RoomInfos.NB_TILES - 1)) {
					StdDraw.setPenColor(StdDraw.BROWN);
					StdDraw.filledRectangle(position.getX(), position.getY(), RoomInfos.HALF_TILE_SIZE.getX(),
							RoomInfos.HALF_TILE_SIZE.getY());
				} else {
					StdDraw.picture(position.getX(), position.getY(), ImagePaths.BRICK_WALL, RoomInfos.TILE_SIZE.getX(), RoomInfos.TILE_SIZE.getY());
				}
			}
		}
		drawDoor();
		drawLarme();
		drawBomb();
		drawObstacles();
		hero.drawGameObject();
		showUHDHero();
	}

	/**
	 * Draw obstacles.
	 */
	public void drawObstacles() {}

	/**
	 * Draw larme.
	 */
	public void drawLarme(){
		for(Larme l : larme){
			StdDraw.picture(l.getPosition().getX(), l.getPosition().getY(), l.getImagePath(), l.getSize().getX(),l.getSize().getY());
		}
	}

	public void drawBomb(){
		for(Bomb b : bombs){
			StdDraw.picture(b.getPosition().getX(), b.getPosition().getY(), b.getImagePath(), b.getSize().getX(), b.getSize().getY());
		}
	}

	/**
	 * Draw door.
	 */
	public void drawDoor(){
		if(downDoor != null) {
			Vector2 positionDownDoor = positionFromTileIndex(RoomInfos.NB_TILES / 2, 0);
			String doorOpen = getOpenDoorPath(downDoor);
			String doorClosed = getClosedDoorPath(downDoor);
			StdDraw.picture(positionDownDoor.getX(), positionDownDoor.getY(),  downDoor.isOpen() ? doorOpen : doorClosed, 180);
		}
		if(upDoor != null) {
			String doorOpen = getOpenDoorPath(upDoor);
			String doorClosed = getClosedDoorPath(upDoor);
			Vector2 positionUpDoor = positionFromTileIndex(RoomInfos.NB_TILES / 2, RoomInfos.NB_TILES - 1);
			StdDraw.picture(positionUpDoor.getX(), positionUpDoor.getY(), upDoor.isOpen() ? doorOpen : doorClosed);
		}
		if(leftDoor != null) {
			String doorOpen = getOpenDoorPath(leftDoor);
			String doorClosed = getClosedDoorPath(leftDoor);
			Vector2 positionLeftDoor = positionFromTileIndex(0, RoomInfos.NB_TILES / 2);
			StdDraw.picture(positionLeftDoor.getX(), positionLeftDoor.getY(), leftDoor.isOpen() ? doorOpen : doorClosed, 90);
		}
		if(rightDoor != null) {
			String doorOpen = getOpenDoorPath(rightDoor);
			String doorClosed = getClosedDoorPath(rightDoor);
			Vector2 positionRightDoor = positionFromTileIndex(RoomInfos.NB_TILES - 1, RoomInfos.NB_TILES / 2);
			StdDraw.picture(positionRightDoor.getX(), positionRightDoor.getY(), rightDoor.isOpen() ? doorOpen : doorClosed, 270);
		}
	}

	private String getOpenDoorPath(Door d) {
		return switch (d.getType()){
			case 1 -> ImagePaths.SHOP_OPENED_DOOR;
			case 2 -> ImagePaths.BOSS_OPENED_DOOR;
			default -> ImagePaths.OPENED_DOOR;
		};
	}

	private String getClosedDoorPath(Door d) {
		return switch (d.getType()){
			case 1 -> ImagePaths.SHOP_ClOSED_DOOR;
			case 2 -> ImagePaths.BOSS_ClOSED_DOOR;
			default -> ImagePaths.CLOSED_DOOR;
		};
	}

	/**
	 * Show uhd hero.
	 */
	public void showUHDHero(){
		double x = 0.15;
		for(int i = 0; i < hero.getLifeMax(); i+= 2){
			StdDraw.picture(x, 0.85,ImagePaths.EMPTY_HEART_HUD, 0.05, 0.05);
			x += 0.05;
		}
		x = 0.15;
		for(int i = 1; i <= hero.getLife(); i++){
			if(i%2 == 0){
				StdDraw.picture(x, 0.85,ImagePaths.HEART_HUD, 0.05, 0.05);
				x += 0.05;
			} else {
				StdDraw.picture(x, 0.85,ImagePaths.HALF_HEART_HUD, 0.05, 0.05);
			}
		}
		//Show the number of coin
		StdDraw.picture(0.15, 0.80, ImagePaths.COIN);
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.text(0.205, 0.80, "" + hero.getGold());
		//Show the number of key
		StdDraw.picture(0.15, 0.75, ImagePaths.KEY);
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.text(0.205, 0.75, "" + hero.getKeys());
		// Show the number of bomb
		StdDraw.picture(0.15, 0.70, ImagePaths.BOMB);
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.text(0.205, 0.70, "" + hero.getBomb());
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

	/**
	 * Renvoie si le hero a toucher la porte du haut
	 *
	 * @return the boolean
	 */
	public boolean heroHitUpDoor() {
		Vector2 positionUpDoor = positionFromTileIndex(RoomInfos.NB_TILES / 2, RoomInfos.NB_TILES - 1);
		if (hero.getPosition().getY() > positionUpDoor.getY() - 0.06
				&& hero.getPosition().getX() > positionUpDoor.getX() - 0.04
				&& hero.getPosition().getX() < positionUpDoor.getX() + 0.04) {
			if(upDoor == null) return false;
			if(upDoor.isKeyLocked() && !upDoor.isOpen()){
				if(hero.getKeys() >= 1){
					upDoor.setOpen(true);
					upDoor.setKeyLocked(false);
					hero.setKeys(hero.getKeys() - 1);
					return true;
				}
				return false;
			}
			return upDoor.isOpen();
		}
		return false;
	}

	/**
	 * Renvoie si le hero a toucher la porte du bas
	 *
	 * @return the boolean
	 */
	public boolean heroHitDownDoor() {
		Vector2 positionDownDoor = positionFromTileIndex(RoomInfos.NB_TILES / 2, 0);
		if (hero.getPosition().getY() < positionDownDoor.getY() + 0.085
				&& hero.getPosition().getX() > positionDownDoor.getX() - 0.04
				&& hero.getPosition().getX() < positionDownDoor.getX() + 0.04) {
			if(downDoor == null) return false;
			if(downDoor.isKeyLocked() && !downDoor.isOpen()){
				if(hero.getKeys() >= 1){
					downDoor.setOpen(true);
					downDoor.setKeyLocked(true);
					hero.setKeys(hero.getKeys() - 1);
					return true;
				}
			}
			return downDoor.isOpen();
		}
		return false;
	}

	/**
	 * Renvoie si le hero a toucher la porte de gauche
	 *
	 * @return the boolean
	 */
	public boolean heroHitLeftDoor() {
		Vector2 positionLeftDoor = positionFromTileIndex(0, RoomInfos.NB_TILES / 2);
		if (hero.getPosition().getY() < positionLeftDoor.getY() + 0.04
				&& hero.getPosition().getY() > positionLeftDoor.getY() - 0.04
				&& hero.getPosition().getX() < positionLeftDoor.getX() + 0.085) {
			if(leftDoor == null) return false;
			if(leftDoor.isKeyLocked() && !leftDoor.isOpen()){
				if(hero.getKeys() >= 1){
					leftDoor.setOpen(true);
					leftDoor.setKeyLocked(true);
					hero.setKeys(hero.getKeys() - 1);
					return true;
				}
			}
			return leftDoor.isOpen();
		}
		return false;
	}

	/**
	 * Renvoie si le hero a toucher la porte de droite
	 *
	 * @return the boolean
	 */
	public boolean heroHitRightDoor() {
		Vector2 positionRightDoor = positionFromTileIndex(RoomInfos.NB_TILES - 1, RoomInfos.NB_TILES / 2);
		if (hero.getPosition().getY() < positionRightDoor.getY() + 0.04
				&& hero.getPosition().getY() > positionRightDoor.getY() - 0.04
				&& hero.getPosition().getX() > positionRightDoor.getX()  - 0.085) {
			if(rightDoor == null) return false;
			if(rightDoor.isKeyLocked() && !rightDoor.isOpen()){
				if(hero.getKeys() >= 1){
					rightDoor.setOpen(true);
					rightDoor.setKeyLocked(true);
					hero.setKeys(hero.getKeys() - 1);
					return true;
				}
			}
			return rightDoor.isOpen();
		}
		return false;
	}

	/**
	 * Add larme.
	 *
	 * @param l La larma a ajouter;
	 */
	public static void addLarme(Larme l){
		larme.add(l);
	}

	/**
	 * Gets larme.
	 *
	 * @return the larme
	 */
	public ArrayList<Larme> getLarme() {return larme;}

	public static void addBomb(Bomb b){ bombs.add(b); }

	public ArrayList<Bomb> getBomb() { return bombs; }

	public static void deleteBomb(ArrayList<Bomb> toDelete) { bombs.removeAll(toDelete); }

	/**
	 * Delete larme.
	 *
	 * @param toDelete the to delete
	 */
	public static void deleteLarme(ArrayList<Larme> toDelete){
		larme.removeAll(toDelete);
	}

	/**
	 * Get up door door.
	 *
	 * @return the door
	 */
	public Door getUpDoor(){ return upDoor; }

	/**
	 * Get down door door.
	 *
	 * @return the door
	 */
	public Door getDownDoor(){ return downDoor; }

	/**
	 * Get left door door.
	 *
	 * @return the door
	 */
	public Door getLeftDoor(){ return leftDoor; }

	/**
	 * Get right door door.
	 *
	 * @return the door
	 */
	public Door getRightDoor(){ return rightDoor; }

	/**
	 * Set up door.
	 *
	 * @param upDoor the up door
	 */
	public void setUpDoor(Door upDoor){ this.upDoor = upDoor; }

	/**
	 * Set down door.
	 *
	 * @param downDoor the down door
	 */
	public void setDownDoor(Door downDoor){ this.downDoor = downDoor; }

	/**
	 * Set left door.
	 *
	 * @param leftDoor the left door
	 */
	public void setLeftDoor(Door leftDoor){ this.leftDoor = leftDoor; }

	/**
	 * Set right door.
	 *
	 * @param rightDoor the right door
	 */
	public void setRightDoor(Door rightDoor){ this.rightDoor = rightDoor; }

	/**
	 * Get hero hero.
	 *
	 * @return the hero
	 */
	public Hero getHero(){ return hero; }

	/**
	 * Hit obstacles boolean.
	 *
	 * @param l the l
	 * @return the boolean
	 */
//For Larme
	public boolean hitObstacles(Larme l){ return false; }

	/**
	 * Kill all monster.
	 */
	public void killAllMonster(){}

	/**
	 *	Appele la fonction qui determine si le boss est mort.
	 */
	public boolean isBossDead() { return false; }

	public boolean isGoInHole(Hero h){ return false; }
}
