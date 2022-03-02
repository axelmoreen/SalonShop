package finalproj;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import finalproj.states.ChainStoreState;
import finalproj.states.GameState;
import finalproj.states.GrandOpeningState;
import finalproj.states.MonopolyState;
import finalproj.states.SmallBusinessState;
import finalproj.ui.GameUI;

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
	private final int defaultCutCost = 50;
	private final int dayTicks = 10; // ticks per day
	private final float baseCustomerChance = 0.01f / dayTicks;
	private final int minCompetingChain = 1000000;
	private final int maxCompetingChain = 10000000;
	private final int monopolyAmount = 5000000;
	private final LocalDate startingDay = LocalDate.of(2010, Month.JANUARY, 1);
	private final LocalTime timeOpen = LocalTime.of(8,0,0);
	private final LocalTime timeClosed = LocalTime.of(20,0,0);
	
	private String startingName;

	private GameState gameState;
	private int ticksElapsed;
	
	private int netWorth;
	private List<Integer> reviews;
	private int popularity;
	private int cutCost;
	private GameUI ui;
	
	private LocalDate today;

	private List<SalonShop> shops;
	
	private Game() {
		random = new Random();
		netWorth = startingMoney;
		reviews  = new ArrayList<Integer>();
		shops = new ArrayList<SalonShop>();
		startingName = shopNames[random.nextInt(shopNames.length)];
		gameState = new GrandOpeningState();
		today = startingDay;
		ticksElapsed = 0;
		popularity = 1;
		cutCost = defaultCutCost;
		
		shops.add(new SalonShop(startingName, SalonShop.Location.randomLocation(random)));
		shops.get(0).addBarber(getNewBarber());
	}

	
	public void tick() {
		//TODO update date
		
		evaluateGameState();
		this.gameState.tick();
		
		//update UI
		this.ui.updateState();
		this.ticksElapsed++;
	}
	public LocalDate getDate() {
		return today;
	}
	
	private Barber getNewBarber() {
		return new Barber(barberNames[random.nextInt(barberNames.length)], random.nextInt(5)+1);
	}
	
	
	public List<SalonShop> getShops(){
		return shops;
	}
	
	public Random getRandom() {
		return random;
	}
	
	public void handleCommand(String command) {
		this.gameState.input(command);
	}
	
	private void evaluateGameState() {
		if (ticksElapsed > 10*dayTicks) {
			if (gameState instanceof GrandOpeningState) {
				gameState = new SmallBusinessState();
			}
			
			if (shops.size() > 1 && gameState instanceof SmallBusinessState) {
				gameState = new ChainStoreState();
			}
			
			if (netWorth > monopolyAmount) {
				gameState = new MonopolyState();
			}
		}
	}
	
	public float getCustomerChance() {
		int rev = 0;
		for (int review : reviews) {
			rev += review;
		}
		return Math.min(1, 0.2f * baseCustomerChance * popularity * rev / reviews.size());
	}
	
	public void queueMessage(String message) {
		ui.getMessageProxy().handleMessage(message, ticksElapsed);
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
	
	public void setUI(GameUI ui) {
		this.ui = ui;
	}
	
	public String getYelpScore() {
		int avg = 0;
		for (Integer score : reviews) {
			avg += score;
		}
		return String.format("%.2f / 5", (float)avg / reviews.size());
	}
	
	public int ticksElapsed() {
		return ticksElapsed;
	}
	
	public static Game getInstance() {
		if (singleton == null) {
			singleton = new Game();
		}
		
		return singleton;
	}
}
