
public abstract class Room {
	public int walk(String direction, Player p) {return 0;};
	public void pickUp(String item, Player p) {};
	public void use(String item, Player p ) {};
	public void talk(String person, Player p){};
	public void Wait(Player p) {};
	public void lookAt(Player p, String target) {};
	public void help() {
		System.out.println("Wait: wait in the room for one turn (Waiting can reveal more information about your surroundings.)");
		System.out.println("Go <direction>: go in the given direction. e.g right, left, straight, back");
		System.out.println("Talk to <object>: talk to the given object found in the room");
		System.out.println("Pick up <item>: pick up the given item found in the room");
		System.out.println("Use <item>: use the given item found in the player’s inventory");
		System.out.println("Look at <object>: look at the given object found in the room");
	};
	public boolean isGameOver() {return false;}
}
