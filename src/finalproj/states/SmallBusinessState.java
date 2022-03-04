package finalproj.states;

import finalproj.Game;

public class SmallBusinessState extends GameState{
	

	
	public void tick() {
		super.tick();
	}

	@Override
	public String[] commands() {
		// TODO Auto-generated method stub
		String[] newCommands = new String[] {};
		return concat(newCommands, super.commands());
	}

}
