package finalproj.appointments;

public class AppointmentIterator {
	private Appointment[] appointments;
	private int position;
	
	public AppointmentIterator(Appointment[] appointments) {
		this.appointments = appointments;
		this.position = 0;
	}
	
	public Appointment next() {
		return appointments[position++];
	}
	
	public boolean hasNext() {
		return !(position >= appointments.length || appointments[position] == null);
	}
	
}
