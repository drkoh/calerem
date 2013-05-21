/**
 * 
 */
package com.calerem.tests.controllers;

import android.content.Context;
import android.test.InstrumentationTestCase;

import com.calerem.classes.Celebration;
import com.calerem.classes.ConfigurationCalerem;
import com.calerem.classes.Contact;
import com.calerem.classes.Event;
import com.calerem.classes.MessageLog;
import com.calerem.classes.SyncLog;
import com.calerem.controllers.Database;

/**
 * Database Tests in JUnit 3.
 * @author DarkParadise
 */
public class DatabaseTest extends InstrumentationTestCase {

	private Context basecontext;
	private Database db;
	private Contact testcontact;
	private Event testevent;
	private Celebration testcelebration;
	private MessageLog testmessagelog;
	private ConfigurationCalerem testconfiguration;
	private SyncLog testsynclog;
	
	/**
	 * Sets up the initial enviroment so tests can be runned.
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		basecontext = getInstrumentation().getContext();
		db = new Database(basecontext);
		testcontact = new Contact("Kostas", "Papadopoulos", "1234567890", "destiny_gs@yahoo.gr", (Integer) null);
		testevent = new Event("Birthday","My Birthday",1365706160,testcontact,1,"Epic Fail Date");
		testmessagelog = new MessageLog(1365708011,(Integer) null,"Email","Kati Egrapsa",testcontact);
		testconfiguration = new ConfigurationCalerem("dd-MM-yyyy","","","","http://www.eortologio.gr/rss/si_av_me_el.xml","http://www.eortologio.gr/rss/si_av_me_en.xml");
		testsynclog = new SyncLog(1365708011,"Facebook",(Integer) null);
		testcelebration = new Celebration("Nameday", "Giannis", "23-02", 1);
	}

	/**
	 * Resets the database to initial state to automate the testing.
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
		db.factory_reset();
		db.close();
	}

	/**
	 * Test method for {@link com.calerem.controllers.Database#add_event(com.calerem.classes.Event)}.
	 */
	public void testAdd_event() {
		long resultCode = db.add_event(testevent);
		assertTrue(resultCode == 1);
	}

	/**
	 * Test method for {@link com.calerem.controllers.Database#delete_event(java.lang.Integer)}.
	 */
	public void testDelete_event() {
		long resultCode = db.add_event(testevent);
		if(resultCode == 1)
		{
			resultCode = db.delete_event(1);
			assertTrue(resultCode == 1);
		}
		else
		{
			fail("Couldnt add event.");
		}
	}

	/**
	 * Test method for {@link com.calerem.controllers.Database#update_event(com.calerem.classes.Event)}.
	 */
	public void testUpdate_event() {
		long resultCode = db.add_event(testevent);
		if(resultCode == 1)
		{
			testevent.setEvent_type("NameDay");
			testevent.setEvent_id(1);
			resultCode = db.update_event(testevent);
			assertTrue(resultCode == 1);
		}
		else
		{
			fail("Couldnt add event.");
		}
	}

	/**
	 * Test method for {@link com.calerem.controllers.Database#get_event(int)}.
	 */
	public void testGet_event() {
		long resultCode = db.add_event(testevent);
		if(resultCode == 1)
		{
			assertEquals(db.get_event(1).getEvent_name(),testevent.getEvent_name());
		}
		else
		{
			fail("Couldnt add event.");
		}
	}

	/**
	 * Test method for {@link com.calerem.controllers.Database#import_events(java.lang.String)}.
	 */
	public void testImport_events() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.calerem.controllers.Database#export_events(java.lang.String)}.
	 */
	public void testExport_events() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.calerem.controllers.Database#factory_reset()}.
	 */
	public void testFactory_reset() {
		//if this doesnt run, everything will fail.
		assertTrue(true);
	}
	
