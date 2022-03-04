package finalproj.states;

import java.util.Arrays;

import finalproj.Barber;
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
		
		String[] commands = Game.getInstance().getCurrentState().commands();
		if (Game.getInstance().isNewDay()) {
			Game.getInstance().queueUserMessage("Enter a command: ("+String.join("/", commands)+")");
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
		else {
			Game.getInstance().queueUserMessage("Unknown command: "+command.toLowerCase());
		}
	}
	
	public String[] commands() {
		return new String[] {(Game.getInstance().isFast() ? "slowdown" : "fastforward")};
	}
	
	protected String[] concat(String[] arr1, String[] arr2) {
		String[] both = Arrays.copyOf(arr1, arr1.length + arr2.length);
		System.arraycopy(arr2,  0, both, arr1.length, arr2.length);
		return both;
	}
}
