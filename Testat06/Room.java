public class Room {
    private String description;
    private Door[] doors;
    private Item[] items;
    private boolean winningRoom;

    public Room(String description, boolean isWinningRoom) {
        this.description = description;
        this.doors = new Door[4]; // Assuming up to 4 directions (N, S, E, W)
        this.items = new Item[10];
        this.winningRoom = isWinningRoom;
    }

    /**
     * Liefert einen Array mit den Türen des Raums
     * 
     * @return
     */
    public Door[] getDoors() {
        return doors;
    }

    /**
     * liefert eine Beschreibung des Raums
     * 
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * Liefert eine Tür (Door) oder null, falls in der angegebenen Richtung keine
     * Tür existiert.
     * 
     * @param direction Richtung in deren Richtung nach einer Tür geprüft werden
     *                  soll ("north", "south", "east", "west").
     * @return Die Tür (Klasse: Door) oder null, falls keine Tür existiert.
     */
    public Door getDoorInDirection(String direction) {
        if (direction.equalsIgnoreCase("north")) {
            return doors[0];
        } else if (direction.equalsIgnoreCase("south")) {
            return doors[1];
        } else if (direction.equalsIgnoreCase("east")) {
            return doors[2];
        } else if (direction.equalsIgnoreCase("west")) {
            return doors[3];
        }
        return null;
    }

    /**
     * Liefert einen Array mit den Items des Raums.
     * 
     * @return
     */
    public Item[] getItems() {
        return items;
    }

    /**
     * Fügt dem Raum ein Item hinzu.
     * 
     * @param item Das hinzuzufügende Item.
     */
    public void addItem(Item item) {
        for (int i = 0; i < items.length; i++) {
            if (items[i] == null) {
                items[i] = item;
                return;
            }
        }
        System.out.println("Room is full.");
    }

    /**
     * Entfernt das angegebene Item aus dem Raum.
     * 
     * @param item Das angegebene Item.
     */
    public void removeItem(Item item) {
        for(int i = 0; i < items.length; i++) {
            if(items[i] != null && items[i].getName().equalsIgnoreCase(item.getName())) {
                items[i] = null;
            }
        }
    }

    /**
     * Liefert das beschriebene Item des Raums oder null, falls das Item in dem Raum
     * nicht existiert.
     * 
     * @param itemName Der Name eines Items, das im Raum existiert.
     * @return Das gesuchte Item oder null.
     */
    public Item getItem(String itemName) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }

    /**
     * Wahr, falls der aktuelle Raum der Zielort ist.
     */
    public boolean isWinningRoom() {
        return winningRoom;
    }
}