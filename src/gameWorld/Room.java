package gameWorld;

import gameobjects.Hero;
import gameobjects.Larme;
import libraries.StdDraw;
import libraries.Vector2;
import resources.HeroInfos;
import resources.ImagePaths;
import resources.RoomInfos;

import java.util.ArrayList;
import java.util.List;

public class Room
{
	private Hero hero;
	private Door leftDoor;
	private Door rightDoor;
	private Door upDoor;
	private Door downDoor;
	private static ArrayList<Larme> larme = new ArrayList<Larme>();

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
		makeHeroPlay();
		makeLarmePlay();
	}


	private void makeHeroPlay()
	{
		hero.updateGameObject();
	}

	private void makeLarmePlay() {
		ArrayList<Larme> toRemove = new ArrayList<Larme>();
		for(Larme l : larme){
			l.updateGameObject();
			if(l.getDead()){
				toRemove.add(l);
			}
		}
		larme.removeAll(toRemove);
	}

	/*
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
					StdDraw.setPenColor(StdDraw.DARK_BROWN);
					StdDraw.filledRectangle(position.getX(), position.getY(), RoomInfos.HALF_TILE_SIZE.getX(),
							RoomInfos.HALF_TILE_SIZE.getY());
				}
			}
		}
		drawDoor();
		drawLarme();
		hero.drawGameObject();
		showLifeHero();
	}

	public void drawLarme(){
		for(Larme l : larme){
			StdDraw.picture(l.getPosition().getX(), l.getPosition().getY(), l.getImagePath(), 0.05,0.05);
		}
	}

	public void drawDoor(){
		String doorOpen = ImagePaths.OPENED_DOOR;
		String doorClosed = ImagePaths.CLOSED_DOOR;
		if(downDoor != null) {
			Vector2 positionDownDoor = positionFromTileIndex(RoomInfos.NB_TILES / 2, 0);
			StdDraw.picture(positionDownDoor.getX(), positionDownDoor.getY(),  downDoor.isOpen() ? doorOpen : doorClosed);
		}
		if(upDoor != null) {
			Vector2 positionUpDoor = positionFromTileIndex(RoomInfos.NB_TILES / 2, RoomInfos.NB_TILES - 1);
			StdDraw.picture(positionUpDoor.getX(), positionUpDoor.getY(), upDoor.isOpen() ? doorOpen : doorClosed);
		}
		if(leftDoor != null) {
			Vector2 positionLeftDoor = positionFromTileIndex(0, RoomInfos.NB_TILES / 2);
			StdDraw.picture(positionLeftDoor.getX(), positionLeftDoor.getY(), leftDoor.isOpen() ? doorOpen : doorClosed);
		}
		if(rightDoor != null) {
			Vector2 positionRightDoor = positionFromTileIndex(RoomInfos.NB_TILES - 1, RoomInfos.NB_TILES / 2);
			StdDraw.picture(positionRightDoor.getX(), positionRightDoor.getY(), rightDoor.isOpen() ? doorOpen : doorClosed);
		}
	}

	public void showLifeHero(){
		double x = 0.15;
		for(int i = 0; i < HeroInfos.ISSAC_LIFE; i+= 2){
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

	public boolean heroHitUpDoor() {
		Vector2 positionUpDoor = positionFromTileIndex(RoomInfos.NB_TILES / 2, RoomInfos.NB_TILES - 1);
		if (hero.getPosition().getY() > positionUpDoor.getY() - 0.06
				&& hero.getPosition().getX() > positionUpDoor.getX() - 0.04
				&& hero.getPosition().getX() < positionUpDoor.getX() + 0.04) {
			if (upDoor != null && upDoor.isOpen()) {
				return true;
			}
		}
		return false;
	}

	public boolean heroHitDownDoor() {
		Vector2 positionDownDoor = positionFromTileIndex(RoomInfos.NB_TILES / 2, 0);
		if (hero.getPosition().getY() < positionDownDoor.getY() + 0.085
				&& hero.getPosition().getX() > positionDownDoor.getX() - 0.04
				&& hero.getPosition().getX() < positionDownDoor.getX() + 0.04) {
			if (downDoor != null && downDoor.isOpen()) {
				return true;
			}
		}
		return false;
	}

	public boolean heroHitLeftDoor() {
		Vector2 positionLeftDoor = positionFromTileIndex(0, RoomInfos.NB_TILES / 2);
		if (hero.getPosition().getY() < positionLeftDoor.getY() + 0.04
				&& hero.getPosition().getY() > positionLeftDoor.getY() - 0.04
				&& hero.getPosition().getX() < positionLeftDoor.getX()  - 0.06) {
			if (leftDoor != null && leftDoor.isOpen()) {
				return true;
			}
		}
		return false;
	}

	public boolean heroHitRightDoor() {
		Vector2 positionRightDoor = positionFromTileIndex(RoomInfos.NB_TILES - 1, RoomInfos.NB_TILES / 2);
		if (hero.getPosition().getY() < positionRightDoor.getY() + 0.04
				&& hero.getPosition().getY() > positionRightDoor.getY() - 0.04
				&& hero.getPosition().getX() > positionRightDoor.getX()  - 0.085) {
			if (rightDoor != null && rightDoor.isOpen()) {
				return true;
			}
		}
		return false;
	}

	public static void addLarme(Larme l){
		larme.add(l);
	}
	public ArrayList<Larme> getLarme() {return larme;}

	public void deleteLarme(ArrayList<Larme> toDelete){
		larme.removeAll(toDelete);
	}

	public Door getUpDoor(){ return upDoor; }
	public Door getDownDoor(){ return downDoor; }
	public Door getLeftDoor(){ return leftDoor; }
	public Door getRightDoor(){ return rightDoor; }

	public void setUpDoor(Door upDoor){ this.upDoor = upDoor; }
	public void setDownDoor(Door downDoor){ this.downDoor = downDoor; }
	public void setLeftDoor(Door leftDoor){ this.leftDoor = leftDoor; }
	public void setRightDoor(Door rightDoor){ this.rightDoor = rightDoor; }
}
