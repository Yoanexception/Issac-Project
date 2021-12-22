package gameWorld;

import gameWorld.room.*;
import gameobjects.Hero;

import java.util.ArrayList;
import java.util.Random;

public class WorldGenerator {

    /**
     * Renvoie la salle de spawn d'un etage généré aleatoirement
     * @param hero Le hero du jeux
     * @param nbMonstersRoom Le nombre de salle de monstre présent dans l'etage (minimun 2)
     * @return Une salle de spawn
     */
    public static Room generateLevel(Hero hero, int nbMonstersRoom){
        SpawnRoom spawn = new SpawnRoom(hero, null, null, null, null);
        BossRoom bossRoom = new BossRoom(hero, null, null, null, null);
        ShopRoom shopRoom = new ShopRoom(hero, null, null, null, null);
        ArrayList<MonsterRoom> monstersRoom = new ArrayList<>();
        for(int i = 0; i < nbMonstersRoom; i++){
            int nbMonsters = (int) (1 + Math.random() * 4);
            MonsterRoom monsterRoom = new MonsterRoom(hero, null, null,null,null, nbMonsters);
            monstersRoom.add(monsterRoom);
        }
        Room[][] map = new Room[5][5];
        int x = map.length - 1;
        int y = map[x].length / 2;
        map[x][y] = spawn;
        x -= 1;
        for(Room room : monstersRoom){
            map[x][y] = room;
            boolean roomPlaced = false;
            //On place la prochaine salle
            int r = 0;
            while(!roomPlaced){
                r++;
                if(r == 50){
                    return generateLevel(hero, nbMonstersRoom);
                }
                int proba = new Random().nextInt(4);
                switch (proba) {
                    case 0 -> {
                        if(y != 0 && map[x][y-1] == null){
                            y -= 1;
                            roomPlaced = true;
                        }
                    }
                    case 1 -> {
                        if(y != map[x].length - 1 && map[x][y+1] == null){
                            y += 1;
                            roomPlaced = true;
                        }
                    }
                    case 2 -> {
                        if(x != 0 && map[x-1][y] == null){
                            x -= 1;
                            roomPlaced = true;
                        }
                    }
                    case 3 -> {
                        if(x < map.length - 2 && map[x+1][y] == null){
                            x += 1;
                            roomPlaced = true;
                        }
                    }
                }
            }
        }

        int bossX = x;
        int bossY = y;

        //on place la salle du shop
        boolean shopPlaced = false;

        while(!shopPlaced){
            x = new Random().nextInt(map.length - 2);
            y = new Random().nextInt(map[x].length);

            if(map[x][y] == null && isNextMonsterRoom(x,y,map)){
                map[x][y] = shopRoom;
                shopPlaced = true;
            }
        }

        int k = 0;
        while(!(map[bossX][bossY] == null && isNextRoom(bossX,bossY,map,1))){
            bossX = new Random().nextInt(map.length - 2);
            bossY = new Random().nextInt(map[x].length);
            k++;
            if(k == 50){
                return generateLevel(hero, nbMonstersRoom);
            }
        }

        map[bossX][bossY] = bossRoom;

        for (int i = 0; i < map.length; i++) {

            for (int j = 0; j < map[i].length; j++) {
                if(map[i][j] != null){
                    boolean doorClose = (!(map[i][j].getClass().getSimpleName().equals("MonsterRoom")) && !(map[i][j].getClass().getSimpleName().equals("BossRoom")));
                    if(i!= map.length - 1 && map[i + 1][j] != null){
                        Door newDoor = new Door(map[i+1][j], doorClose);
                        map[i][j].setDownDoor(newDoor);
                    }
                    if(i != 0 && map[i - 1][j] != null){
                        Door newDoor = new Door(map[i-1][j], doorClose);
                        map[i][j].setUpDoor(newDoor);
                    }
                    if(j != map[x].length - 1 && map[i][j + 1] != null){
                        Door newDoor = new Door(map[i][j+1], doorClose);
                        map[i][j].setRightDoor(newDoor);
                    }
                    if(j != 0 && map[i][j - 1] != null){
                        Door newDoor = new Door(map[i][j-1], doorClose);
                        map[i][j].setLeftDoor(newDoor);
                    }
                }
            }
        }

        drawMap(map);

        return map[map.length - 1][map[0].length / 2];

    }

    private static boolean isNextMonsterRoom(int x, int y, Room[][] map) {
        if(x < map.length - 1 && map[x + 1][y] != null && map[x + 1][y].getClass().getSimpleName().equals("MonsterRoom")){
            return true;
        } else if(x != 0 && map[x - 1][y] != null && map[x - 1][y].getClass().getSimpleName().equals("MonsterRoom")){
            return true;
        } else if(y < map.length - 1 && map[x][y + 1] != null && map[x][y + 1].getClass().getSimpleName().equals("MonsterRoom")){
            return true;
        } else return y != 0 && map[x][y - 1] != null &&  map[x][y - 1].getClass().getSimpleName().equals("MonsterRoom");
    }

    /**
     *
     * @param x Position x de la salle
     * @param y Position y de la salle
     * @param map Carte Tableau de deux dimensions qui represente l'air de jeux
     * @param nbRoom Le nombre que la salle voulue doit avoir a coté
     * @return Si la salle a bien le bon nombre de salle voisin
     */
    private static boolean isNextRoom(int x, int y, Room[][] map, int nbRoom) {
        int i = 0;
        if(x < map.length - 1 && map[x + 1][y] != null){
            i++;
        }
        if(x != 0 && map[x - 1][y] != null) {
            i++;
        }
        if(y < map.length - 1 && map[x][y + 1] != null){
            i++;
        }
        if(y != 0 && map[x][y - 1] != null){
            i++;
        }
        return nbRoom == i;
    }

    private static void drawMap(Room[][] map) {
        for (Room[] rooms : map) {
            for (Room room : rooms) {
                if (room == null) {
                    System.out.print(" ");
                } else {
                    switch (room.getClass().getSimpleName()) {
                        case "SpawnRoom" -> {
                            System.out.print("S");
                        }
                        case "ShopRoom" -> {
                            System.out.print("$");
                        }
                        case "MonsterRoom" -> {
                            System.out.print("M");
                        }
                        case "BossRoom" -> {
                            System.out.print("B");
                        }
                    }
                }
                System.out.print("|");
            }
            System.out.println("");
        }
        System.out.println("");
        System.out.println("");
        System.out.println("");
    }

}
