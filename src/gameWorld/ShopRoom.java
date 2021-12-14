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
			System.out.println(randomItems);
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


	public void drawRoom(){
		super.drawRoom();
		for(Item i : item){
			StdDraw.picture(i.getPosition().getX(), i.getPosition().getY(), i.getImagePath(), 0.075,0.075);
		}
	}

}
