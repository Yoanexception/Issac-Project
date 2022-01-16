package libraries;

import resources.RoomInfos;

public class Physics
{
	/**
	 * Calculates whether two rectangles are in collision or not. Two rectangles are
	 * in collision if they share any area.
	 * 
	 * @param pos1:  position of first rectangle
	 * @param size1: size of first rectangle
	 * @param pos2:  position of second rectangle
	 * @param size2: size of second rectangle
	 * @return true if rectangles are in collision else false
	 */
	public static boolean rectangleCollision(Vector2 pos1, Vector2 size1, Vector2 pos2, Vector2 size2)
	{
		// We authorise a small overlap before considering a collision in order to avoid
		// collision between side-to-side objects and floating point approximation
		// errors.
		double authorizedOverlap = RoomInfos.TILE_WIDTH / 1000;

		boolean tooFarLeft = pos1.getX() + (size1.getX() / 2) < authorizedOverlap + pos2.getX() - (size2.getX() / 2);
		boolean tooFarBelow = pos1.getY() + (size1.getY() / 2) < authorizedOverlap + pos2.getY() - (size2.getY() / 2);
		boolean tooFarRight = pos1.getX() - (size1.getX() / 2) + authorizedOverlap > pos2.getX() + (size2.getX() / 2);
		boolean tooFarAbove = pos1.getY() - (size1.getY() / 2) + authorizedOverlap > pos2.getY() + (size2.getY() / 2);

		if (tooFarLeft || tooFarRight || tooFarAbove || tooFarBelow)
		{
			return false;
		}
		return true;
	}

	public static boolean CircleToPointCollision(Vector2 pos1, Vector2 pos2, double rayon, Vector2 size1){
		double dx = (pos1.getX() * pos2.getX());
		double dy = (pos1.getY() * pos2.getY());
		double d = dx*dx+dy*dy;
		System.out.println(d);
		return d - size1.getX() < rayon*2;
	}
}
