package finalproj.appointments;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AppointmentSingleton {
	private static AppointmentSingleton instance;
	
	private HashMap<LocalDate, List<Appointment>> appointments;
	
	public AppointmentSingleton() {
		appointments = new HashMap<LocalDate, List<Appointment>>();
		
	}
	
	public List<Appointment> getAppointmentsOnDate(LocalDate date){
		
		if (appointments.containsKey(date)) return appointments.get(date);
		return new ArrayList<Appointment>();
	}
	
	public void addAppointment(LocalDate date, Appointment appointment) {
		List<Appointment> list;
		if (appointments.containsKey(date)) {
			list = appointments.get(date);
		}
		else {
			list = new ArrayList<Appointment>();
		}
		list.add(appointment);
		appointments.put(date,  list);
	}
	
	public static AppointmentSingleton getInstance() {
		if (instance == null) {
			instance = new AppointmentSingleton();
		}
		
		return instance;
	}
}
