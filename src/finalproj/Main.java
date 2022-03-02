package finalproj;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;

import javax.swing.Timer;

import finalproj.ui.GameUI;
import finalproj.ui.SimpleGameUI;

public class Main {
	public static void main(String[] args) {
		GameUI ui = new SimpleGameUI();
		Game.getInstance().setUI(ui);
		
		//run game loop
		final int tickMs = 50;
		Timer timer = new Timer(tickMs, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Game.getInstance().tick();
			}
			
		});
		timer.start();
		ui.createAndShow();
		//ui.getMessageProxy().handleMessage("Test Message", 0);
		//ui.getMessageProxy().tick();
		
	}
}
