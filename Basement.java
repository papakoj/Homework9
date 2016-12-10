import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Basement extends Room {
	ArrayList<String> states;
	public int lookCounter;
	public int talkCounter;
	public int useCounter;
	public int openCounter;
	public int gameOver;
	public int gunPick;
	public int boxPick;
	public boolean isOpen = false;

	public Basement() {
		talkCounter = 0;
		gunPick = 0;
		boxPick = 0;
		lookCounter = 0;
		openCounter = 0;
		useCounter = 0;
		gameOver = 0;
		this.states = new ArrayList<>();
		states.add("You see a little boy crying in the corner.");
		states.add("The basement is really dark and cold.");
		states.add("You look around and find a box under the desk.");
		states.add("You hear someone's footsteps. Was that from Shida?");
		states.add("You see a set of stairs to your right");
		states.add("You see a creepy doll on the floor. Could that be Jacobsen?");
	}

	public int walk(String direction, Player p) {
		if (direction.equalsIgnoreCase("right")) {
			System.out.println("You see a set of stairs leading back to the livingroom.");
			System.out.println("You walk up to the living room.");
			return 4;
		} else {
			System.out.println("You can only go right.");
			return 3;
		}
	}

	public void Wait(Player p) {
		if (lookCounter == 0) {
			System.out.println(states.get(0));
			p.inventory.put("Mike", "A boy found in basement");
			System.out.println("The boy will follow you now.");
			states.remove(0);
			lookCounter++;
		} else if(lookCounter == 1){
			System.out.println(states.get(1));
			states.remove(1);
			lookCounter++;
		}
		else if (lookCounter > 1) {
			Random x = new Random();
			System.out.println(states.get(x.nextInt(states.size())));
		}
	}

	public void lookAt(Player p, String target) {
		if (target.equalsIgnoreCase("mike") || target.equalsIgnoreCase("boy")) {
			System.out.println("He's a sad boy with curly, brown hair.");
		} else if (target.equalsIgnoreCase("box")) {
			if (p.inventory.containsKey("gun")) {
				System.out.println("The box is empty now.");
			} else {
				System.out.println("The box is locked.");
			}
		} else if (target.equalsIgnoreCase("gun")) {
			System.out.println("You only have 1 bullet in the gun. Use it wisely.");
		} else {
			System.out.println("There is nothing special about " + target + ".");
		}
	}

	public void pickUp(String item, Player p) {
		if (item.equalsIgnoreCase("box") && boxPick == 0) {
			boxPick++;
			p.inventory.put("box", "Box contains gun");
			System.out.println("The box is locked. What is the passwords? We need to open it.");
		} else if (item.equalsIgnoreCase("gun") && gunPick == 0 && isOpen == true) {
			gunPick++;
			p.inventory.put("gun", "A gun with one bullet. Let's use it wisely.");
			p.inventory.put("box", "an empty box that used to contain a gun");
			System.out.println("Wow! We are ready to fight now.");
			System.out.println("Unfortunately, the gun only has one bullet!");
			System.out.println("We need to use this very carefully.");
		} else if ((item.equalsIgnoreCase("doll") || item.equalsIgnoreCase("Jacobsen")) && !p.inventory.containsKey("Doll")) {
			System.out.println("You pick up the doll. It IS Jacobsen!");
			System.out.println("You take Jacobsen, maybe it could come in handy.");
			p.inventory.put("Doll", "Jacobsen, Shida's favourite doll");
			states.remove(states.size()-1);
		}
		else {
			System.out.println("you can not pick up " + item);
		}
	}


	public void open(String item, Player p, Scanner in){
		if (p.inventory.containsKey("box")){
			if (item.equalsIgnoreCase("box") && !isOpen){
				System.out.println("What should the password be?");
				if (in.next().equals("207")){
					System.out.println("Great! You opened it! There is a gun in it.");
					isOpen = true;
				}else if (openCounter < 5) {
					System.out.println("No!!! Wrong Password!! Please try again.");
					openCounter++;
				} else if (openCounter >= 5) {
					System.out.println("The box explodes, and you die!");
					System.out.println("GAME OVER!!!");
					gameOver = 1;
				}
			} else {
				System.out.println("The box is already open!");
			}
		}
	}

	public void use(String item, Player p) {
		if (p.inventory.containsKey("box") && item.equalsIgnoreCase("box")) {
			open("box", p, new Scanner(System.in));
		} else if (p.inventory.containsKey("gun") && item.equalsIgnoreCase("gun") ) {
			System.out.println("You don't want to waste the bullet.");
		} else if (item.equalsIgnoreCase("food") && p.inventory.containsKey("food")) {
			p.eat();
			p.printStats();
		} else {
			System.out.println("You cannot use " + item);
		}
	}
	
	
	public void talk(String person, Player p) {
		if (p.inventory.containsKey("Mike") && (person.equalsIgnoreCase("mike") || person.equalsIgnoreCase("boy"))) {
			if (talkCounter == 0) {
				System.out.println("He calls out to you, \"Please save me.\"");
				talkCounter++;
			} else if (talkCounter == 1) {
				System.out.println("You ask him.. \"What is your name?\"");
				System.out.println("\"My name is Mike\", he says.");
				talkCounter++;
			} else if (person.equalsIgnoreCase("mike") || person.equalsIgnoreCase("boy")) {
				if (talkCounter == 2) {
					System.out.println("\"How did you get here?\" you ask.");
					System.out.println("I was caught by Monster Shida and stuck in this house for a week.");
					System.out.println("Please take me away from him.");
					talkCounter++;
				} else if (talkCounter > 2) {
					System.out.println("He seems too scared to talk.");
				}
			} else {
				System.out.println("No response.");
			}
		}
	}
	
	
	public boolean isGameOver() {
		if (gameOver == 1) {
			return true;
		}
		return false;
	}
	
}
