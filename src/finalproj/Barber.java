package finalproj;

public class Barber {

	private String name;
	private BarberStatus status;
	
	public Barber(String name) {
		this.name = name;
		this.status = BarberStatus.AVAILABLE;
	}
	
	
	
	public enum BarberStatus{
		AVAILABLE,
		BUSY,
		ON_VACATION
	}
}
