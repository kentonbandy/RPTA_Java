package org.kentonbandy.UI;

import org.kentonbandy.action.Attack;
import org.kentonbandy.character.ShopOwner;
import org.kentonbandy.item.Item;

import java.util.List;

public interface Input {
    String prompt();
    void prompt(String message);
    boolean promptYN(String message);
    boolean quit();
    Item prompt(ShopOwner shop) throws LeaveException;
    String battlePrompt();
    String attackPrompt(List<Attack> attacks);
}
