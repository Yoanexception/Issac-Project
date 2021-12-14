package resources;

import gameobjects.Item;
import libraries.Vector2;

import java.util.ArrayList;

public class Items {

    private static Vector2 position = new Vector2(0,0);

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
        Item bloodOfTheMartyr = new Item("Lunch", position, ImagePaths.LUNCH);
        bloodOfTheMartyr.setHealLife(2);
        return bloodOfTheMartyr;
    }

    public static Item GenerateJesusJuice(){
        Item bloodOfTheMartyr = new Item("Lunch", position, ImagePaths.JESUS_JUICE);
        bloodOfTheMartyr.setHealLife(2);
        bloodOfTheMartyr.setAddDamage(1);
        return bloodOfTheMartyr;
    }



    public static ArrayList<Item> getItems(){
        ArrayList<Item> items = new ArrayList<>();
        items.add(GenerateCoeur());
        items.add(GenerateBloodOfTheMartyr());
        items.add(GenerateLunch());
        items.add(GenerateJesusJuice());
        return items;
    }

}
