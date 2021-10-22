package org.kentonbandy;

import org.kentonbandy.DAO.CsvLoader;
import org.kentonbandy.DAO.DataLoader;
import org.kentonbandy.DAO.ObjectFactory;
import org.kentonbandy.action.*;
import org.kentonbandy.character.*;
import org.kentonbandy.item.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {

    private static boolean enemiesDropEquipment = false;
    private static Location currentLocation;

    public static void main(String[] args) {
        DataLoader dataLoader = new CsvLoader();
        ObjectFactory objectFactory = new ObjectFactory(dataLoader.loadGameData());
        World world = new World();
        world.buildWorld(objectFactory);

        Map<String,Location> locations = world.getLocations();
        System.out.println(locations);

        /*
        Game start screen
            -start game
            -tutorial
            -instructions
            -quit
        Start game
            -Enter name
            -Enter description
        Spawn at starting location
            -location description includes hints for characters and items present
            -bosses attack after x turns (boss counter?)
            -if location is an unsafe location, the player can be randomly attacked by spawned enemies
            -random enemies are generated in real time with RNG
            -the player can >wait at unsafe locations until enemies spawn
            -potions outside of battles replenish HP/MP fully, in battle they only replenish their set amount
            -location descriptions change based on the items and characters at the location
        Battles
            -player and enemy alternate taking actions
            -if enemy is vanquished, player gets spoils including currency and items that the enemy had
         */
    }
}
