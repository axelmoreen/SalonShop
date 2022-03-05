package finalproj.states;

import finalproj.EmployeeType;
import finalproj.Game;

public class SmallBusinessState extends GameState{
	

	
	public void tick() {
		super.tick();
	}

	
	public void input(String input) {
		if (input.toLowerCase().startsWith("advertise")) {
			String[] args = input.split(" ");
			if (args.length < 2) {
				reply("Popularity level: "+String.valueOf(Game.getInstance().getPopularity()));
				reply("Advertising cost: "+String.valueOf(Game.getInstance().getAdvertisingCost()+". Type 'advertise buy' to buy."));
			}
			else {
				if (args[1].equalsIgnoreCase("buy")) {
					if (Game.getInstance().buyPopularity()) {
						reply("Congrats! Your popularity level is now: "+String.valueOf(Game.getInstance().getPopularity()));
					}else {
						reply("Not enough money to buy advertising!");
					}
				}
			}
		}
		else if (input.toLowerCase().startsWith("hire")) {
			String[] args = input.split(" ");
			if (args.length < 2) {
				reply("To hire a barber, type 'hire barber'.");
				//reply("To hire a manager, type 'hire manager'.");
			}
			else {
				if (args[1].equalsIgnoreCase("barber")) {
					String b = Game.getInstance().hireBarber(Game.getInstance().getShops().get(0));
					if (b == null) {
						reply("Not enough money!");
					}
					else {
						reply("Welcome to the team, "+b+"!");
					}
				}else if (args[1].equalsIgnoreCase("manager")) {
					//if (Game.getInstance().hireManager(Game.getInstance().getShops().get(0))) {
					//	reply("You hired a manager!");
					//}
					//else {
					//	reply("Not enough money.");
					//}
				}
			}
		}
		else if (input.toLowerCase().startsWith("train")) {
			String[] args = input.split(" ");
			if (args.length < 2) {
				int cost = Game.getInstance().getTrainingCost();
				reply("Are you sure you want to send your barbers to a class?");
				reply(String.format("Type 'train now' to confirm. Cost: $%d",cost));
			}
			else {
				if (args[1].equalsIgnoreCase("now")) {
					if (Game.getInstance().runTraining(Game.getInstance().getShops().get(0))) {
						reply("Training is complete!");
					}else {
						reply("Not enough money for that!");
					}
				}
			}
		}
		
		else {
			super.input(input);
		}
	}
	
	@Override
	public String[] commands() {
		// TODO Auto-generated method stub
		String[] newCommands = new String[] {"advertise", "hire", "train"};
		return concat(newCommands, super.commands());
	}

}
