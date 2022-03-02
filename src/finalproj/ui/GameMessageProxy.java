package finalproj.ui;

public interface GameMessageProxy {
	public void doTextUpdate();
	public void handleMessage(String message, int tickAt);
}
