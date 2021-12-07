package gameWorld;

import gameobjects.Hero;
import gameobjects.Item;

public class Shop extends Room{
	
	private Item[] item;

	public Shop(Hero hero, Door leftRoom, Door rightRoom, Door upRoom, Door downRoom, int nbItem) {
		super(hero, leftRoom, rightRoom, upRoom, downRoom);
		this.item = new Item[nbItem];
		generateItem(nbItem);
	}
	
	public void generateItem(int nbItem) {
		
	}
	
}
