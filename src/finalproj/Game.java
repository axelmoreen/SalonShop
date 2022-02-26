package finalproj;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import finalproj.states.GameState;
import finalproj.states.GrandOpeningState;

public class Game {
	
	private static Game singleton;
	
	private static final String[] shopNames = new String[] {
		"Coolio Cuts",
		"Sam's Snips",
		"Sandy's Shaves",
		"Bob's Barbery",
		"Hank's Hair Salon",
		"Ian's Impossible Cuts",
		"Mr. Blue's Barber Shop",
		"Sebastian's Salon",
		"Max's Manes",
		"The Loft",
		"Awesome Haircuts Here",
		"Illegal Shaves",
		"Straight No Razor",
		"Gentlemen Salon",
		"Gavin's Grooms"
	};
	
	private static final String[] barberNames = new String[] {
			"Liam", "Noah", "Oliver", "Elijah", "William", "James", "Benjamin", "Lucas", "Henry", "Alexander",
			"Olivia", "Emma", "Ava", "Charlotte", "Sophia", "Amelia", "Isabella", "Mia", "Evelyn", "Harper"
	};
	
	private final Random random;
	
	private final int barberSalary = 5000;
	private final int leaseMonth = 4000;
	private final int startingMoney = 20000;
	
	private String startingName;

	private GameState gameState;
	
	private int netWorth;
	private List<Integer> reviews;
	
	private List<Barber> barbers;
	
	private Game() {
		random = new Random();
		netWorth = startingMoney;
		reviews  = new ArrayList<Integer>();
		barbers = new ArrayList<Barber>();
		startingName = shopNames[random.nextInt(shopNames.length)];
		gameState = new GrandOpeningState();
	}

	private Barber getNewBarber() {
		return new Barber(barberNames[random.nextInt(barberNames.length)]);
	}
	
	
	public void queueMessage(String message) {
		
	}
	
	public void addReview(int review) {
		reviews.add(review);
	}
	
	public int getNumberReviews() {
		return reviews.size();
	}
	
	public GameState getCurrentState() {
		return gameState;
	}
	
	public String getYelpScore() {
		int avg = 0;
		for (Integer score : reviews) {
			avg += score;
		}
		return String.format("%.2f / 5", (float)avg / reviews.size());
	}
	
	public static Game getInstance() {
		if (singleton == null) {
			singleton = new Game();
		}
		
		return singleton;
	}
}
