package gameWorld.room;

import gameWorld.Door;
import gameobjects.*;
import gameobjects.items.*;
import gameobjects.monsters.Fly;
import gameobjects.monsters.Monster;
import gameobjects.monsters.Spider;
import gameobjects.obstacles.Obstacles;
import gameobjects.obstacles.Rock;
import gameobjects.obstacles.Spikes;
import libraries.Physics;
import libraries.StdDraw;
import libraries.Vector2;
import resources.*;

import javax.swing.text.Position;
import java.util.ArrayList;
import java.util.Random;

/**
 * The type Monsters room.
 */
public class MonsterRoom extends Room {
	
	private ArrayList<Monster> monster;
	private ArrayList<Obstacles> obstacles;
	private ArrayList<Coin> coin;
	private ArrayList<Hearts> hearts;
	private Key key;
	private Item passif;
	private boolean generateItem;

	/**
	 * Instantiates a new Monsters room.
	 *
	 * @param hero      the hero
	 * @param leftRoom  the left room
	 * @param rightRoom the right room
	 * @param upRoom    the up room
	 * @param downRoom  the down room
	 * @param nbMonster the nb monster
	 */
	public MonsterRoom(Hero hero, Door leftRoom, Door rightRoom, Door upRoom, Door downRoom, int nbMonster) {
		super(hero, leftRoom, rightRoom, upRoom, downRoom);
		this.monster = new ArrayList<>();
		this.obstacles = new ArrayList<>();
		this.coin = new ArrayList<>();
		this.hearts = new ArrayList<>();
		this.key = null;
		this.passif = null;
		this.generateItem = false;
		generateMonster(nbMonster);
		generateObstacles();
	}

	/**
	 * Generer tout les obstacles de la salle (Pique et Roc)
	 */
	private void generateObstacles() {
		int nbObstacles = (int) (1 + Math.random() * 4);
		for(int i = 0; i < nbObstacles; i++){
			int type = (int) (Math.random() * 2);
			double x = RoomInfos.TILE_SIZE.getX() * 2 + (Math.random() * (1 - RoomInfos.TILE_SIZE.getX() * 4));
			double y = RoomInfos.TILE_SIZE.getY() * 2 + (Math.random() * (1 - RoomInfos.TILE_SIZE.getY() * 4));
			Vector2 position = new Vector2(x,y);
			if(isOnObstacle(position, ObstaclesInfo.SIZE_ROCK)){
				break;
			}
			if(x < 0.53 + ObstaclesInfo.SIZE_ROCK.getX() / 2 && x > 0.47 - ObstaclesInfo.SIZE_ROCK.getX() / 2 && y < 0.53 + ObstaclesInfo.SIZE_ROCK.getY() / 2 && y > 0.47 - ObstaclesInfo.SIZE_ROCK.getY() / 2){
				break;
			}
			if(type == 0){
				Rock newRock = new Rock(position);
				obstacles.add(newRock);
			} else {
				Spikes newSpikes = new Spikes(position);
				obstacles.add(newSpikes);
			}
		}
	}

