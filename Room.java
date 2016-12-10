
public abstract class Room {
	/**
	 * moves the player in the direction specified
	 * @param direction, the direction in which to move
	 * @param p, the Player
	 * @return
	 */
	public int walk(String direction, Player p) {return 0;};
	
	/**
	 * Commands the player to pick up the item and add it to their inventory
	 * @param item, the item to be picked up
	 * @param p, the Player
	 */
	public void pickUp(String item, Player p) {};
	
	/**
	 * Commands the player to use the specified item
	 * @param item, the item to be used
	 * @param p, the Player
	 */
	public void use(String item, Player p ) {};
	
	/**
	 * Commands the player to talk to the specified person
	 * @param person, the person to be spoken to
	 * @param p, the Player
	 */
	public void talk(String person, Player p){};

	/**
	 * Advances the game one turn and reveals more information about the surroundings
	 * @param p, the Player
	 */
	public void Wait(Player p) {};
	
	/**
	 * Commands the player to look at the target. Can reveal more information about the target
	 * @param p, the Player
	 * @param target, the item to be looked at
	 */
	public void lookAt(Player p, String target) {};

	/**
	 * Prints a list of valid commands to the console
	 */
	public void help() {
		System.out.println("Wait: wait in the room for one turn (Waiting can reveal more information about your surroundings.)");
		System.out.println("Go <direction>: go in the given direction. e.g right, left, straight, back");
		System.out.println("Talk to <object>: talk to the given object found in the room");
		System.out.println("Pick up <item>: pick up the given item found in the room");
		System.out.println("Use <item>: use the given item found in the player’s inventory");
		System.out.println("Look at <object>: look at the given object found in the room");
		System.out.println("Show items: Prints a list of the items (or people) you have with you.");
	};
	
	/**
	 * Prints a list of the items in the player's inventory
	 * @param p, the Player
	 */
	public void showItems(Player p) {
		if (p.inventory.size() == 0) {
			System.out.println("You have no items.");
		} else {
			System.out.println("Inventory");
			for (String m : p.inventory.keySet()) {
				System.out.println(m + ": " + p.inventory.get(m));
			}
			System.out.println();
		}
	}
	
	/**
	 * Returns whether or not the game is over
	 * @return
	 */
	public boolean isGameOver() {return false;}
}
