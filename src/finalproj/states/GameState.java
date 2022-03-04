package finalproj.states;

import java.util.Arrays;

import finalproj.Barber;
import finalproj.EmployeeType;
import finalproj.SalonShop;
import finalproj.Game;
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
				for (Barber barber : shop.getBarbers()) {
					if (!barber.isBusy()) {
						barber.setBusy();
					}
				}
			}
		}
		
		
		if (Game.getInstance().isNewDay()) {
			showCommands();
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
		Game.getInstance().queueUserMessage("Enter a command: ("+String.join("/", commands)+")");
	}
	
	protected void reply(String message) {
		Game.getInstance().queueUserMessage(message);
	}
	
	
}
