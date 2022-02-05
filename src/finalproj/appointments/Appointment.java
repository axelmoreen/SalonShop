package finalproj.appointments;

import java.awt.Image;
import java.time.LocalDate;
import java.util.List;

public class Appointment {
	private LocalDate date;
	private String fullName;
	private String notes;
	private List<Image> references;
	private String barberName;
	private boolean isAssignedBarber;
	private float quote; // how much appointment will cost
	private boolean isAssignedQuote;
	
	private Appointment(LocalDate date, String fullName, String notes, List<Image> references, String barberName) {
		this.date = date;
		this.fullName = fullName;
		this.notes = notes;
		this.references = references;
		this.barberName = barberName;
		if (barberName == null) {
			isAssignedBarber = false;
		}
		else {
			isAssignedBarber = true;
		}
		this.quote = -1;
		this.isAssignedQuote = false;
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public String getCustomerName() {
		return fullName;
	}
	
	public String getNotes() {
		return notes;
	}
	
	public List<Image> getReferences(){
		return references;
	}
	
	public String getBarberName() {
		return barberName;
	}
	
	public boolean getIsAssignedBarber() {
		return isAssignedBarber;
	}

	public boolean getIsAssignedQuote() {
		return isAssignedQuote;
	}
	
	public float getQuote() {
		return quote;
	}
	
	public static Appointment createAppointment(LocalDate date, String fullName, String notes, List<Image> references) {
		return new Appointment(date, fullName, notes, references, null);
	}
	
	public static Appointment createAppointmentWithBarber(LocalDate date, String fullName, String barberName, String notes, List<Image> references) {
		return new Appointment(date, fullName, notes, references, barberName);
	}
}
