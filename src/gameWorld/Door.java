package gameWorld;

public class Door {

	private Room nextRoom;
	private boolean open;
	
	public Door(Room nextRoom, boolean open) {
		this.nextRoom = nextRoom;
		this.open = open;
	}

	public Room getNextRoom() {
		return nextRoom;
	}

	public void setNextRoom(Room nextRoom) {
		this.nextRoom = nextRoom;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}
	
}
