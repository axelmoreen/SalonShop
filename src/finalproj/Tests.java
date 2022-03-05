package finalproj;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

import finalproj.appointments.Appointment;
import finalproj.appointments.AppointmentIterator;
import finalproj.ui.GameUI;
import finalproj.ui.SimpleGameUI;
import finalproj.appointments.AppointmentHandler;
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
		Assert.assertEquals(iter.hasNext(), false);
	}
	
	/*@Test
	public void testAppointmentSingleton() {
		AppointmentHandler.getInstance().addAppointment(LocalDate.now(), 
				Appointment.createAppointment(LocalDate.now(), "test_name", "test_notes"));
		AppointmentIterator apps = AppointmentHandler.getInstance().getAppointmentsOnDate(LocalDate.now());
		Assert.assertEquals(apps.hasNext(), true);
		Appointment appointment = apps.next();
		Assert.assertEquals(appointment.getCustomerName(), "test_name");
		Assert.assertEquals(appointment.getNotes(), "test_notes");
		Assert.assertEquals(apps.hasNext(), false);
	}*/
	
	@Test
	public void testSimpleUI() {
		GameUI ui = new SimpleGameUI();
		ui.createAndShow();
		ui.getMessageProxy().handleMessage("Test Message", 0);
	}
	
	@Test
	public void testChance() {
		Assert.assertEquals(Game.getInstance().getCustomerChance() > 0, true);
	}
}
