package gameWorld;

import gameobjects.Hero;
import gameobjects.Item;

public class Shop extends Room{
	
	private Item[] item;

	public Shop(Hero hero, Room leftRoom, Room rightRoom, Room upRoom, Room downRoom, int nbItem) {
		super(hero, leftRoom, rightRoom, upRoom, downRoom);
		this.item = new Item[nbItem];
		generateItem(nbItem);
	}
	
	public void generateItem(int nbItem) {
		
	}
	
}
