public class Combat {

    private Player player;
    private StringBuilder playerHP;
    private StringBuilder enemyHP;
    private int playerDamage;
    private int enemyDamage;
    private boolean hasTreasure;

    public Combat(Player player) {
        this.playerHP = new StringBuilder("oooooooooooo");
        this.enemyHP = new StringBuilder("oooooooooooooooooooo");
        this.hasTreasure = false;
        this.player = player;
        this.playerDamage = 1;
        this.enemyDamage = 1;
    }

    public boolean startCombat() {
        Item[] inventory = player.getInventory();
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] != null && inventory[i].getName().equalsIgnoreCase("treasure")) {
                this.hasTreasure = true;
                this.playerDamage = 2;
            }
        }
        System.out.println("You face a fearsome opponent.");

        while (!enemyHP.isEmpty() && !playerHP.isEmpty()) {
            System.out.println(player.getName() + " HP: " + playerHP + "\n Opponent HP: "  + enemyHP);
            if(hasTreasure) {
                System.out.println("You whack your opponent using your shiny treasure, dealing " + playerDamage + " damage. \n Your opponent retaliates, dealing 1 Damage.");
            } else {
                System.out.println("You strike your opponent using your bare fist, dealing " + playerDamage + " damage. \n Your opponent retaliates, dealing 1 Damage.");
            }

            playerHP.setLength(playerHP.length() - enemyDamage);
            enemyHP.setLength(enemyHP.length() - playerDamage);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }

        }
        System.out.println(player.getName() + " HP: " + playerHP + "\n Opponent HP: "  + enemyHP);
        return enemyHP.isEmpty();

    }
}