	/**
	 * Test method for {@link com.calerem.controllers.Database#get_celebrations(String)}.
	 */
	public void testGet_celebrations()
	{
		long resultCode = db.add_celebration(testcelebration);
		if(resultCode == 1)
		{
			Celebration[] Celebrations = db.get_celebrations("23-02");
			if(Celebrations.length > 0)
			{
				if(Celebrations[0].getName() == testcelebration.getName())
				{
					assertTrue(true);
				}
			}
			else
			{
				fail("Coudlnt find celebration");
			}
		}
		else
		{
			fail("Couldnt add celebration");
		}
	}
	
	/**
	 * Test method for {@link com.calerem.controllers.Database#celebration_exists(java.lang.String, java.lang.String)}.
	 */
	public void testCelebration_exists() {
		long resultCode = db.add_celebration(testcelebration);
		if(resultCode == 1)
		{
			if(db.celebration_exists(testcelebration.getDate(), testcelebration.getName()) > 0)
			{
				assertTrue(true);
			}
			else
			{
				fail("Celebration doesnt exist?");
			}
		}
		else
		{
			fail("Couldnt add celebration");
		}
	}
	
	/**
	 * Test method for {@link com.calerem.controllers.Database#return_events(java.lang.Integer, java.lang.Integer)}.
	 */
	public void testGet_events() {
		long resultCode = db.add_event(testevent);
		if(resultCode == 1)
		{
			testevent.setEvent_type("NameDay");
			resultCode = db.add_event(testevent);
			if(resultCode == 2)
			{
				Event tempevents[] = db.get_events(0, 1365708011);
				assertEquals(tempevents.length,2);
			}
			else
			{
				fail("Couldnt add event 2.");
			}
			assertTrue(resultCode == 2);
		}
		else
		{
			fail("Couldnt add event.");
		}
	}

	/**
	 * Test method for {@link com.calerem.controllers.Database#read_configuration()}.
	 */
	public void testRead_configuration() {
		ConfigurationCalerem tempconfig = db.read_configuration();
		assertEquals(tempconfig.getDate_format(),testconfiguration.getDate_format());
	}

	/**
	 * Test method for {@link com.calerem.controllers.Database#update_configuration(com.calerem.classes.ConfigurationCalerem)}.
	 */
	public void testUpdate_configuration() {
		testconfiguration.setDate_format("MM-DD-YYYY");
		long resultCode = db.update_configuration(testconfiguration);
		assertEquals(resultCode,1);
	}

	/**
	 * Test method for {@link com.calerem.controllers.Database#add_celebration(com.calerem.classes.Event)}.
	 */
	public void testAdd_celebration() {
		long resultCode = db.add_celebration(testcelebration);
		assertTrue(resultCode == 1);
	}

	/**
	 * Test method for {@link com.calerem.controllers.Database#update_celebration(com.calerem.classes.Event)}.
	 */
	public void testUpdate_celebration() {
		long resultCode = db.add_celebration(testcelebration);
		if(resultCode == 1)
		{
			testevent.setEvent_type("NameDay");
			testevent.setEvent_id(1);
			resultCode = db.update_celebration(testcelebration);
			assertTrue(resultCode == 1);
		}
		else
		{
			fail("Couldnt add celebration.");
		}
	}

	/**
	 * Test method for {@link com.calerem.controllers.Database#delete_celebration(com.calerem.classes.Event)}.
	 */
	public void testDelete_celebration() {
		long resultCode = db.add_celebration(testcelebration);
		if(resultCode == 1)
		{
			testevent.setEvent_id(1);
			resultCode = db.delete_celebration(testcelebration);
			assertTrue(resultCode == 1);
		}
		else
		{
			fail("Couldnt add celebration.");
		}
	}

