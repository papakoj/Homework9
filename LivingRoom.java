import java.util.ArrayList;
import java.util.Random;

public class LivingRoom extends Room {
	private ArrayList<String> states;
	private ArrayList<String> conversation;
	private int lookCounter;
	public int talkCounter;
	public int useCounter;
	private int paperPick;

	public LivingRoom() {
		talkCounter = 0;
		paperPick = 0;
		lookCounter = 0;
		useCounter = 0;
		conversation = new ArrayList<>();
		this.states = new ArrayList<>();
		states.add("You see Shida's picture on the wall. He has a creepy smile that sends shivers down your spine.");
		states.add("You hear some crying.. It seems like it might be coming from the basement.");
		states.add("You see the TV. The news is on. The reporter says, \"The search continues for the 3 kids who were abducted a week ago.\"");
		states.add("The reporter on the TV says, \"There are no signs of where they may have been taken to. Anyone with any information should contact the police.\"");
		states.add("You see a big door in front of you... Could this be the way out?");
		states.add("You see a piece of paper sticking out from under the sofa.");
		states.add("You see a set of stairs to your left.");
		conversation.add("My family is looking for me!");
		conversation.add("I wanna go back home!");
		conversation.add("Shida is so scary.");
		conversation.add("Let's go!");
	}

	public int walk(String direction, Player p) {
		if (direction.equalsIgnoreCase("straight")) {
			System.out.println("You walk to the Front Door. It will lead you out of the house.");
			if (p.inventory.containsKey("Mike") && p.inventory.containsKey("Sarah") && p.inventory.containsKey("John")) {
				System.out.println("You walk out the door with the children . . .");
				System.out.println("You are outside now!");
				System.out.println("The Monster Shida is staring at you scarily!");
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
		} else if (direction.equalsIgnoreCase("right")) {
			System.out.println("You walk into the Kitchen.");
			return 2;
		}
		else {
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
		}else if (lookCounter == 2){
			System.out.println(states.get(3));
			lookCounter++;
			states.remove(3);
		}
		else if (lookCounter > 2) {
			Random x = new Random();
			System.out.println(states.get(x.nextInt(states.size())));
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
		Random x = new Random();
		if (p.inventory.containsKey("Sarah") && (person.equalsIgnoreCase("girl") || person.equalsIgnoreCase("sarah"))) {	
			System.out.println(conversation.get(x.nextInt(conversation.size())));
		} else if (p.inventory.containsKey("John") && (person.equalsIgnoreCase("boy") || person.equalsIgnoreCase("john"))) {
			System.out.println(conversation.get(x.nextInt(conversation.size())));
		} else if (p.inventory.containsKey("Mike") && (person.equalsIgnoreCase("boy") || person.equalsIgnoreCase("mike"))) {
			System.out.println(conversation.get(x.nextInt(conversation.size())));
		} else {
			System.out.println("No response.");
		}
	}

}
