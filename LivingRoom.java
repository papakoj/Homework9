import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class LivingRoom extends Room {
	//ArrayList<String> item;
	ArrayList<String> states;
	public int lookCounter;
	public int talkCounter;
	public int useCounter;
	public int paperPick;
	
	public LivingRoom() {
		//this.item = new ArrayList<>();
		talkCounter = 0;
		paperPick = 0;
		lookCounter = 0;
		useCounter = 0;
		//item.add("Password");
		//		item.add("Drawer");
		this.states = new ArrayList<>();
		states.add("You see Shida's picture on the wall. He has a creepy smile that sends shivers down your spine.");
		states.add("You hear some crying.. It seems like it might be coming from the basement.");
		states.add("You see the TV. The news is on. The reporter says, \"The search continues for the 3 kids who were abducted a week ago.\"");
		states.add("The reporter on the TV says, \"There are no signs of where they may have been taken to. Anyone with any information should contact the police.\"");
		states.add("You see a big door in front of you... Could this be the way out?");
		states.add("You see a piece of paper sticking out from under the sofa.");
	}

	public int walk(String direction, Player p) {
		if (direction.equalsIgnoreCase("straight")) {
			System.out.println("You walk to the Front Door. It will lead you out of the house.");
			if (p.inventory.containsKey("Mike") && p.inventory.containsKey("Sarah") && p.inventory.containsKey("John")) {
				System.out.println("You walk out the door with the children . . .");
				return 5;
			} else {
				System.out.println("You think you have forgotten something . . . ");
				System.out.println("Weren't you supposed to save all of Sarah's friends?");
				System.out.println("You are back in the living room now.");
				return 4;
			}
		} else if (direction.equalsIgnoreCase("left")) {
			System.out.println("You walk around and see a set of stairs leading down to the basement.");
			System.out.println("You walk down the stairs, ");
			return 3;
		} else if (direction.equalsIgnoreCase("back")) {
			System.out.println("You walk back into the Bedroom.");
			return 0;
		} else {
			System.out.println("You can only go straight, left or back.");
			return 4;
		}
	}

	public void Wait(Player p) {
		if (lookCounter == 0) {
			System.out.println(states.get(2));
			lookCounter++;
			states.remove(2);
		} else if (lookCounter == 1) {
			System.out.println(states.get(2));
			lookCounter++;
			states.remove(2);
		}
		else if (lookCounter > 1) {
			Random x = new Random();
			System.out.println(states.get(x.nextInt(3)));
		}
	}
	
	public void lookAt(Player p, String target) {
		if (target.equalsIgnoreCase("tv")) {
			System.out.println("The news is still on the TV.");
		} else if (target.equalsIgnoreCase("picture")) {
			System.out.println("Shida looks creepy on the picture. You should stop looking at it.");
		} else if (target.equalsIgnoreCase("paper")) {
			System.out.println("The paper says \"207\"");
		} else {
			System.out.println("There is nothing special about " + target + ".");
		}
	}

	public void pickUp(String item, Player p) {
		if (item.equalsIgnoreCase("paper") && paperPick == 0) {
			paperPick++;
			p.inventory.put("paper", "207");
			System.out.println("You pick up the paper and read it.");
			System.out.println("The paper says \"207\"");
			System.out.println("You are confused about what it means, so you put it in your pocket for later.");
		}else{
			System.out.println("you can not pick up " + item + ".");
		}
	}

	public void use(String item, Player p) {
		if (item.equalsIgnoreCase("paper")) {
			if (p.inventory.containsKey("paper")) {
				System.out.println("You take out the paper and read it.");
				System.out.println("The paper says \"207\"");
				System.out.println("You are confused about why you are wasting time using paper instead of finding the children.");
			}
		} else if (item.equalsIgnoreCase("food") && p.inventory.containsKey("food")) {
			p.eat();
			p.printStats();
		} else {
			System.out.println("You can't use " + item + ".");
		}
	}

	public void talk(String person, Player p) {
		System.out.println(" No response. ");
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
