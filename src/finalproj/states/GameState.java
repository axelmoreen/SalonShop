package finalproj.states;

import finalproj.Game;

public abstract class GameState {
	
	public void tick() {
		String[] commands = Game.getInstance().getCurrentState().commands();
		Game.getInstance().queueMessage("Enter a command: ("+String.join("/", commands)+")");
	}
	
	public void input(String command) {
		Game.getInstance().queueMessage("Unknown command: "+command.toLowerCase());
	}
	
	public abstract String[] commands();
}
