package finalproj;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.Timer;

import finalproj.ui.GameUI;
import finalproj.ui.SimpleGameUI;

public class Main {

	public static void main(String[] args) {
		GameUI ui = new SimpleGameUI();
		Game.getInstance().setUI(ui);
		Game.getInstance().startTimer();		
		ui.createAndShow();
		//ui.getMessageProxy().handleMessage("Test Message", 0);
		//ui.getMessageProxy().tick();
		
	}
}
