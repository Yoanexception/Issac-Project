package resources;

import gameobjects.items.Item;
import libraries.Vector2;

import java.util.ArrayList;

public class Items {

    private static Vector2 position = new Vector2(0,0);
    public static Vector2 sizeItems = new Vector2(0.075,0.075);

    public static Item GenerateCoeur() {
        Item coeur = new Item("<3", position, ImagePaths.HP_UP);
        coeur.setHeal(true);
        coeur.setAddLifeMax(2);
        return coeur;
    }

    public static Item GenerateBloodOfTheMartyr(){
        Item bloodOfTheMartyr = new Item("Blood Of The Martyr", position, ImagePaths.BLOOD_OF_THE_MARTYR);
        bloodOfTheMartyr.setAddDamage(1);
        return bloodOfTheMartyr;
    }

    public static Item GenerateLunch(){
        Item lunch = new Item("Lunch", position, ImagePaths.LUNCH);
        lunch.setHealLife(2);
        return lunch;
    }

    public static Item GenerateJesusJuice(){
        Item jesusJuice = new Item("Lunch", position, ImagePaths.JESUS_JUICE);
        jesusJuice.setHealLife(2);
        jesusJuice.setAddDamage(1);
        return jesusJuice;
    }

    public static Item GenerateMushroom(){
        Item mushroom = new Item("Mushroom", position, ImagePaths.MAGIC_MUSHROOM);
        mushroom.setAddSpeed(0.005);
        mushroom.setHeal(true);
        return mushroom;
    }

    public static Item GeneratePentagram(){
        Item pentagram = new Item("Mushroom", position, ImagePaths.PENTAGRAM);
        pentagram.setAddDamage(1);
        pentagram.setHealLife(1);
        return pentagram;
    }


    /**
     * Liste des items avec leur bienfaits :
     *  - <3 : Ajoute 1 coeur dans la vie max du hero et le soigne entierement.
     *  - Blood of the martyr : Ajoute 1 points de degats pour le heros
     *  - Lunch : Soigne de 1 coeur le hero
     *  - Jesus Juice : Soigne de 1 coeur le hero et lui ajoute 1 point de degats
     *  - Mushroom : Rend le joueur plus rapide et le soigne entierement
     *  - Pentagram : Ajoite 1 point de degats et 1 point de vie
     * @return La liste des items disponible
     */
    public static ArrayList<Item> getItems(){
        ArrayList<Item> items = new ArrayList<>();
        items.add(GenerateCoeur());
        items.add(GenerateBloodOfTheMartyr());
        items.add(GenerateLunch());
        items.add(GenerateJesusJuice());
        items.add(GenerateMushroom());
        return items;
    }

}
