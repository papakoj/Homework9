import java.util.HashMap;
import java.util.Map;

public class Player {

	String name;
	public int blood;
	public boolean hasEaten;
	Map<String, String> inventory;
	public int gameOver;

	public Player(String name) {
		this.name = name;
		blood = 50;
		hasEaten = false;
		inventory = new HashMap<String, String>();
		gameOver = 0;
	}

	/**
	 * Attacks Shida with the specified item
	 * @param item, the item to be used on Shida
	 * @param s, Shida
	 */
	public void attack(String item, Shida s) {
		if (s.blood > 0) {
			if (item.equalsIgnoreCase("knife") && inventory.containsKey("knife")) {
				System.out.println("You use the knife and Shida loses 30% blood.");
				System.out.println("He knocks the knife out of your hand, and it flies out of sight.");
				inventory.remove("knife");
				s.blood -= 30;
				if (s.blood <= 0 || blood <= 0 || s.isGameOver()) {
					gameOver = 1;
					System.out.println("You defeated the Monster Shida!!!");
					System.out.println("The children are now free and safe!!");
					System.out.println("YOU ARE THE HERO!!!");
				}
			} else if (item.equalsIgnoreCase("knife") && !inventory.containsKey("knife")) {
				System.out.println("You don't have the knife! Shida knocked it away!");
				s.attack(this);
				if (blood <= 0 || s.isGameOver()) {
					gameOver = 1;
				}
			} else if (item.equalsIgnoreCase("gun") && inventory.containsKey("gun")) {
				System.out.println("You shoot Shida with the gun!");
				System.out.println("He falls to the ground!");
				s.blood -= 70;
				inventory.remove("gun");
				if (s.blood <= 0 || blood <= 0 || s.isGameOver()) {
					gameOver = 1;
					System.out.println("You defeated the Monster Shida!!!");
					System.out.println("The children are now free and safe!!");
					System.out.println("YOU ARE THE HERO!!!");
				}
			}
		} else {
			System.out.println("Shida is already dead! You cannot fight him.");
		}
	}
	/**
	 * Print out the player's name and the amount of life they have
	 */
	public void printStats() {
		System.out.println("STATS");
		System.out.println("Player " + this.name);
		System.out.println("Life: " + Math.max(this.blood, 0));
	}
	
	/**
	 * Replenishes the players life
	 */
	public void eat() {
		System.out.println("You eat the food!");
		blood = 100;
		hasEaten = true;
		inventory.remove("food");
	}
	
	/**
	 * Checks if the game is over
	 * @return
	 */
	public boolean isGameOver() {
		if (gameOver == 1) {
			return true;
		}
		return false;
	}
}