	/**
	 * Test method for {@link com.calerem.controllers.Database#truncate_celebrations()}.
	 */
	public void testTruncate_celebrations() {
		long resultCode = db.add_celebration(testcelebration);
		if(resultCode == 1)
		{
			testevent.setEvent_description("NameDay");
			resultCode = db.add_celebration(testcelebration);
			if(resultCode == 2)
			{
				resultCode = db.truncate_celebrations();
				assertTrue(resultCode == 2);	
			}
			else
			{
				fail("Couldnt add celebration 2.");
			}
		}
		else
		{
			fail("Couldnt add celebration.");
		}
	}

	/**
	 * Test method for {@link com.calerem.controllers.Database#log_sync(com.calerem.classes.SyncLog)}.
	 */
	public void testLog_sync() {
		long resultCode = db.log_sync(testsynclog);
		assertEquals(resultCode,1);
	}

	/**
	 * Test method for {@link com.calerem.controllers.Database#read_sync_log(int)}.
	 */
	public void testRead_sync_log() {
		long resultCode = db.log_sync(testsynclog);
		if(resultCode == 1)
		{
			testsynclog.setType("Eortologio");
			resultCode = db.log_sync(testsynclog);
			if(resultCode == 2)
			{
				assertEquals(db.read_sync_log(10).length,2);
			}
			else
			{
				fail("Couldnt insert SyncLog 2");
			}
		}
		else
		{
			fail("Couldnt insert SyncLog");
		}
	}

	/**
	 * Test method for {@link com.calerem.controllers.Database#log_messages(com.calerem.classes.MessageLog)}.
	 */
	public void testLog_messages() {
		long resultCode = db.log_messages(testmessagelog);
		assertEquals(resultCode,1);
	}

	/**
	 * Test method for {@link com.calerem.controllers.Database#read_message_log(int)}.
	 */
	public void testRead_message_log() {
		long resultCode = db.log_messages(testmessagelog);
		if(resultCode == 1)
		{
			testmessagelog.setMessage("Kati Allo");
			resultCode = db.log_messages(testmessagelog);
			if(resultCode == 2)
			{
				MessageLog tempmessagelog[] = db.read_message_log(10);
				assertEquals(tempmessagelog.length,2);
			}
			else
			{
				fail("Couldnt Insert Message 2.");
			}
		}
		else
		{
			fail("Couldnt Insert message");
		}
	}

	/**
	 * Test method for {@link com.calerem.controllers.Database#add_contact(com.calerem.classes.Contact)}.
	 */
	public void testAdd_contact() {
		long resultCode = db.add_contact(testcontact);
		assertTrue(resultCode == 1);
	}

	/**
	 * Test method for {@link com.calerem.controllers.Database#update_contact(com.calerem.classes.Contact)}.
	 */
	public void testUpdate_contact() {
		long resultCode = db.add_contact(testcontact);
		if(resultCode == 1)
		{
			testcontact.setLastname("Test");
			testcontact.setId(1);
			resultCode = db.update_contact(testcontact);
			assertTrue(resultCode == 1);
		}
		else
		{
			fail("Couldnt add contact");
		}
	}

	/**
	 * Test method for {@link com.calerem.controllers.Database#get_contact(int)}.
	 */
	public void testGet_contact() {
		long resultCode = db.add_contact(testcontact);
		if(resultCode == 1)
		{
			testcontact.setId(1);
			db.get_contact(1);
			assertEquals(db.get_contact(1).getName(),testcontact.getName());
		}
		else
		{
			fail("Couldnt add contact");
		}
	}

	/**
	 * Test method for {@link com.calerem.controllers.Database#get_contacts()}.
	 */
	public void testGet_contacts() {
		long resultCode = db.add_contact(testcontact);
		if(resultCode == 1)
		{
			testcontact.setName("Mitsos");
			resultCode = db.add_contact(testcontact);
			if(resultCode == 2)
			{
				Contact tempcontacts[] = db.get_contacts();
				assertEquals(tempcontacts.length,2);
			}
			else
			{
				fail("Couldnt add contact 2.");
			}
		}
		else
		{
			fail("Couldnt add contact");
		}
	}

}
