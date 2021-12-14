package gameWorld;

import gameobjects.Hero;
import gameobjects.Item;
import libraries.StdDraw;
import libraries.Vector2;
import resources.Items;

import javax.swing.text.Position;
import java.util.ArrayList;

public class ShopRoom extends Room{
	
	private ArrayList<Item> item;

	public ShopRoom(Hero hero, Door leftRoom, Door rightRoom, Door upRoom, Door downRoom) {
		super(hero, leftRoom, rightRoom, upRoom, downRoom);
		this.item = new ArrayList<>();
		generateItem();
	}
	
	public void generateItem() {
		ArrayList<Item> items = Items.getItems();
		for(int i = 0; i < 3; i++){
			int randomItems = (int) (Math.random() * items.size());
			if(!item.contains(items.get(randomItems))){
				Item newItem = items.get(randomItems);
				item.add(newItem);
				Vector2 position = new Vector2();
				switch (i) {
					case 0 : {
						double x = 0.3;
						double y = 0.5;
						position = new Vector2(x,y);
					}
					break;
					case 1 : {
						double x = 0.7;
						double y = 0.5;
						position = new Vector2(x,y);
					}
					break;
					case 2 : {
						double x = 0.5;
						double y = 0.7;
						position = new Vector2(x,y);
					}
					break;
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

	public void itemTouch(Hero h){
		System.out.println("Je cherche");
		ArrayList<Item> toRemove = new ArrayList<>();
		for(Item i : item){
			if(h.getPosition().getX() > i.getPosition().getX() - 0.075 / 2
					&& h.getPosition().getX() < i.getPosition().getX() + 0.075 / 2
					&& h.getPosition().getY() > i.getPosition().getY() - 0.075 / 2
					&& h.getPosition().getY() < i.getPosition().getY() + 0.075 / 2
					&& h.getGold() >= 15
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

				h.setGold(h.getGold() - 15);
				toRemove.add(i);
			}
		}
		item.removeAll(toRemove);
	}


	public void drawRoom(){
		super.drawRoom();
		for(Item i : item){
			StdDraw.picture(i.getPosition().getX(), i.getPosition().getY(), i.getImagePath(), 0.075,0.075);
		}
	}

}
