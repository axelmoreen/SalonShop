package finalproj.states;

import java.util.Arrays;

import finalproj.Barber;
import finalproj.EmployeeType;
import finalproj.SalonShop;
import finalproj.Game;
import finalproj.HairStyle;
import finalproj.appointments.AppointmentIterator;

public abstract class GameState {
	
	protected float customerChance() {
		return Game.getInstance().getCustomerChance();
	}
	
	public void tick() {
		for (SalonShop shop : Game.getInstance().getShops()) {
			/// shop tick
			shop.checkAvailabilities();
			AppointmentIterator appoint = shop.getAppointmentHandler().getAppointmentsOnDate(Game.getInstance().getDate());
			while (Game.getInstance().getRandom().nextFloat() < customerChance()) {
				// walk in customer
				boolean reqFilled = false;
				boolean skillful = true;
				HairStyle request = HairStyle.randomValue(Game.getInstance().getRandom());
				
				for (Barber barber : shop.getBarbers()) {
					if (!barber.isBusy()) {
						if (barber.getSkillLevel() >= request.getDifficulty()) {
							barber.setBusy();
							reqFilled = true;
						}
						else {
							skillful = false;
						}
					}					
				}
				if (reqFilled) {
					shop.processCustomer();
					gameMessage("Cha-ching! Walk-in customer got "+request.getDisplayName());
				}else {
					if (!skillful) {
						gameMessage("Walk-in wanted "+request.getDisplayName() + " but we didn't know how!");
					}else {
						gameMessage("Too busy, so walk-in customer left!");
					}
				}
			}
		}
		
		
		if (Game.getInstance().ticksElapsed() == 0) {
			reply("Welcome to your new Salon Shop!");
			reply("For a list of commands, type 'help'");
			showCommands();
		}
		
		//check if its time to pay
		else if (Game.getInstance().getDate().getDayOfMonth() == 1 && Game.getInstance().isNewDay()) {
			// pay day
			int lease = Game.getInstance().getLeaseCost(true);
			int utilities = Game.getInstance().getUtilitiesCost(true);
			int barberNum = Game.getInstance().getNumberOfEmployees(EmployeeType.BARBER);
			int managerNum = Game.getInstance().getNumberOfEmployees(EmployeeType.MANAGER);
			int barberCost = barberNum * Game.getInstance().getBarberSalary();
			int managerCost = managerNum * Game.getInstance().getManagerSalary();
			gameMessage(String.format("You paid $%d for your lease", lease));
			gameMessage(String.format("You paid $%d for utilities", utilities));
			gameMessage(String.format("You paid $%d to your employees", barberCost + managerCost));
			
			Game.getInstance().takeMoney(lease + utilities + barberCost + managerCost);
		}
	}
	
	public void input(String command) {
		if (command.equalsIgnoreCase("slowdown") || command.equalsIgnoreCase("slow")) {
			if (Game.getInstance().isFast()) {
				Game.getInstance().toggleFast();
			}
		}
		else if (command.equalsIgnoreCase("fastforward") || command.equalsIgnoreCase("ff")) {
			if (!Game.getInstance().isFast()) {
				Game.getInstance().toggleFast();
			}
		}
		else if (command.toLowerCase().startsWith("costs")) {
			reply("====Your monthly costs breakdown====");
			int lease = Game.getInstance().getLeaseCost(true);
			int utilities = Game.getInstance().getUtilitiesCost(true);
			int barberNum = Game.getInstance().getNumberOfEmployees(EmployeeType.BARBER);
			int managerNum = Game.getInstance().getNumberOfEmployees(EmployeeType.MANAGER);
			int barberCost = barberNum * Game.getInstance().getBarberSalary();
			int managerCost = managerNum * Game.getInstance().getManagerSalary();
			reply(String.format("Lease: $%d", lease));
			reply(String.format("Utilities: $%d", utilities));
			reply(String.format("%d X Barbers: $%d", barberNum, barberCost));
			reply(String.format("%d X Managers: $%d", managerNum, managerCost ));
			reply(String.format("--- Total: $%d", lease + utilities + barberNum + managerNum + barberCost + managerCost));
		}
		else if (command.equalsIgnoreCase("help")) {
			showCommands();
		}
		else {
			Game.getInstance().queueUserMessage("Unknown command: "+command.toLowerCase());
		}
	}
	
	public String[] commands() {
		return new String[] {(Game.getInstance().isFast() ? "slowdown" : "fastforward"), "costs"};
	}
	
	protected String[] concat(String[] arr1, String[] arr2) {
		String[] both = Arrays.copyOf(arr1, arr1.length + arr2.length);
		System.arraycopy(arr2,  0, both, arr1.length, arr2.length);
		return both;
	}
	
	protected void showCommands() {
		String[] commands = Game.getInstance().getCurrentState().commands();
		Game.getInstance().queueUserMessage("List of commands: ("+String.join("/", commands)+")");
	}
	
	protected void reply(String message) {
		Game.getInstance().queueUserMessage(message);
	}
	
	protected void gameMessage(String message) {
		Game.getInstance().queueGameMessage(message);
	}
	
}
