public class Item {
    private final String name;
    private final String description;

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return this.name;
    }
    public String getDescription() {
        return this.description;
    }
    public void useIn(Room room) {
        Door[] doors = room.getDoors();
        switch(this.name) {
            case "goldkey":
                for(int i = 0; i < doors.length; i++) {
                    if(doors[i] != null && !doors[i].isOpen()) {
                        doors[i].tryToOpen(this);
                    } else if (doors[i] != null && doors[i].isOpen()) {
                        doors[i].lock(this);
                    }
                }
                break;
            case "torch":
                for(int i = 0; i < doors.length; i++) {
                    if(doors[i] != null && !doors[i].isOpen()) {
                        doors[i].tryToOpen(this);
                    }
                }
                break;
            case "treasure":
                System.out.println("Shiny treasure!");
                break;
        }
    }
}
