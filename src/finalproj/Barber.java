package finalproj;

public class Barber {

	private String name;
	private BarberStatus status;
	private static final int tickLength = 2;
	private int tickStart;
	
	public Barber(String name) {
		this.name = name;
		this.status = BarberStatus.AVAILABLE;
		tickStart = 0;
	}
	
	public void checkAvailability() {
		if (status == BarberStatus.BUSY) {
			if (Game.getInstance().ticksElapsed() - tickStart > tickLength) {
				status = BarberStatus.AVAILABLE;
			}
		}
	}
	
	public void setBusy() {
		this.status = BarberStatus.BUSY;
	}
	
	public boolean isBusy() {
		return status == BarberStatus.BUSY || status == BarberStatus.ON_VACATION;
	}
	
	public enum BarberStatus{
		AVAILABLE,
		BUSY,
		ON_VACATION
	}
}
