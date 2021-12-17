package gameWorld.room;

import gameWorld.Door;
import gameWorld.room.Room;
import gameobjects.Hero;

/**
 * The type Spawn room.
 */
public class SpawnRoom extends Room {

	/**
	 * Instantiates a new Spawn room.
	 *
	 * @param hero      the hero
	 * @param leftRoom  the left room
	 * @param rightRoom the right room
	 * @param upRoom    the up room
	 * @param downRoom  the down room
	 */
	public SpawnRoom(Hero hero, Door leftRoom, Door rightRoom, Door upRoom, Door downRoom) {
		super(hero, leftRoom, rightRoom, upRoom, downRoom);
		// TODO Auto-generated constructor stub
	}

}
