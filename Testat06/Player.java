public class Player {
    private String name;
    private Room currentRoom;
    private Item[] inventory;

    /**
     * Constructor für Player-Klasse.
     * 
     * @param name         Name des Spielers
     * @param startingRoom Raum in dem der Spieler initialisiert wird.
     */
    public Player(String name, Room startingRoom) {
        this.name = name;
        this.currentRoom = startingRoom;
        this.inventory = new Item[10];
    }

    /**
     * Liefert den Namen.
     */
    public String getName() {
        return name;
    }

    /**
     * Liefert den aktuellen Raum, in dem Spieler*innen sich aufhalten
     * 
     * @return
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     * Bewegt Spieler*innen in den nächsten Raum in der angegebenen Richtung, sofern
     * eine Tür vorhanden und nicht verschlossen ist.
     * 
     * @param direction
     */
    public void move(String direction) {
        Door door = this.currentRoom.getDoorInDirection(direction);
        if(door != null && door.isOpen()) {
            this.currentRoom = door.getOtherRoom(this.currentRoom);
        }

    }

    /**
     * Ein Item mit dem angegebenen Namen aufheben, falls es in dem aktuellen Raum
     * existiert.
     * 
     * @param itemName
     */
    public void takeItem(String itemName) {
        Item item = this.currentRoom.getItem(itemName);
        if(item != null) {
            for(int i = 0; i < inventory.length; i++) {
                if(inventory[i] == null) {
                    inventory[i] = item;
                    this.currentRoom.removeItem(item);
                    return;
                }
            }
        }

    }

    /**
     * Ein Item aus dem Inventar in dem aktuellen Raum benutzen.
     * 
     * @param itemName
     */
    public void useItem(String itemName) {
        for(Item item : inventory) {
            if(item != null && itemName.equalsIgnoreCase(item.getName())) {
                item.useIn( this.currentRoom);
            }
        }
    }

    public void openInventory() {
        System.out.println("-------------------------");
        System.out.println("Your inventory:");
        boolean empty = true;
        for(Item item : inventory) {
            if(item != null) {
                System.out.println(item.getName() + ": " + item.getDescription());
                empty = false;
            }

        }
        if(empty) {
            System.out.println("You have no items in your inventory.");
        }
        System.out.println("-------------------------");
    }
}