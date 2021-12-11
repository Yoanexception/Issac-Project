package gameWorld;

import gameobjects.Fly;
import gameobjects.Hero;
import gameobjects.Monster;
import gameobjects.Spider;
import libraries.StdDraw;
import libraries.Vector2;
import resources.MonstersInfo;

import javax.swing.text.Position;
import java.util.ArrayList;

public class MonstersRoom extends Room {
	
	private ArrayList<Monster> monster;

	public MonstersRoom(Hero hero, Door leftRoom, Door rightRoom, Door upRoom, Door downRoom, int nbMonster) {
		super(hero, leftRoom, rightRoom, upRoom, downRoom);
		this.monster = new ArrayList<Monster>();
		generateMonster(nbMonster);
	}
	
	/**
	 * Elle genere les monstre present dans la salle.
	 * @param nbMonster nombre de monstre voulue dans cette salle
	 */
	public void generateMonster(int nbMonster) {
		for(int i = 0; i < nbMonster; i++){
			int randomSpices = (int) (Math.random() * 2);
			System.out.println(randomSpices);
			double randomX = 0.15 + (Math.random() * 0.70);
			double randomY = 0.15 + (Math.random() * 0.70);
			Vector2 position = new Vector2(randomX, randomY);
			//if =0, generate Spider
			//if =1, generate Fly
			if(randomSpices == 0){
				Spider newMonster = new Spider(position, MonstersInfo.SPIDER_SIZE, MonstersInfo.SPIDER_SPEED, MonstersInfo.SPIDER_LIFE, MonstersInfo.SPIDER_DAMAGE);
				monster.add(newMonster);
			} else if(randomSpices == 1) {
				Fly newMonster = new Fly(position, MonstersInfo.FLY_SIZE, MonstersInfo.FLY_SPEED, MonstersInfo.FLY_LIFE, MonstersInfo.FLY_DAMAGE, MonstersInfo.FLY_PROJECTIL_RANGE, MonstersInfo.FLY_PROJECTIL_DAMAGE, MonstersInfo.FLY_PROJECTIL_SPEED);
				monster.add(newMonster);
			}
		}
	}

	public void drawRoom(){
		monsterDead();
		super.drawRoom();
		drawMonster();
	}

	public void drawMonster(){
		for(Monster m : monster){
			StdDraw.picture(m.getPosition().getX(), m.getPosition().getY(), m.getImagePath(), m.getSize().getX(), m.getSize().getY());
		}
	}

	public void monsterDead(){
		if(monster.size() == 0){
			if(super.getUpDoor() != null) {super.getUpDoor().setOpen(true);}
			if(super.getDownDoor() != null) {super.getDownDoor().setOpen(true);}
			if(super.getLeftDoor() != null) {super.getLeftDoor().setOpen(true);}
			if(super.getRightDoor() != null) {super.getRightDoor().setOpen(true);}
		}
	}

}
