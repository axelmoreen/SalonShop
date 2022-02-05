package finalproj;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import finalproj.appointments.Appointment;
import finalproj.appointments.AppointmentIterator;
import junit.framework.Assert;

public class Tests {

	@Test
	public void testAppointmentIterator() {
		Appointment app1 = Appointment.createAppointment(LocalDate.now(), "John Smith", "1 inch long");
		Appointment app2 = Appointment.createAppointment(LocalDate.now(), "Thomas Jefferson", "Curly Trim");
		Appointment[] apps = new Appointment[]{app1,app2};
		AppointmentIterator iter = new AppointmentIterator(apps);
		Appointment _app1 = iter.next();
		Assert.assertEquals(_app1.getCustomerName(), "John Smith");
		Appointment _app2 = iter.next();
		Assert.assertEquals(_app2.getCustomerName(), "Thomas Jefferson");
	}
}
