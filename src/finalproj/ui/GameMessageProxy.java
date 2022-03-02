package finalproj.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.JTextArea;

import finalproj.Game;

public class GameMessageProxy extends JTextArea{
	private final int rows = 17;
	private final int columns= 100;
	private final int ticksOn = 30;
	
	private LinkedList<Map.Entry<String, Integer>> messageBuffer = new LinkedList<Map.Entry<String, Integer>>();
	
	public GameMessageProxy() {
		this.setRows(rows);
		this.setColumns(columns);
		this.setEditable(false);
	}
	
	public void tick() {
		flushBefore(Game.getInstance().ticksElapsed() - ticksOn);
		doTextUpdate();
	}
	
	private void flushBefore(int tickBefore) {
		while (messageBuffer.size() > rows) {
			messageBuffer.removeFirst();
		}
		//LinkedList<String> sortedKeys = new LinkedList<String>();
		//List<Map.Entry<String, Integer>> toRemove = new ArrayList<Map.Entry<String, Integer>>();
		for (Map.Entry<String,Integer> entry : messageBuffer) {
			if (entry.getValue() < tickBefore) {
				//toRemove.add(entry);
				messageBuffer.removeFirst();
			}
		}
		//for (Map.Entry<String, Integer> entry : toRemove) {
		//	messageBuffer.remove(entry);
		//}
		
	}
	
	public void handleMessage(String message, int tickAt) {
		messageBuffer.add(Map.entry(message, tickAt));
	}
	
	public void doTextUpdate() {
		List<String> lines = new ArrayList<String>();
		for (Map.Entry<String, Integer> entry : messageBuffer) {
			lines.add(entry.getKey());
		}
		System.out.println(lines.size());
		this.setText(String.join("\n", lines));
	}
	
}
