package finalproj.ui;

public interface GameUI {
	public void createAndShow();
	
	public SwingGameMessageProxy getMessageProxy();
	
	public void updateState();
}
