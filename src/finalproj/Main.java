package finalproj;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import finalproj.ui.GameUI;
import finalproj.ui.SimpleGameUI;

public class Main {

	public static void main(String[] args) {
		System.out.println("Starting Salon Shop...");
		String startName = (String)JOptionPane.showInputDialog(
                "Hooray! You just bought your dream salon. Pick a name:", Game.getInstance().getRandomName());
		if (startName != null) {
			Game.getInstance().setStartingName(startName);
		}
		GameUI ui = new SimpleGameUI();
		
		Game.getInstance().setUI(ui);
		Game.getInstance().startTimer();		
		ui.createAndShow();
		
		//ui.getMessageProxy().handleMessage("Test Message", 0);
		//ui.getMessageProxy().tick();
		
	}
}
