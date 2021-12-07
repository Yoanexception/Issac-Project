package gameWorld;

import gameobjects.Fly;
import gameobjects.Hero;
import gameobjects.Monster;

public class MonstersRoom extends Room {
	
	private Monster[] monster;

	public MonstersRoom(Hero hero, Room leftRoom, Room rightRoom, Room upRoom, Room downRoom, int nbMonster) {
		super(hero, leftRoom, rightRoom, upRoom, downRoom);
		this.monster = new Monster[nbMonster];
		generateMonster(nbMonster);
	}
	
	/**
	 * Elle genere les monstre present dans la salle.
	 * @param nbMonster nombre de monstre voulue dans cette salle
	 */
	public void generateMonster(int nbMonster) {
		
	}

}
