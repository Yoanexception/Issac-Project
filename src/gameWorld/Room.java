package gameWorld;

import gameobjects.Hero;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.RoomInfos;

public class Room
{
	private Hero hero;
	private Door leftDoor;
	private Door rightDoor;
	private Door upDoor;
	private Door downDoor;

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
	}


	private void makeHeroPlay()
	{
		hero.updateGameObject();
	}

	/*
	 * Drawing
	 */
	public void drawRoom()
	{
		// For every tile, set background color.
		StdDraw.setPenColor(StdDraw.DARK_GRAY);
		for (int i = 0; i < RoomInfos.NB_TILES; i++)
		{
			for (int j = 0; j < RoomInfos.NB_TILES; j++)
			{
				Vector2 position = positionFromTileIndex(i, j);
				if(j == 0 || i == 0 || j == RoomInfos.NB_TILES - 1 || i == RoomInfos.NB_TILES - 1) {
					StdDraw.picture(position.getX(), position.getY(), ImagePaths.WALL);
				} else {
					StdDraw.filledRectangle(position.getX(), position.getY(), RoomInfos.HALF_TILE_SIZE.getX(),
							RoomInfos.HALF_TILE_SIZE.getY());
				}
			}
		}
		hero.drawGameObject();
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