	/**
	 * Elle genere les monstre present dans la salle.
	 *
	 * @param nbMonster nombre de monstre voulue dans cette salle
	 */
	public void generateMonster(int nbMonster) {
		for(int i = 0; i < nbMonster; i++){
			int randomSpices = (int) (Math.random() * 2);
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

	/**
	 * Permet de dessiner la salle on rajoute a celle de la classe Room le fait de dessiner les item et les monstres,
	 * On veut aussi avant de les dessner verifier si certain monstre ne sont pas mort
	 *
	 */
	public void drawRoom(){
		monsterDead();
		super.drawRoom();
		drawItem();
		drawMonster();
	}

	/**
	 *  Permet de dessiner les obstacles.
	 */
	public void drawObstacles() {
		for(Obstacles o : obstacles){
			StdDraw.picture(o.getPosition().getX(), o.getPosition().getY(), o.getImagePath(), o.getSize().getX(), o.getSize().getY());
		}
	}

	/**
	 * Dessine les items
	 */
	public void drawItem() {
		for(Coin c : coin){
			StdDraw.picture(c.getPosition().getX(), c.getPosition().getY(), c.getImagePath());
		}
		for(Hearts h : hearts){
			StdDraw.picture(h.getPosition().getX(), h.getPosition().getY(), h.getImagePath());
		}
		if(passif != null){
			StdDraw.picture(passif.getPosition().getX(), passif.getPosition().getY(), passif.getImagePath(), 0.075,0.075);
		}
		if(key != null){
			StdDraw.picture(key.getPosition().getX(), key.getPosition().getY(), key.getImagePath());
		}
	}

	/**
	 * Met a jour la salle, on ajoute a celle de la classe Room, le fait de mettre a jour les monstres, de les faire bouger, et de verifier que le hero n'a pas touche un item.
	 */
	public void updateRoom(){
		super.updateRoom(obstacles);
		updateMonster();
		moveMonster(super.getHero());
		hitCoin(super.getHero());
		hitHeart(super.getHero());
		if(key != null){
			hitKey(super.getHero());
		}
		if(passif != null){
			hitPassif(super.getHero());
		}
	}

	public void explodeBomb(Bomb b) {
		super.explodeBomb(b);
		ArrayList<Obstacles> obstaclesToDelete = new ArrayList<>();
		for(Obstacles o : obstacles){
			if(o.getClass().getSimpleName().equals("Rock"))
			{
				if(Physics.CircleToPointCollision(o.getPosition(), b.getPosition(), b.getRange(), o.getSize())){
					obstaclesToDelete.add(o);
				}
			}
		}
		ArrayList<Monster> monstersToDelete = new ArrayList<>();
		for(Monster m : monster){
			if(Physics.CircleToPointCollision(m.getPosition(), b.getPosition(), b.getRange(), m.getSize())){
				m.setLife(m.getLife() - b.getDamage());
				System.out.println(m.getLife());
				if(m.isDead()){
					monstersToDelete.add(m);
				}
			}
		}
		obstacles.removeAll(obstaclesToDelete);
		monster.removeAll(monstersToDelete);
	}

	/**
	 * Regarde si le hero n'a pas touche une piece
	 *
	 * @param h Le hero
	 */
	private void hitCoin(Hero h) {
		ArrayList<Coin> toDelete = new ArrayList<>();
		for(Coin c : coin){
			if(Physics.rectangleCollision(h.getPosition(), h.getSize(), c.getPosition(), c.getSize())
			){
				toDelete.add(c);
				h.setGold(h.getGold() + c.getAddCoin());
			}
		}
		coin.removeAll(toDelete);
	}

	/**
	 * Verifie si l'utilisateur ne marche pas sur la clé pour la recuoerer
	 * @param h Le hero de la room currente
	 */
	private void hitKey(Hero h){
		if(Physics.rectangleCollision(h.getPosition(), h.getSize(), key.getPosition(), key.getSize())){
			h.setKeys(h.getKeys() + 1);
			key = null;
		}
	}

	/**
	 * Regarde si le hero n'a pas touche un coeur
	 *
	 * @param hero Le hero
	 */
	private void hitHeart(Hero hero) {
		ArrayList<Hearts> toDelete = new ArrayList<>();
		for(Hearts h : hearts){
			if(Physics.rectangleCollision(h.getPosition(), h.getSize(), h.getPosition(), h.getSize()
			)){
				toDelete.add(h);
				if(hero.getLife() > hero.getLifeMax() - h.getAddLife()){
					hero.setLife(hero.getLifeMax());
				} else {
					hero.setLife(hero.getLife() + h.getAddLife());
				}
			}
		}
		hearts.removeAll(toDelete);
	}

	/**
	 * Regarde si le hero n'a pas touche un item passif (Les memes que ceux dans la salle du shop)
	 *
	 * @param h Le hero
	 */
	private void hitPassif(Hero h){
		if(Physics.rectangleCollision(h.getPosition(), h.getSize(), passif.getPosition(), passif.getSize()
		)){
			h.setLifeMax(h.getLifeMax() + passif.getAddLifeMax());
			if(h.getLife() < h.getLife() - passif.getHealLife()){
				h.setLife(h.getLife() + passif.getHealLife());
			} else {
				h.setLife(h.getLifeMax());
			}
			h.setDamageAttack(h.getDamageAttack() + passif.getAddDamage());
			if(passif.isHeal()){
				h.setLife(h.getLifeMax());
			}
			h.setSpeed(h.getSpeed() + passif.getAddSpeed());
			passif = null;
		}
	}

	/**
	 * Permet de faire bouger tout les monstres
	 *
	 * @param h Le hero
	 */
	public void moveMonster(Hero h){
		for(Monster m : monster){
			m.move(h, obstacles);
		}
	}

	/**
	 * Dessine tout les mosntres
	 */
	public void drawMonster(){
		for(Monster m : monster){
			StdDraw.picture(m.getPosition().getX(), m.getPosition().getY(), m.getImagePath(), m.getSize().getX(), m.getSize().getY());
		}
	}

	/**
	 * Met a jour tout les monstres en verifiant si ils n'ont pas été touche par une larme ou si ils sont morts.
	 */
	public void updateMonster(){
		ArrayList<Monster> monsterToDelete = new ArrayList<>();
		ArrayList<Larme> larmeToDelete = new ArrayList<>();
		for(Monster m : monster){
			m.setWait(m.getWait() > 0 ? m.getWait() - 1 : 0);
			if(m.hitHero(super.getHero()) && m.getWait() == 0 && !super.getHero().isInvincible()){
				super.getHero().setLife(super.getHero().getLife() - 1);
				m.setWait(10);
			}
			for(Larme l : super.getLarme()){
				if(m.isHit(l)){
					larmeToDelete.add(l);
				}
				if(m.isDead()){
					monsterToDelete.add(m);
				}
			}
		}
		super.deleteLarme(larmeToDelete);
		monster.removeAll(monsterToDelete);

	}

	/**
	 * Regarde si les larmes n'ont pas touche d'obstacles
	 *
	 * @param l La liste des larmes de la salle
	 * @return le booleen
	 */
	public boolean hitObstacles(Larme l){
		for (Obstacles o : obstacles){
			if(Physics.rectangleCollision(l.getPosition(), l.getSize(), o.getPosition(), o.getSize())){
				return true;
			}
		}
		return false;
	}

	/**
	 * Regarde si tout les monstre sont dort et genere les items puis ouvre les portes.
	 */
	public void monsterDead() {
		if (monster.size() == 0) {
			if (super.getUpDoor() != null) {
				if(!super.getUpDoor().isKeyLocked()) super.getUpDoor().setOpen(true);
			}
			if (super.getDownDoor() != null) {
				if(!super.getDownDoor().isKeyLocked()) super.getDownDoor().setOpen(true);
			}
			if (super.getLeftDoor() != null) {
				if(!super.getLeftDoor().isKeyLocked()) super.getLeftDoor().setOpen(true);
			}
			if (super.getRightDoor() != null) {
				if(!super.getRightDoor().isKeyLocked()) super.getRightDoor().setOpen(true);
			}

			generateItem();

		}
	}

	/**
	 * Cette fonction genere les items avec une certaine probabilité
	 */
	private void generateItem(){
		if (!generateItem) {
			/**
			 * Generate Coin
			 */
			double random = Math.random();
			if (random <= 0.5) {
				int randomNbCoin = (int) (1 + Math.random() * 2);
				for (int i = 0; i < randomNbCoin; i++) {
					double x = 0.2 + Math.random() * 0.6;
					double y = 0.2 + Math.random() * 0.6;
					Vector2 position = new Vector2(x, y);
					if(isOnObstacle(position, Items.sizeItems)){
						while (isOnObstacle(position, Items.sizeItems)){
							x = 0.2 + Math.random() * 0.6;
							y = 0.2 + Math.random() * 0.6;
							position = new Vector2(x, y);
						}
					}
					double type = Math.random();
					Coin newCoin = new Coin(position, 1, "");
					if (type < 0.7) {
						newCoin = new Coin(position, 1, ImagePaths.COIN);
					} else if (type < 0.9) {
						newCoin = new Coin(position, 5, ImagePaths.NICKEL);
					} else {
						newCoin = new Coin(position, 10, ImagePaths.DIME);
					}
					coin.add(newCoin);
				}
			}
			else if (random <= 0.7) {
				double x = 0.2 + Math.random() * 0.6;
				double y = 0.2 + Math.random() * 0.6;
				Vector2 position = new Vector2(x, y);
				while (isOnObstacle(position, Items.sizeItems)){
					x = 0.2 + Math.random() * 0.6;
					y = 0.2 + Math.random() * 0.6;
					position = new Vector2(x, y);
				}
				key = new Key(position);
			}
			else if (random <= 0.9) {
				double x = 0.2 + Math.random() * 0.6;
				double y = 0.2 + Math.random() * 0.6;
				Vector2 position = new Vector2(x, y);
				if(isOnObstacle(position, Items.sizeItems)){
					while (isOnObstacle(position, Items.sizeItems)){
						x = 0.2 + Math.random() * 0.6;
						y = 0.2 + Math.random() * 0.6;
						position = new Vector2(x, y);
					}
				}
				double type = Math.random();
				if (type > 0.3) {
					HalfHeart newHeart = new HalfHeart(position);
					hearts.add(newHeart);
				} else {
					Heart newHeart = new Heart(position);
					hearts.add(newHeart);
				}
			}
			else {
				ArrayList<Item> list = Items.getItems();
				double x = 0.2 + Math.random() * 0.6;
				double y = 0.2 + Math.random() * 0.6;
				Vector2 position = new Vector2(x, y);
				if(isOnObstacle(position, Items.sizeItems)){
					while (isOnObstacle(position, Items.sizeItems)){
						x = 0.2 + Math.random() * 0.6;
						y = 0.2 + Math.random() * 0.6;
						position = new Vector2(x, y);
					}
				}
				int randomItems = (int) (Math.random() * list.size());
				passif = list.get(randomItems);
				passif.setPosition(position);
			}
			generateItem = true;
		}
	}

	/**
	 * Renvoie si la position et la taille sont dans un obstacles de la salle
	 *
	 * @param position La position de l'object demande
	 * @param size La taille de l'object demandé
	 * @return le booleen
	 */
	private boolean isOnObstacle(Vector2 position, Vector2 size){
		for(Obstacles o : obstacles){
			if(Physics.rectangleCollision(o.getPosition(), o.getSize(), position, size)){
				return true;
			}
		}
		return false;
	}

	public void killAllMonster(){
		monster.clear();
	}

}
