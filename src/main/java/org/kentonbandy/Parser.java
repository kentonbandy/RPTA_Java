package org.kentonbandy;

import org.kentonbandy.UI.CliInOut;
import org.kentonbandy.character.Player;
import org.kentonbandy.item.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Parser {
    private String verb;
    private String object;
    private String target;
    private Set<String> commands = Set.of("get", "take", "drop", "x", "examine", "inspect", "talk", "hit", "go",
            "travel", "equip", "unequip", "remove", "use", "climb", "duck", "run", "attack", "speak", "strike", "push",
            "pull", "steal", "break", "place", "set", "grab", "throw");

    private Set<String> oneWordCommands = Set.of("north", "n", "south", "s", "east", "e", "west", "w", "up", "down", "in",
            "inside", "out", "outside", "inventory", "i", "look", "l");

    public void execute(String input) {
        String[] arr = input.split(" ");
        if (arr.length == 1) {
            String word = arr[0].toLowerCase();
            if (!oneWordCommands.contains(word)) CliInOut.error("I don't recognize that command");
            else runOneWordCommand(word);
        }
    }

    private void runOneWordCommand (String command) {
        if (command.equals("north") || command.equals("n")) ;

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