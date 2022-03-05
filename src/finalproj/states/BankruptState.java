package finalproj.states;

import finalproj.Game;

public class BankruptState extends GameState{
	public void tick() {
		reply("You went out of business! :(");
		Game.getInstance().stopTimer();
		
	}
}
