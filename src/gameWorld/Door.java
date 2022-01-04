package gameWorld;

import gameWorld.room.Room;

/**
 * The type Door.
 */
public class Door {

	private Room nextRoom;
	private boolean open;
	private boolean visible;
	private int type;

	/**
	 * Instantiates a new Door.
	 *
	 * @param nextRoom the next room
	 * @param open     is the door open
	 * @param type 	   The type of the port (0 : MonsterRoom ou Spawn, 1 : ShopRoom, 2 : BossRoom)
	 */
	public Door(Room nextRoom, boolean open, int type) {
		this.nextRoom = nextRoom;
		this.open = open;
		this.visible = false;
		this.type = type;
	}

	/**
	 * Instantiates a new Door.
	 *
	 * @param nextRoom the next room
	 * @param open     is the door open
	 * @param visible  is the door visible
	 */
	public Door(Room nextRoom, boolean open, boolean visible, int type){
		this.nextRoom = nextRoom;
		this.open = open;
		this.visible = visible;
		this.type = type;
	}

	/**
	 * Gets next room.
	 *
	 * @return the next room
	 */
	public Room getNextRoom() {
		return nextRoom;
	}

	/**
	 * Sets next room.
	 *
	 * @param nextRoom the next room
	 */
	public void setNextRoom(Room nextRoom) {
		this.nextRoom = nextRoom;
	}

	/**
	 * Is open boolean.
	 *
	 * @return the boolean
	 */
	public boolean isOpen() {
		return open;
	}

	/**
	 * Sets open.
	 *
	 * @param open the open
	 */
	public void setOpen(boolean open) {
		this.open = open;
	}

	/**
	 * Sets visible.
	 *
	 * @param visible the visible
	 */
	public void setVisible(boolean visible) { this.visible = visible; }

	/**
	 * Is visible boolean.
	 *
	 * @return the boolean
	 */
	public boolean isVisible(){ return this.visible; }

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
