package org.kentonbandy.Commands;

import org.kentonbandy.Location;
import org.kentonbandy.item.Item;

import java.util.*;


/**
 * The Parser checks for good actions, but does not check to see if the objects or targets are good.
 * NoSuchActionException is thrown if an action is not recognized.
 * one-word commands are fully verified.
 */

public class Parser {
    private final Set<String> actions = Set.of("get", "take", "drop", "x", "examine", "inspect", "talk", "hit", "go",
            "travel", "equip", "unequip", "remove", "use", "climb", "duck", "run", "attack", "speak", "strike", "push",
            "pull", "steal", "break", "place", "set", "grab", "throw", "move", "put");

    private final Set<String> ignored = Set.of("in", "inside", "on", "of", "over", "under", "underneath", "below",
            "beneath", "into", "onto", "with", "across", "around", "as", "at", "beside", "between", "by", "from",
            "near", "past", "toward", "towards", "the", "some", "all", "my", "out");

    private final Map<String,String> oneWordMoveMap = Map.of(
            "n", "north",
            "s", "south",
            "e", "east",
            "w", "west",
            "in", "inside",
            "out", "outside"
    );

    private final Map<String,String> oneWordNonMoveMap = Map.of(
            "i", "inventory",
            "l", "look"
    );

    private final Set<String> directions = Set.of("north", "n", "south", "s", "east", "e", "west", "w", "up", "down",
            "in", "inside", "out", "outside");

    private final Set<String> oneWordCommands = Set.of("inventory", "i", "look", "l");

    public Command buildCommand(String input, Location location) throws NoSuchActionException, EmptyCommandException, ItemNotFoundException {
        Item item = null;
        for (Item i : location.getItems()) {
            String iName = i.getName().toLowerCase();
            if (input.contains(iName)) {
                input = input.replace(iName, iName.replaceAll(" ", ""));
            }
        }
        List<String> lst = new ArrayList<> (Arrays.asList(input.split(" ")));
        lst.removeAll(ignored);
        if (lst.size() == 0) throw new EmptyCommandException();
        String action = lst.get(0);
        if (lst.size() == 1) return buildOneWordCommand(action);
        if (!actions.contains(action)) throw new NoSuchActionException();
        for (Item i : location.getItems()) {
            String iName = i.getName().toLowerCase();
            if (iName.replace(" ", "").equals(lst.get(1))) {
                item = i;
                break;
            }
        }
        if (item == null) throw new ItemNotFoundException();
        if (lst.size() == 2) return new Command(action, item.getName());
        // todo: implement Targets (conditional items)
        else if (lst.size() == 3) return new Command(action, item.getName(), lst.get(2));
        throw new NoSuchActionException();
    }

    private Command buildOneWordCommand(String word) throws NoSuchActionException {
        if (oneWordCommands.contains(word) || directions.contains(word)) {
            boolean isDirection = (oneWordMoveMap.containsKey(word) || oneWordMoveMap.containsValue(word));
            String action = (isDirection ? "move" : oneWordNonMoveMap.getOrDefault(word, word));
            if (action.equals("move")) {
                String direction = oneWordMoveMap.getOrDefault(word, word);
                return new Command(action, direction);
            } return new Command(action);
        } throw new NoSuchActionException();
    }

    private Item getLocationItem(String itemName, Location location) {
        for (Item item : location.getItems()) {
            if (item.getName().toLowerCase().equals(itemName.toLowerCase())) return item;
        }
        return null;
    }

    /*
    Parser needs to:
    split the input and find an action (verb) and target (noun or direction)
    one-word commands are accepted for directions and shortcuts (look, l, inventory, i)
    prepositions will be ignored (talk to merchant -> talk merchant)
    some verbs will require an object and a target (put stick in box -> verb(put) stick(object) in(ignored) box(target))
    if only a verb is found, the user will be prompted for a target
    the parser will look for a direction or one-word command first, then a verb, then a target or object and target based on the requirements of the verb
     */
}