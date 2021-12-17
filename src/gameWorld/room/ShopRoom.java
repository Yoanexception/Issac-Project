package gameWorld.room;

import gameWorld.Door;
import gameobjects.Hero;
import gameobjects.items.Item;
import libraries.Physics;
import libraries.StdDraw;
import libraries.Vector2;
import resources.Items;

import java.util.ArrayList;

/**
 * The type Shop room.
 */
public class ShopRoom extends Room {
	
	private ArrayList<Item> item;

	/**
	 * Instantiates a new Shop room.
	 *
	 * @param hero      the hero
	 * @param leftRoom  the left room
	 * @param rightRoom the right room
	 * @param upRoom    the up room
	 * @param downRoom  the down room
	 */
	public ShopRoom(Hero hero, Door leftRoom, Door rightRoom, Door upRoom, Door downRoom) {
		super(hero, leftRoom, rightRoom, upRoom, downRoom);
		this.item = new ArrayList<>();
		generateItem();
	}

	/**
	 * Permet de generer les items a vendre de la salle.
	 */
	public void generateItem() {
		ArrayList<Item> items = Items.getItems();
		for(int i = 0; i < 3; i++){
			int randomItems = (int) (Math.random() * items.size());
			if(!item.contains(items.get(randomItems))){
				Item newItem = items.get(randomItems);
				item.add(newItem);
				Vector2 position = new Vector2();
				switch (i) {
					case 0 -> {
						double x = 0.3;
						double y = 0.5;
						position = new Vector2(x, y);
					}
					case 1 -> {
						double x = 0.7;
						double y = 0.5;
						position = new Vector2(x, y);
					}
					case 2 -> {
						double x = 0.5;
						double y = 0.7;
						position = new Vector2(x, y);
					}
				}
				item.get(i).setPosition(position);
			} else {
				i--;
			}
		}
	}

	public void updateRoom(){
		super.updateRoom();
		itemTouch(super.getHero());
	}

	/**
	 * Regarde si le hero à toucher un item et lui ajoute les differentes caractéristique de l'item.
	 *
	 * @param h Le hero
	 */
	public void itemTouch(Hero h){
		ArrayList<Item> toRemove = new ArrayList<>();
		for(Item i : item){
			if(Physics.rectangleCollision(h.getPosition(), h.getSize(), i.getPosition(), i.getSize()) && h.getGold() >= 15
			){
				h.setLifeMax(h.getLifeMax() + i.getAddLifeMax());
				if(h.getLife() < h.getLife() - i.getHealLife()){
					h.setLife(h.getLife() + i.getHealLife());
				} else {
					h.setLife(h.getLifeMax());
				}
				h.setDamageAttack(h.getDamageAttack() + i.getAddDamage());
				if(i.isHeal()){
					h.setLife(h.getLifeMax());
				}
				h.setSpeed(h.getSpeed() + i.getAddSpeed());

				h.setGold(h.getGold() - 15);
				toRemove.add(i);
			}
		}
		item.removeAll(toRemove);
	}


	public void drawRoom(){
		super.drawRoom();
		for(Item i : item){
			StdDraw.picture(i.getPosition().getX(), i.getPosition().getY(), i.getImagePath(), i.getSize().getX(),i.getSize().getY());
		}
	}

}
