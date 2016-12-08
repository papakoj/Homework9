import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class Bathroom extends Room {
	//ArrayList<String> item;
	ArrayList<String> states;
	public int lookCounter;
	public int talkCounter;
	public int useCounter;
	public int notePick;
	
	public Bathroom() {
		//this.item = new ArrayList<>();
		talkCounter = 0;
		lookCounter = 0;
		useCounter = 0;
		notePick = 0;
		//item.add("Note");
		//		item.add("Drawer");
		this.states = new ArrayList<>();
		states.add("You look around the bathroom and see a little boy.");
		states.add("You look around and see a note.");
		states.add("You look around and find nothing special.");
		states.add("You see your pale face in the mirror.");
		states.add("You see a lot of magezines with Shida's face on the cover.");
	}

	public int walk(String direction, Player p) {
		if (direction.equalsIgnoreCase("left")) {
			System.out.println("You walk back into the Bedroom.");
			return 0;
		} else {
			System.out.println("You can only go left.");
			return 1;
		}
	}

	public void Wait(Player p) {
		if (lookCounter == 0) {
			System.out.println(states.get(0));
			p.inventory.put("John", "A sad, scared little boy");
			lookCounter++;
			states.remove(0);
		} else if (lookCounter == 1) {
			System.out.println(states.get(0));
			lookCounter++;
			states.remove(0);
		}
		else if (lookCounter > 1) {
			Random x = new Random();
			System.out.println(states.get(x.nextInt(3)));
		}
	}

	public void lookAt(Player p, String target) {
		if (target.equalsIgnoreCase("note")) {
			System.out.println("The note says \" In order to get out of this house, you need to eat and find enough weapons to fight against Monster Shida.\"");
		} else if (target.equalsIgnoreCase("john")) {
			System.out.println("He is crying silently.");
		} else {
			System.out.println("There is nothing special about " + target + ".");
		}
	}

	public void pickUp(String item, Player p) {
		if (item.equalsIgnoreCase("note") && notePick == 0) {
			notePick++;
			p.inventory.put("note", "a note");
			System.out.println("The note says \" In order to get out of this house, you need to eat and find enough weapons to fight against Monster Shida.\"");
		}else{
			System.out.println("you can not pick up " + item);
		}
	}

	public void use(String item, Player p) {
		if (item.equalsIgnoreCase("food") && p.inventory.containsKey("food")) {
			p.eat();
			p.printStats();
		} else {
			System.out.println("You cannot use " + item + ".");
		}
	}
	public void talk(String person, Player p) {
		if (p.inventory.containsKey("John") && (person.equalsIgnoreCase("john") || person.equalsIgnoreCase("boy"))){
			if (talkCounter == 0) {
				System.out.println("He says, \"Please save me.\"");
				talkCounter++;
			} else if (talkCounter == 1) {
				System.out.println("You ask him.. \"What's your name?\"");
				System.out.println("\"My name is John\", he says.");
				talkCounter++;
			} else if (person.equalsIgnoreCase("John") || person.equalsIgnoreCase("boy")) {
				if (talkCounter == 2) {
					System.out.println("\"What is going on here?\" you ask.");
					System.out.println("Please save me!! Monster Shida took me away from my family! I miss them.");
					System.out.println("Please take me away from him.");
					talkCounter++;
				} else if (talkCounter > 2) {
					System.out.println("He seems too scared to talk.");
				}
			}
		} else {
			System.out.println("No response.");
		}
	}

	public void help() {
		System.out.println("Wait: wait in the room for one turn (Waiting can reveal more information about your surroundings.)");
		System.out.println("Go <direction>: go in the given direction. e.g right, left, straight, back");
		System.out.println("Talk to <object>: talk to the given object found in the room");
		System.out.println("Pick up <item>: pick up the given item found in the room");
		System.out.println("Use <item>: use the given item found in the player’s inventory");
		System.out.println("Look at <object>: look at the given object found in the room");
	}
}
