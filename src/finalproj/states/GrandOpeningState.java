package finalproj.states;

import finalproj.Game;

public class GrandOpeningState extends SmallBusinessState{

	protected float customerChance() {
		return 10 * Game.getInstance().getCustomerChance();
	}
	
	public void tick() {
		if (Game.getInstance().ticksElapsed() == 0) {
			Game.getInstance().queueMessage("It's your opening day! Let's make a good impression.");
		}
		else if (Game.getInstance().ticksElapsed() % 4 == 0) {
			Game.getInstance().queueMessage("We're a brand new salon! Let's make a good impression.");
		}
		super.tick();
	}
	
	
}
