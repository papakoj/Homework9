import java.util.ArrayList;
import java.util.Random;

public class Kitchen extends Room {
	public ArrayList<String> states;
	private int lookCounter;
	private int talkCounter;
	private int useFridge;
	private int knifePick;
	private int foodPick;
	private ArrayList<String> conversation;

	public Kitchen() {
		talkCounter = 0;
		lookCounter = 0;
		useFridge = 0;
		knifePick = 0;
		foodPick = 0;
		this.states = new ArrayList<>();
		this.conversation = new ArrayList<>();
		states.add("You look around and find a fridge.");
		states.add("You look around and find a knife.");
		states.add("You see some dirty plates in the sink.");
		states.add("The light is flickering.");
		states.add("You see the door leading to the bedroom.");
		conversation.add("You should eat so you can fight Shida.");
		conversation.add("Let's get out of here quickly.");
		conversation.add("I'm happy you found me.");
		conversation.add("Let's go!");
	}

	public int walk(String direction, Player p) {
		if (direction.equalsIgnoreCase("right")) {
			System.out.println("You walk back into the bedroom.");
			return 0;
		} else {
			System.out.println("You can only go right.");
			return 2;
		}
	}


	public void Wait(Player p) {
		if (lookCounter == 0) {
			System.out.println(states.get(0));
			states.remove(0);
			p.inventory.put("fridge", "a fridge you are able to open.");
			lookCounter++;
		} else if (lookCounter == 1) {
			System.out.println(states.get(0));
			states.remove(0);
			lookCounter++;
		}
		else if (lookCounter > 1) {
			Random x = new Random();
			System.out.println(states.get(x.nextInt(states.size())));
		}
	}

	public void lookAt(Player p, String target) {
		if (target.equalsIgnoreCase("fridge")) {
			System.out.println("It's a huge fridge! Wonder what's inside?");
		} else if (target.equalsIgnoreCase("knife")) {
			System.out.println("The knife is big and shiny. Looks like a good weapon!");
		} else if (target.equalsIgnoreCase("dirty plates")) {
			System.out.println("There are so many dirty plates. The Monster Shida does not like doing dishes. ");
		} else if (target.equalsIgnoreCase("food")) {
			System.out.println("Wooow! It is a hotdog! ");
		}else {
			System.out.println("There is nothing special about " + target + ".");
		}
	}

	public void pickUp(String item, Player p) {
		if (item.equalsIgnoreCase("knife") && knifePick == 0) {
			knifePick++;
			p.inventory.put("knife", "a knife to fight");
			System.out.println("You pick up the knife.");
		} else if (item.equalsIgnoreCase("food") && useFridge > 0 && foodPick == 0) {
			foodPick++;
			System.out.println("You take the food. You can eat anytime.");
			p.inventory.put("food", "you can eat now");
		} else{
			System.out.println("you can not pick up " + item);
		}
	}

	public void use(String item, Player p) {
		if (p.inventory.containsKey("fridge")) {
			if (item.equalsIgnoreCase("fridge") && useFridge == 0) {
				System.out.println("You find some food and you can pick it up.");
				useFridge++;
			} else if (item.equalsIgnoreCase("fridge") && useFridge > 0) {
				System.out.println("There is nothing left in the fridge.");
				useFridge++;
			}
		} if (p.inventory.containsKey("knife") && item.equalsIgnoreCase("knife")){		
			System.out.println("You can use the knife to fight later.");
		} if (item.equalsIgnoreCase("food") && p.inventory.containsKey("fridge") && p.inventory.containsKey("food")) {
			p.eat();
			p.printStats();
		}else if (! (p.inventory.containsKey(item))) {
			System.out.println("You cannot use " + item + ".");
		}
	}

	public void talk(String person, Player p) {
		Random x = new Random();
		if (p.blood == 100) {
			conversation.remove(0);
		} 
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


