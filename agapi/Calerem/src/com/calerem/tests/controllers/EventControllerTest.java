import org.junit.Test;

import android.content.Context;

import com.calerem.classes.MockDB;
import com.calerem.controllers.Database;
import com.calerem.controllers.EventController;

import junit.framework.TestCase;


public class EventControllerTest extends TestCase {

	private MockDB mDB;
	Database db;
	Context basecontext;
	EventController evMDb = new EventController(mDB);
	EventController evDb = new EventController(db);
	String expected = "";
	String generated = "";
	
	@Test
	public void TestUpcommingEvents_success() {
		expected =  "Upcomming Event: Maria's Birthday, Dimitris's Anniversary, Agapi's Graduation";
		generated = evMDb.upcommingEventsMDatabase();
		assertEquals(generated,expected);
	}
	
	@Test
	public void TestUpcommingEvents_emptyEvents() {
		expected = "Upcomming Event: ";
		generated = evDb.upcommingEventsDatabase();
		assertEquals(generated,expected);
	}
	
	@Test
	public void TestUpcommingEvents_NoName() {
		mDB.editSpecifiedEventName(0, "");
		EventController newMDb = new EventController(mDB);
		generated = newMDb.upcommingEventsMDatabase();
		expected = "Upcomming Event: 's Birthday, Dimitris's Anniversary, Agapi's Graduation";
		assertEquals(generated,expected);
	}
	
	@Test
	public void TestUpcommingEvents_NoType() {
		mDB.editSpecifiedEventName(0, "");
		EventController newMDb = new EventController(mDB);
		generated = newMDb.upcommingEventsMDatabase();
		expected = "Upcomming Event: Maria's , Dimitris's Anniversary, Agapi's Graduation";
		assertEquals(generated,expected);
	}
}
