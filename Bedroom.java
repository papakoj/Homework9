import java.util.ArrayList;
import java.util.Random;

public class Bedroom extends Room {
	String item;
	ArrayList<String> states;
	public int lookCounter;
	public int talkCounter;
	public int useCounter;
	public int gameOver;
	public int phonePick;
	public int keyPick;
	public int doorOpen;

	public Bedroom() {
		talkCounter = 0;
		doorOpen = 0;
		lookCounter = 0;
		useCounter = 0;
		gameOver = 0;
		phonePick = 0;
		keyPick = 0;
		this.states = new ArrayList<>();
		states.add("You look around the room and see signs of a struggle.");
		states.add("You see some blood on the ground.");
		states.add("You see three doors, one to the left, one to the right, and one straight ahead.");
		states.add("The room is cold and dark.");
	}
	public boolean isOpen(Player p){
		if ((p.inventory.containsKey("key") && doorOpen == 0) || doorOpen > 0){
			doorOpen++;
			return true;
		}else{
			return false;
		}
	}
	public int walk(String direction, Player p) {
		if (direction.equalsIgnoreCase("right")) {
			if (isOpen(p) == false) {
				System.out.println("You try to open the door but you can't.");
				System.out.println("Seems like you need a key.");
				return 0;
			} else {
				System.out.println("You use the key to open the door, and enter the Bathroom.");
				p.inventory.remove("key");
				return 1;
			}
		} else if (direction.equalsIgnoreCase("straight")) {
			System.out.println("You walk into the Living Room.");
			return 4;
		} else {
			System.out.println("You can only go right or straight.");
			return 0;
		}
	}

	public void Wait(Player p) {
		if (lookCounter == 0) {
			System.out.println("You wake up in the bed. You are confused and don't know what's going on.");
			lookCounter++;
		} else if (lookCounter == 1) {
			System.out.println("You see a little girl in the corner crying.");
			p.inventory.put("Sarah", "A sad, scared little girl");
			System.out.println("She will follow you now.");
			lookCounter++;
		} else if (lookCounter == 2) {
			System.out.println("You see a phone lying on the ground.");
			lookCounter++;
		} else if  (lookCounter == 3) {
			System.out.println("You look around and see a key on the floor.");
			lookCounter++;
		}
		else if (lookCounter > 3) {
			Random x = new Random();
			System.out.println(states.get(x.nextInt(states.size())));
		}
	}

	public void lookAt(Player p, String target) {
		if (target.equalsIgnoreCase("sarah")) {
			System.out.println("She looks scared and tired.");
		} else if (target.equalsIgnoreCase("phone")) {
			System.out.println("You read the message! It says \"Welcome to my House!\"");
			System.out.println("\"Don't try to contact anyone, or else. . . \"");
		} else if (target.equalsIgnoreCase("key")) {
			System.out.println("\"What is this key for?\", you wonder.");
		} else {
			System.out.println("There is nothing special about " + target + ".");
		}
	}

	public void pickUp(String item, Player p) {
		if (item.equalsIgnoreCase("phone") && phonePick == 0 ) {
			phonePick++;
			System.out.println("You pick up the phone!");
			System.out.println("There is a message on the screen!");
			p.inventory.put("phone", "You got a message!");
		} else if (item.equalsIgnoreCase("key") && keyPick == 0) {
			keyPick++;
			p.inventory.put("key", "The key to the bathroom");
			System.out.println("You pick up the key!");
			states.remove(2);
		}else{
			System.out.println("you can not pick up " + item);
		}
	}

	public void use(String item, Player p) {
		if (item.equalsIgnoreCase("phone") && useCounter == 0 && p.inventory.containsKey("phone")) {
			System.out.println("You read the message! It says \"Welcome to my House!\"");
			System.out.println("Don't try to contact anyone, or else. . . ");
			useCounter++;
		} if (item.equalsIgnoreCase("phone") && useCounter == 1 && p.inventory.containsKey("phone")) {
			System.out.println("You try to call 911 anyway.");
			System.out.println("Before the call is connected, you get another message.");
			System.out.println("It reads \"You have been warned! If you try to contact anyone again, you will feel the consequences!\"");
			useCounter++;
		} else if (item.equalsIgnoreCase("phone") && useCounter > 1 && useCounter != 5 && useCounter != 10 && p.inventory.containsKey("phone")) {
			System.out.println("You decide not to use the phone, in order to stay safe.");
			useCounter++;
		} else if (item.equalsIgnoreCase("phone") && useCounter == 5 && p.inventory.containsKey("phone")) {
			System.out.println("The light fixture falls off the ceiling and hits you on the arm. You begin to bleed.");
			p.blood -= 10;
			System.out.println("You receive another message.");
			System.out.println("\"You didn't listen to the instructions. Stop trying to call and you'll be safe.\"");
			p.printStats();
			useCounter++;
		} else if (item.equalsIgnoreCase("phone") && useCounter == 10 && p.inventory.containsKey("phone")) {
			System.out.println("The phone exlpodes next to your face. You die instantly.");
			System.out.println("You should have probably followed the instructions.");
			System.out.println("GAME OVER!!");
			this.gameOver = 1;
		}else if (item.equalsIgnoreCase("food") && p.inventory.containsKey("food")) {
			p.eat();
			p.printStats();
		}else {
			System.out.println("You cannot use " + item + ".");
		}
	}

	public void talk(String person, Player p) {
		if (p.inventory.containsKey("Sarah") && (person.equalsIgnoreCase("girl") || person.equalsIgnoreCase("sarah"))) {
			if (talkCounter == 0) {
				System.out.println("She calls out to you, \"Please save me.\"");
				talkCounter++;
			} else if (talkCounter == 1) {
				System.out.println("You ask her.. \"What is your name?\"");
				System.out.println("\"My name is Sarah\", she says.");
				talkCounter++;
			} else if (person.equalsIgnoreCase("sarah") || person.equalsIgnoreCase("girl")) {
				if (talkCounter == 2) {
					System.out.println("\"How did you get here?\" you ask.");
					System.out.println("Me and my friends were caught by Monster Shida and we were stuck in this house for a week.");
					System.out.println("Please take us away from him.");
					talkCounter++;
				} else if (talkCounter > 2) {
					System.out.println("She seems too scared to talk.");
				}
			}
		} else {
			System.out.println("No response.");
		}
	}

	public boolean isGameOver() {
		if (gameOver == 1) {
			return true;
		}
		return false;
	}

}
