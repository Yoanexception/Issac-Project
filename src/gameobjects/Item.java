package gameobjects;

import libraries.Vector2;

public class Item {

	private Vector2 position;
	private int price;
	private String imagePath;
	
	public Item(Vector2 position, int price, String imagePath) {
		this.position = position;
		this.price = price;
		this.imagePath = imagePath;
	}
	
}
