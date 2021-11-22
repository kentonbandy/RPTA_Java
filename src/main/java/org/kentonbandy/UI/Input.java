package org.kentonbandy.UI;

import org.kentonbandy.character.ShopOwner;
import org.kentonbandy.item.Item;

public interface Input {
    String prompt();
    boolean promptYN(String message);
    boolean quit();
    Item prompt(ShopOwner shop) throws LeaveException;
}
