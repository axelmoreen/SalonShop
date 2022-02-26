package finalproj.states;

import finalproj.Barber;
import finalproj.BarberShop;
import finalproj.Game;

public abstract class GameState {
	
	protected float customerChance() {
		return Game.getInstance().getCustomerChance();
	}
	
	public void tick() {
		for (BarberShop shop : Game.getInstance().getShops()) {
			/// shop tick
			shop.checkAvailabilities();
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
