//import java.util.ArrayList;
import java.util.Scanner;

public class TextAdventure {
	
	/**
	 * The main executable responsible for starting the game.
	 * @param args
	 */
	public static void main(String[] args) {
		Game g = new Game();
		Scanner in = new Scanner(System.in);
		g.play();
		in.close();
	}
	
}
