import java.util.ArrayList;
import java.util.Scanner;

public class Parser {

	public Parser() {
	}
	
	/**
	 * Interprets the commands passed in by the user and executes the desired one.
	 * @param in, the Scanner
	 * @param cur, the current room
	 * @param p, the Player
	 * @param rooms, The collection of rooms
	 * @return
	 */
	public Room parse(Scanner in, Room cur, Player p, ArrayList<Room> rooms) {
		String command = in.nextLine();
		String[] words = command.split(" ");
		if (words[0].equalsIgnoreCase("wait") && words.length == 1) {
			cur.Wait(p);
		} else if (words[0].equalsIgnoreCase("pick")  && words.length > 2) {
			if (words[1].equalsIgnoreCase("up")) {
				cur.pickUp(words[2], p);
			}
		} else if (words[0].equalsIgnoreCase("use")  && words.length > 1) {
			cur.use(words[1], p);
		} else if (words[0].equalsIgnoreCase("talk")  && words.length > 2) {
			if (words[1].equalsIgnoreCase("to")) {
				cur.talk(words[2], p);
			}
		} else if (words[0].equalsIgnoreCase("go")  && words.length > 1) {
			int j = cur.walk(words[1], p);
			cur = rooms.get(j);
		} else if (words[0].equalsIgnoreCase("look")  && words.length > 2) {
			if (words[1].equalsIgnoreCase("at")) {
				cur.lookAt(p, words[2]);
			}
		} else if (words[0].equalsIgnoreCase("help")) {
			cur.help();
		} else if (words[0].equalsIgnoreCase("show") && words.length > 1) {
			if (words[1].equalsIgnoreCase("items")) {
				cur.showItems(p);
			}
		} else {
			System.out.println("Sorry, " + command +  " is not a valid command.");
			System.out.println("For a list of valid commands, type help.");
		}
		return cur;}
}
