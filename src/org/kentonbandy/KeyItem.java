package org.kentonbandy;

public class KeyItem extends Item {
    private String target;
    private String effect;
    // Effect can be unlock, combine, persuade

    /**
     * @param name
     * @param description
     * @param price
     * @param target
     * @param effect
     */
    public KeyItem(String name, String description, int price, String target, String effect) {
        super(name, description, price);
        this.target = target;
        this.effect = effect;
    }

    /**
     * @param name
     * @param description
     * @param price
     * @param gettable
     * @param target
     * @param effect
     */
    public KeyItem(String name, String description, int price, boolean gettable, String target, String effect) {
        super(name, description, price, gettable);
        this.target = target;
        this.effect = effect;
    }

    public String getTarget() {
        return target;
    }

    public String getEffect() {
        return effect;
    }
}
