import java.util.ArrayList;
import java.util.Random;

public class Outside extends Room {
	ArrayList<String> item;
	ArrayList<String> states;
	int lookCounter;
	int talkCounter;
	int gunCounter;
	int gameOver;
	Shida s;

	public Outside() {
		this.item = new ArrayList<>();
		talkCounter = 0;
		lookCounter = 0;
		gunCounter = 0;
		item.add("Note");
		gameOver = 0;
		s = new Shida();
		this.states = new ArrayList<>();
		states.add("You look around and see the Monster Shida looking at you.");
		states.add("It's snowing outside and the kids are cold. You'll have to take them somewhere warm quickly.");
		states.add("You look around and find nothing special.");
		states.add("Shida seems to be ready for a fight.");
		states.add("The children are scared of Monster Shida and start crying.");
		states.add("You are still outside.");
		states.add("Shida can't wait to fight with you.");
	}

	public int walk(String direction, Player p) {
		if (direction.equalsIgnoreCase("back")) {
			System.out.println("You try to run back into the house but the door is locked now.");
			System.out.println("Monster Shida stabs you in the back!!!");
			System.out.println("GAME OVER!!!!");
			gameOver = 1;
			return 5;
		} else {
			System.out.println("You have to defeat Shida before you can walk anywhere.");
			return 5;
		}
	}

	public void Wait(Player p) {
		if (lookCounter == 0) {
			System.out.println(states.get(0));
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
		if (target.equalsIgnoreCase("Shida")) {
			System.out.println("Shida has glowing red eyes and a scary smile.");
		} else {
			System.out.println("You are too distracted by Shida to look at " + target + ".");
		}
	}

	public void pickUp(String item, Player p) {
		System.out.println("you can not pick up " + item);
	}

	public void use(String item, Player p) {
		useH(item, p, s);
	}

	public void useH(String item, Player p, Shida s) {
		if (item.equalsIgnoreCase("food") && p.inventory.containsKey("food")) {
			p.eat();
			p.printStats();
		} else if (item.equalsIgnoreCase("knife") || item.equalsIgnoreCase("gun")) {
			if (p.inventory.containsKey(item)) {
				p.attack(item, s);
				if (!p.isGameOver()) {
					p.printStats();
					s.printStats();
				}
			} else {
				System.out.println("You don't have " + item);
				s.attack(p);
				if (!p.isGameOver()) {
					p.printStats();
					s.printStats();
				}
			}

		} else {
			System.out.println("You cannot use " + item + " to fight Shida!!!");
			s.attack(p);
			if (!p.isGameOver()) {
				p.printStats();
				s.printStats();
			}
		}
	}

	public void talk(String person, Player p) {
		System.out.println("You're too busy fighting against Shida to talk!!");
	}


	public boolean isGameOver() {
		if (gameOver == 1) {
			return true;
		}
		return false;
	}
}
