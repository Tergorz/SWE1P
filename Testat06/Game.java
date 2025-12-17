public class Game {

    private Player player;

    public Game(String playerName, Room startRoom) {
        this.player = new Player(playerName, startRoom);
    }

    public static void main(String[] args) {
        // Initiieren
        System.out.print("Hi there, I am ready to start the game. What is your name? ");
        String playerName = Input.readString();

        // Räume anlegen
        Room woods = new Room("You are in a dense, dark forest.", false);
        Room clearing = new Room("You emerge into a small clearing.", false);
        Room cave = new Room("You enter a dimly lit cave.", false);
        Room treasureRoom = new Room("You find the treasure room.", false);
        Room outsideSafety = new Room("You finally find the world outside the woods.", true);

        // Gegenstände anlegen
        Item torch = new Item("torch", "A burning torch.");
        Item goldKey = new Item("goldkey", "A golden key.");
        Item treasure = new Item("treasure", "The treasure from the treasure room.");

        // Räume verbinden
        Door woodsClearingDoor = new Door();
        woodsClearingDoor.connectRooms(woods, "east", "A gap between the trees.",
                clearing, "west", "The woods getting denser.", null);

        Door woodsCaveDoor = new Door();
        woodsCaveDoor.connectRooms(woods, "west", "A bright red door.",
                cave, "east", "A bright red door.", null);

        Door caveTreasureDoor = new Door();
        caveTreasureDoor.connectRooms(cave, "north", "A golden door.",
                treasureRoom, "south", "A golden door.", goldKey);

        Door finalDoor = new Door();
        finalDoor.connectRooms(treasureRoom, "east", "A simple wooden door.",
                outsideSafety, "west", "A burned wooden door.", torch);

        // Gegenstände verteilen
        woods.addItem(torch);
        clearing.addItem(goldKey);
        treasureRoom.addItem(treasure);

        // Spiel starten
        Game game = new Game(playerName, woods);
        game.startLoop();
    }

    /**
     * Startet die Hauptschleife des Spiels
     */
    private void startLoop() {

        // Game Loop
        while (true) {
            describeRoom();
            System.out.print("> ");
            String input = Input.readString();

            if (input.equals("quit")) {
                break;
            }
            parseInput(input);

            // Check if the final room is unlocked
            if (player.getCurrentRoom().isWinningRoom()) {
                Combat boss = new Combat(player);
                if(boss.startCombat()) {
                    System.out.println("Congratulations " + player.getName() + ", you win!");
                } else {
                    System.out.println("Game over.");
                }
                break;

            }

        }
    }

    /**
     * Beschreibt den aktuellen Raum inkl. Gegenstände und Türen
     */
    private void describeRoom() {
        System.out.println(this.player.getCurrentRoom().getDescription());

        // Alle Items im Raum auflisten
        System.out.println("You see the following items: ");
        for (Item i : this.player.getCurrentRoom().getItems()) {
            if (i != null) {
                System.out.println("- " + i.getName() + ": " + i.getDescription());
            }
        }

        // Alle Ausgänge des Raums auflisten
        System.out.println("You can move in the following directions:");
        Door[] doors = this.player.getCurrentRoom().getDoors();
        for (int i = 0; i < 4; i++) {
            if (doors[i] != null) {
                // I don't like this hard-coded encoding of the directions in multiple places.
                // Should at least be constants, better enums or part of the Door class.
                switch (i) {
                    case 0:
                        System.out.print("- north: ");
                        break;
                    case 1:
                        System.out.print("- south: ");
                        break;
                    case 2:
                        System.out.print("- east: ");
                        break;
                    case 3:
                        System.out.print("- west: ");
                        break;
                    default:
                        break;
                }
                System.out.println(doors[i].getFullDescription(this.player.getCurrentRoom()));
            }
        }
    }

    /**
     * Nimmt die Eingaben entgegen und führt entsprechende Aktionen aus.
     * 
     * @param input Die Nutzereingabe, die geparst werden soll.
     */
    public void parseInput(String input) {

        String[] words = input.split(" ");
        String command = words[0];

        if (command.equals("move")) {
            if (words.length > 1) {
                String direction = words[1];
                player.move(direction);
            } else {
                System.out.println("Please specify a direction.");
            }
        } else if (command.equals("take")) {
            if (words.length > 1) {
                String itemName = words[1];
                player.takeItem(itemName);
            } else {
                System.out.println("Please specify an item to take.");
            }
        } else if (command.equals("use")) {
            if (words.length > 1) {
                String itemName = words[1];
                player.useItem(itemName);
            } else {
                System.out.println("Please specify an item to use.");
            }
        } else if (command.equals("inventory")) {
            player.openInventory();
        } else {
            System.out.println("Invalid command.");
        }
    }
}
