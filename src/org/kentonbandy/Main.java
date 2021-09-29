package org.kentonbandy;

import org.kentonbandy.action.*;
import org.kentonbandy.character.*;
import org.kentonbandy.item.*;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Attack slash = new Attack("Slash", 4);
        Armor breastplate = new Armor("Breastplate", "A shiny bronze breastplate", 7, 3);
        List<Item> inventory = new ArrayList<>();
        Weapon sword = new Weapon("Sword", "A long metal murder device with a blunt end and a pointy end.", 5, 2);
        Player player = new Player("Humperdink", "A precocious adventurer", 0, inventory, 1, slash, breastplate,
                sword);
        System.out.println("Player name: " + player.getName());
        System.out.println("Player description: " + player.getDescription());
        System.out.println("Player level: " + player.getLevel());
        System.out.println("Player HP: " + player.getHp());
        System.out.println("Player MP: " + player.getMp());
        System.out.println("Player XP: " + player.getXp());
        System.out.println("Player weapon: " + player.getWeapon().getName());
        System.out.println("Player armor: " + player.getArmor().getName());
        System.out.println("Player attack: " + player.getAttack().getName());

    }
}
