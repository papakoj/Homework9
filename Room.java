
public abstract class Room {
	public int walk(String direction, Player p) {return 0;};
	public void pickUp(String item, Player p) {};
	public void use(String item, Player p ) {};
	public void talk(String person, Player p){};
	public void Wait(Player p) {};
	public void lookAt(Player p, String target) {};
	public void help() {};
	public boolean isGameOver() {return false;}
}
