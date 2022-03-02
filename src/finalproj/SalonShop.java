package finalproj;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import finalproj.appointments.AppointmentHandler;

public class SalonShop {
	
	private String name;
	private Location location;
	private List<Barber> barbers;
	private AppointmentHandler appointments;
	private boolean hasManager;
	
	
	public SalonShop(String name, Location location) {
		this.name = name;
		this.location = location;
		this.barbers = new ArrayList<Barber>();
		this.appointments = new AppointmentHandler();
		this.hasManager = false;
	}
	
	
	public boolean hasManager() {
		return hasManager;
	}
	
	public void addBarber(Barber barber) {
		this.barbers.add(barber);
	}
	
	public List<Barber> getBarbers(){
		return barbers;
	}
	
	public void checkAvailabilities() {
		for (Barber barber : barbers) {
			barber.checkAvailability();
		}
	}
	public boolean hasAvailableBarber() {
		for (Barber barber : barbers) {
			if (!barber.isBusy()) return true;
		}
		return false;
	}
	
	public void processCustomer() {
		//to do, process sale/ add to net worth etc
	}
	
	public AppointmentHandler getAppointmentHandler() {
		return appointments;
	}
	
	public String getName() {
		return name;
	}
	
	public Location getLocation() {
		return location;
	}

	
	
	public enum Location{
		NYC("New York City"),
		LA("Los Angeles"),
		CHICAGO("Chicago"),
		HOUSTON("Houston"),
		PHOENIX("Phoenix");
		
		private String name;
		
		private Location(String name) {
			this.name = name;
		}
		
		public String getName() {
			return name;
		}
		
		public static Location randomLocation(Random random) {
			return Location.values()[random.nextInt(Location.values().length)];
		}
	}
}
