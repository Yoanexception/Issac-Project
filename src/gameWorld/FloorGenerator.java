package gameWorld;

import gameWorld.room.Room;
import gameobjects.Hero;

import java.util.ArrayList;

public class FloorGenerator {

    public static Floor generateDonjon(Hero hero, int nbFloor){
        if(nbFloor > 1){
            ArrayList<Floor> donjon = new ArrayList<>();
            for(int i = 0; i < nbFloor; i++){
                Room spawnFloor = WorldGenerator.generateLevel(hero, 7);
                Floor newFloor = new Floor(hero, spawnFloor);
                donjon.add(newFloor);
            }
            for(int i = 0; i < nbFloor; i++){
                if(i == 0){
                    donjon.get(i).setNextFloor(donjon.get(i+1));
                } else if(i == nbFloor - 1 ){
                    donjon.get(i).setPreviousFloor(donjon.get(i-1));
                } else {
                    donjon.get(i).setNextFloor(donjon.get(i+1));
                    donjon.get(i).setPreviousFloor(donjon.get(i-1));
                }
            }
            return donjon.get(0);
        } else {
            return new Floor(hero, WorldGenerator.generateLevel(hero, 7));
        }
    }

}
