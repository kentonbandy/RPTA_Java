package org.kentonbandy.UI;

import org.kentonbandy.character.Player;
import org.kentonbandy.character.ShopOwner;
import org.kentonbandy.item.Item;

public class ShopLoop {
    private Output output = new CliOut();
    private Input input = new CliIn();

    public void run(ShopOwner shop, Player player) {
        while (true) {
            output.shopMenu(shop, player);
            Item i = null;
            try {
                i = input.prompt(shop);
            } catch (LeaveException e) {
                output.line(shop.getName() + ": " + shop.getFarewell());
                break;
            }
            if (i != null) {
                if (player.getCurrencyAmount() >= i.getPrice()) {
                    player.getItem(i);
                    player.setCurrency(player.getCurrencyAmount() - i.getPrice());
                    output.purchaseSuccess(i);
                } else {
                    output.error("You don't have enough currency");
                }
            } else {
                output.error("You can't buy that here");
            }
        }
    }
}
