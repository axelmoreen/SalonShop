package finalproj.ui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

import finalproj.Game;
import finalproj.states.SmallBusinessState;

public class SimpleGameStatusFactory {
	private static final DateTimeFormatter timeForm; 
	
	public static String getGameStatus(Game game) {
		String out = "";
		String name;
		if (game.getCurrentState() instanceof SmallBusinessState) {
			name = game.getShops().get(0).getName();
		}
		else {
			name = "Salon Shops";
		}
		String date = game.getDate().toString();
		String money = String.valueOf(game.getMoney());
		String worth = String.valueOf(game.getNetWorth());
		String employees = String.valueOf(game.getNumberOfEmployees());
		
		out = name+"\n"+
				date +" "+timeForm.format(game.getTimeOfDay())+"\n"+
				"Cash: "+money+"\n"+
				"Net worth: "+worth+"\n"+
				"Employees: "+employees;
		return out;
	}
	
	static {
		timeForm = DateTimeFormatter.ofPattern("hh:mm a");
	}
}
