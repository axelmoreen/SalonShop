package finalproj.states;

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
		Game.getInstance().queueMessage("Enter a command: ("+String.join("/", commands)+")");
	}
	
	public void input(String command) {
		Game.getInstance().queueMessage("Unknown command: "+command.toLowerCase());
	}
	
	public abstract String[] commands();
}
