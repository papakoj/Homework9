import java.util.ArrayList;
import java.util.Scanner;

public class Parser {
	//	private Scanner in;

	public Parser() {
		//		in = new Scanner(System.in);
	}

	public Room parse(Scanner in, Room cur, Player p, ArrayList<Room> rooms) {
		String command = in.nextLine();
		String[] words = command.split(" ");
		if (words[0].equalsIgnoreCase("wait")) {
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
		}else if (words[0].equalsIgnoreCase("go")  && words.length > 1) {
			int j = cur.walk(words[1], p);
			cur = rooms.get(j);
		} else if (words[0].equalsIgnoreCase("look")  && words.length > 2) {
			if (words[1].equalsIgnoreCase("at")) {
				cur.lookAt(p, words[2]);
			}
		} else if (words[0].equalsIgnoreCase("help")) {
			cur.help();
		} else {
			System.out.println("Sorry, " + command +  " is not a valid command.");
			System.out.println("For a list of valid commands, type help.");
		}
		return cur;}
}
