package org.kentonbandy;

import org.kentonbandy.Commands.*;
import org.kentonbandy.UI.*;
import org.kentonbandy.action.Attack;
import org.kentonbandy.character.Enemy;
import org.kentonbandy.character.Player;
import org.kentonbandy.item.Armor;
import org.kentonbandy.item.Item;
import org.kentonbandy.item.Potion;
import org.kentonbandy.item.Weapon;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class AdventureLoop {
    private final World world;
    private final Map<String,Item> allItems;
    private Location currentLocation;
    private final Player player;
    private Enemy enemy;

    private final Parser parser = new Parser();
    private final Input input = new CliIn();
    private final Output output = new CliOut();
    private final ShopLoop shopLoop = new ShopLoop();
    private final Executor exec = new Executor();
    private int turnCount = 0;
    private Random rand = new Random();

    public AdventureLoop(World world, Player player) {
        this.world = world;
        this.allItems = world.getAggregateItems();
        this.currentLocation = world.getStartLocation();
        this.player = player;
    }


    public void run() {
        while (true) {
            if (!currentLocation.isSafe()) {
                if (rand.nextInt(5) == 4) {
                    List<Enemy> enemies = currentLocation.getEnemies();
                    enemy = enemies.get(rand.nextInt(enemies.size()));
                    battle();
                }
            }
            output.atLocation(currentLocation);
            Command command = null;
            try {
                command = parser.buildCommand(input.prompt(), allItems.keySet(), currentLocation);
            } catch (NoSuchActionException | EmptyCommandException | ItemNotFoundException | ObjectNotFoundExeption e) {
                output.error(e.getMessage());
            }
            if (command != null) {
                commandInfoHelper(command);
                Location loc = exec.execute(command, world, player, currentLocation);
                if (loc != null) {
                    currentLocation = loc;
                }
                turnCount++;
            }
        }
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location location) {
        currentLocation = location;
    }

    private void commandInfoHelper(Command command) {
        if (command != null) {
            System.out.println("Action: " + command.getAction());
            System.out.println("Object: " + command.getObject());
            System.out.println("Target: " + command.getTarget());
        }
    }

    private void battle() {
        output.line("--- BATTLE BEGIN! ---");
        output.line(enemy.getBattleMessage());
        boolean playerAlive = true;
        boolean enemyAlive = true;
        while (playerAlive && enemyAlive) {
            output.line("Your HP: " + player.getHp());
            output.line("Your MP: " + player.getMp());
            output.line(enemy.getName() + "'s HP: " + enemy.getHp());
            String action = input.battlePrompt();
            if (action.equals("attack")) {
                List<Attack> attacks = getAttacks();
                if (attacks.size() == 1) enemyAlive = attack(attacks.get(0), true);
                else {
                    boolean found = false;
                    while (!found) {
                        String choice = input.attackPrompt(attacks);
                        for (Attack attack : attacks) {
                            if (attack.getName().equalsIgnoreCase(choice)) {
                                enemyAlive = attack(attack, true);
                                found = true;
                            }
                        }
                        if (!found) output.error(choice + " isn't a valid attack");
                    }
                }
            }
            else if (action.equals("item")) {
                List<Item> inv = player.getInventory();
                if (inv.size() == 0) {
                    output.error("You don't have any items!");
                } else {
                    boolean found = false;
                    while (!found) {
                        output.line("Which item would you like to use?");
                        output.printInventory(player);
                        String choice = input.prompt();
                        for (Item item : inv) {
                            if (item.getName().toLowerCase().equals(choice)) {
                                if (item.getClass() == Armor.class) player.equipArmor((Armor)item);
                                else if (item.getClass() == Weapon.class) player.equipWeapon((Weapon)item);
                                else if (item.getClass() == Potion.class) player.use((Potion)item);
                                else {
                                    output.error("You can't use that now");
                                }
                                found = true;
                            }
                        }
                        if (!found) output.error(choice + " isn't in your inventory");
                    }

                }
            }
            else if (action.equals("run")) {
                if (rand.nextInt(5) == 3) {
                    output.line("You got away successfully!");
                    return;
                }
            }
            else {
                output.line("That isn't a valid choice");
                continue;
            }
            playerAlive = enemyAttack();
        }
        if (playerAlive) postBattle();
        else gameOver();
    }

    private List<Attack> getAttacks() {
        int mp = player.getMp();
        List<Attack> attacks = new ArrayList<>();
        for (Attack attack : player.getWeapon().getAttackList()) {
            if (attack.getMpCost() <= mp) attacks.add(attack);
        }
        return attacks;
    }

    private boolean attack(Attack attack, boolean isPlayer) {
        Enemy attacker = isPlayer ? player : enemy;
        Enemy defender = isPlayer ? enemy : player;
        int att = attack.getPower();
        int def = defender.getArmor().getDefense() * 5 / 100;
        int effect = att - def;
        output.line(attacker.getName() + " used " + attack.getName() + "!");
        output.line(defender.getName() + " lost " + effect + "HP!");
        defender.setHp(defender.getHp() - effect);
        if (isPlayer) player.setMp(player.getMp() - attack.getMpCost());
        return defender.getHp() > 0;
    }

    private boolean enemyAttack() {
        List<Attack> attacks = enemy.getWeapon().getAttackList();
        Attack attack;
        if (attacks.size() == 2) {
            int r = rand.nextInt(3);
            if (r > 1) attack = attacks.get(1);
            else attack = attacks.get(0);
        } else if (attacks.size() == 3) {
            int r = rand.nextInt(20);
            if (r > 15) attack = attacks.get(2);
            else if (r > 8) attack = attacks.get(1);
            else attack = attacks.get(0);
        } else {
            output.error("Attacks size isn't 2 or 3???");
            attack = attacks.get(0);
        }
        return attack(attack, false);
    }

    private void postBattle() {
        output.line("--- BATTLE ENDED ---");
        output.line("You've prevailed! You receive the following spoils:");
        output.printLocationItems(enemy.getInventory());
        for (Item item : enemy.getInventory()) {
            player.getItem(item);
        }
        output.line(enemy.getCurrencyAmount() + " currency");
        player.acquireCurrency(enemy.getCurrencyAmount());
        output.newLines(1);
        input.prompt("Press enter to continue.");
    }

    private void gameOver() {
        output.newLines(1);
        output.line("You have died! A resurrection tax of 10% is charged to your currency!");
        int before = player.getCurrencyAmount();
        player.setCurrency((int)(player.getCurrencyAmount() * 0.90));
        output.line(before + " => " + player.getCurrencyAmount());
        currentLocation = world.getStartLocation();
        input.prompt("Press enter to continue");
    }
}
