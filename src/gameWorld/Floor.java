package gameWorld;

import gameWorld.room.Room;
import gameobjects.Hero;

public class Floor {

    private Hero hero;
    private Room currentRoom;
    private Floor previousFloor;
    private Floor nextFloor;

    public Floor(Hero hero, Room currentRoom){
        this.hero = hero;
        this.currentRoom = currentRoom;
        this.nextFloor = null;
        this.previousFloor = null;
    }

    public Floor(Hero hero, Room currentRoom, Floor nextFloor, Floor previousFloor){
        this.hero = hero;
        this.currentRoom = currentRoom;
        this.nextFloor = nextFloor;
        this.previousFloor = previousFloor;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public Floor getNextFloor() {
        return nextFloor;
    }

    public void setNextFloor(Floor nextFloor) {
        this.nextFloor = nextFloor;
    }

    public Floor getPreviousFloor() {
        return previousFloor;
    }

    public void setPreviousFloor(Floor previousFloor) {
        this.previousFloor = previousFloor;
    }
}
