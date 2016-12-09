import java.util.Random;

public class Shida {
	public int blood;
	public int gameOver;

	public Shida() {
		blood = 100;
		gameOver = 0;
	}

	public void attack(Player p) {
		Random rand = new Random();
		int x = rand.nextInt(5);
		if (p.blood > 0) {
			switch (x) {
			case 0: System.out.println("Shida gets off the ground and attacks you!");
			System.out.println("He punches you hard in the face!");
			p.blood -= 20;
			if (p.blood <= 0) {
				gameOver = 1;
				System.out.println("You died!!!");
				System.out.println("GAME OVER!!!");
			}
			break;
			case 1: System.out.println("Shida jumps and attacks you!");
			System.out.println("He kicks you in the stomach!");
			p.blood -= 30;
			if (p.blood <= 0) {
				gameOver = 1;
				System.out.println("You died!!!");
				System.out.println("GAME OVER!!!");
			}
			break;
			case 2: System.out.println("Shida attacks you!");
			System.out.println("He shoots you in the face!");
			p.blood -= 70;
			if (p.blood <= 0) {
				gameOver = 1;
				System.out.println("You died!!!");
				System.out.println("GAME OVER!!!");
			}
			break;
			case 3: System.out.println("Shida tries to attack you but misses!");
			System.out.println("It seems like he hurt himself!");
			blood -= 10;
			if (p.blood <= 0) {
				gameOver = 1;
				System.out.println("You died!!!");
				System.out.println("GAME OVER!!!");
			}
			break;
			case 4: System.out.println("Shida attacks you!");
			System.out.println("He bites you!");
			p.blood -= 10;
			if (p.blood <= 0) {
				gameOver = 1;
				System.out.println("You died!!!");
				System.out.println("GAME OVER!!!");
			}
			break;
			default: System.out.println("Shida attacks you!");
			System.out.println("He uses jiu-jitsu on you!");
			p.blood -= 15;
			if (p.blood <= 0) {
				gameOver = 1;
				System.out.println("You died!!!");
				System.out.println("GAME OVER!!!");
			}
			break;
			}
		}
	}
	
	
	
	public void printStats() {
		System.out.println("Shida");
		System.out.println("Life: " + this.blood);
	}
}