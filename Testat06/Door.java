public class Door {
    private Item neededKey;
    private boolean open;
    private Room[] rooms;
    private String[] descriptions;

    /**
     * Konstruktor
     */
    public Door() {
        this.neededKey = null;
        this.open = true;
        this.rooms = new Room[2];
        this.descriptions = new String[2];
    }

    /**
     * Beschreibt die Tür aus sicht des gegebenen Raums.
     * 
     * @param fromRoom Der gegebene Raum.
     * @return Die Beschreibung oder null, falls der Raum nicht mit dieser Tür
     *         verbunden ist.
     */
    public String getDescription(Room fromRoom) {
        for (int i = 0; i < rooms.length; i++) {
            if (this.rooms[i] == fromRoom) {
                return this.descriptions[i];
            }
        }
        System.err.println("The provided room is not connected to the door!");
        return null;
    }

    /**
     * Ist diese Tür offen?
     */
    public boolean isOpen() {
        return this.open;
    }

    /**
     * Fügt die Tür einem Raum in gegebener Richtung hinzu.
     * 
     * @param room      Der Raum.
     * @param direction Die Richtung.
     */
    private void addToRoom(Room room, String direction) {
        if (direction.equalsIgnoreCase("north")) {
            room.getDoors()[0] = this;
        } else if (direction.equalsIgnoreCase("south")) {
            room.getDoors()[1] = this;
        } else if (direction.equalsIgnoreCase("east")) {
            room.getDoors()[2] = this;
        } else if (direction.equalsIgnoreCase("west")) {
            room.getDoors()[3] = this;
        } else {
            System.err.println("Could not add door: direction does not exist!");
            return;
        }
    }

    /**
     * Connects to rooms with this door
     * 
     * @param room1        The first room
     * @param direction1   The direction of this door in the first room (east, west,
     *                     south, east)
     * @param description1 The description of this door from the first room's
     *                     perspective
     * @param room2        The second room
     * @param direction2   The direction of this door in the second room (east,
     *                     west, south, east)
     * @param description2 The description of this door from the second room's
     *                     perspective
     * @param key          An item that must be used to unlock the door. If null:
     *                     door stays unlocked.
     */
    public void connectRooms(Room room1, String direction1, String description1,
            Room room2, String direction2, String description2, Item key) {
        this.rooms[0] = room1;
        this.rooms[1] = room2;

        this.descriptions[0] = description1;
        this.descriptions[1] = description2;

        if (key != null) {
            this.lock(key);
        }

        this.addToRoom(room1, direction1);
        this.addToRoom(room2, direction2);
    }

    /**
     * Returns the other room that is connected to the provided currentRoom via this
     * door.
     * 
     * @param currentRoom
     * @return null, if the current room can't be reached through this door.
     */
    public Room getOtherRoom(Room currentRoom) {
        if (this.rooms[0] == currentRoom) {
            return this.rooms[1];
        } else if (this.rooms[1] == currentRoom) {
            return this.rooms[0];
        } else {
            System.err.println("The current room is not connected to this one!");
            return null;
        }
    }

    /**
     * Locks the door using a key-item.
     * 
     * @param key the key that needs to be found. Can't be null.
     */
    public void lock(Item key) {
        if (key == null) {
            System.err.println("Can't lock room without a key");
        } else {
            this.neededKey = key;
            this.open = false;
        }
    }

    /**
     * Compares provided key to needed key and unlocks door if they match
     * 
     * @param key Item to try to unlock the door with.
     * @return if success: true.
     */
    public boolean tryToOpen(Item key) {
        if (key == this.neededKey) {
            this.open = true;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Describes the door, including its status.
     * 
     * @return a String describing the door.
     */
    public String getFullDescription(Room currentRoom) {
        String descr = getDescription(currentRoom);
        if (descr != null) {
            String fullDesc = descr + " It appears to be ";
            if (open) {
                fullDesc += "unlocked. ";
            } else {
                fullDesc += "locked. ";
            }

            return fullDesc;
        } else {
            return "This door is not visible from the current room.";
        }
    }

}