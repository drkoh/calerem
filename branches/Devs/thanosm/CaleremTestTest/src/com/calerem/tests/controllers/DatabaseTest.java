/**
 * 
 */
package com.calerem.tests.controllers;

import android.content.Context;
import android.test.InstrumentationTestCase;
import android.util.Log;

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
	
	
	
	/**
	 * Sets up the initial enviroment so tests can be runned.
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		basecontext = getInstrumentation().getContext();
		db = new Database(basecontext);
		db.factory_reset();
		
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
		long resultCode = db.add_event(this.DefaultEvent());
		assertTrue(resultCode == 1);
	}
	
	/**
	 * Enhanced by athaboil to test if add_event works with null name
	 */
	public void testAdd_event_Null_Name() {
		Event e1=this.DefaultEvent();
		e1.setEvent_name(null);
		long resultCode = db.add_event(e1);
		assertTrue(resultCode == 1);
	}

	/**
	 * Enhanced by athaboil to test if add_event works with empty name
	 */
	public void testAdd_event_Empty_Name() {
		Event e1=this.DefaultEvent();
		e1.setEvent_name("");
		long resultCode = db.add_event(e1);
		assertTrue(resultCode == 1);
	}
	/**
	 * Enhanced by athaboil to test if add_event works with empty type
	 */
	public void testAdd_event_Empty_Type() {
		Event e1=this.DefaultEvent();
		e1.setEvent_type("");
		long resultCode = db.add_event(e1);
		assertTrue(resultCode == 1);
	}
	/**
	 * Enhanced by athaboil to test if add_event works with null type
	 */
	public void testAdd_event_Null_Type() {
		Event e1=this.DefaultEvent();
		e1.setEvent_type(null);
		long resultCode = db.add_event(e1);
		assertTrue(resultCode == 1);
	}
	/**
	 * Enhanced by athaboil to test if add_event works with zero date
	 */
	public void testAdd_event_Zero_Date() {
		Event e1=this.DefaultEvent();
		e1.setEvent_date(0);
		long resultCode = db.add_event(e1);
		assertTrue(resultCode == 1);
	}
	/**
	 * Enhanced by athaboil to test if add_event works with null contact
	 */
	public void testAdd_event_Null_Contact() {
		Event e1=this.DefaultEvent();
		e1.setEvent_contact(null);
		long resultCode = db.add_event(e1);
		assertTrue(resultCode == 1);
	}
	/**
	 * Enhanced by athaboil to test if add_event works with prefix id
	 */
	public void testAdd_event_Prefix_id() {
		Event e1=this.DefaultEvent();
		e1.setEvent_id(3);
		long resultCode = db.add_event(e1);
		assertTrue(resultCode == 1);
	}
	/**
	 * Enhanced by athaboil to test if add_event works with negative id
	 */
	public void testAdd_event_Negative_id() {
		Event e1=this.DefaultEvent();
		e1.setEvent_id(-3);
		long resultCode = db.add_event(e1);
		assertTrue(resultCode == 1);
	}
	
	/**
	 * Enhanced by athaboil to test if add_event works with null Description
	 */
	public void testAdd_event_Null_Description() {
		Event e1=this.DefaultEvent();
		e1.setEvent_type(null);
		long resultCode = db.add_event(e1);
		assertTrue(resultCode == 1);
	}

	/**
	 * Enhanced by athaboil to test if add_event works with empty Description
	 */
	public void testAdd_event_Empty_Description() {
		Event e1=this.DefaultEvent();
		e1.setEvent_type("");
		long resultCode = db.add_event(e1);
		assertTrue(resultCode == 1);
	}
	
	
	/**
	 * Test method for {@link com.calerem.controllers.Database#delete_event(java.lang.Integer)}.
	 */
	public void testDelete_event() {
		long resultCode = db.add_event(this.DefaultEvent());
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
	 * Enhanced by athaboil to test if delete_event works with prefix ID
	 */
	public void testDelete_event_with_different_Id() {
		Event e1 = this.DefaultEvent();
		e1.setEvent_id(10);
		long resultCode = db.add_event(e1);
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
		long resultCode = db.add_event(this.DefaultEvent());
		if(resultCode == 1)
		{
			Event ev1 = this.DefaultEvent();
			ev1.setEvent_type("NameDay");
			ev1.setEvent_id(1);
			resultCode = db.update_event(ev1);
			assertTrue(resultCode == 1);
		}
		else
		{
			fail("Couldnt add event.");
		}
	}

	/**
	 * Enhanced by athaboil to test if update_event works with empty Name,type,Description, prefix id, null contact and zero date
	 */
	public void testUpdate_event_EmptyStrings() {
		long resultCode = db.add_event(this.DefaultEvent());
		if(resultCode == 1)
		{
			Event ev1 = this.DefaultEvent();
			ev1.setEvent_name("");
			ev1.setEvent_type("");
			ev1.setEvent_description("");
			ev1.setEvent_contact(null);
			ev1.setEvent_date(0);
			ev1.setEvent_id(1);
			resultCode = db.update_event(ev1);
			assertTrue(resultCode == 1);
		}
		else
		{
			fail("Couldnt add event.");
		}
	}
	/**
	 * Enhanced by athaboil to test if update_event works with null Name,type,Description, prefix id, null contact and zero date
	 */
	public void testUpdate_event_NullStrings() {
		long resultCode = db.add_event(this.DefaultEvent());
		if(resultCode == 1)
		{
			Event ev1 = this.DefaultEvent();
			ev1.setEvent_name(null);
			ev1.setEvent_type(null);
			ev1.setEvent_description(null);
			ev1.setEvent_contact(null);
			ev1.setEvent_date(0);
			ev1.setEvent_id(1);
			resultCode = db.update_event(ev1);
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
		long resultCode = db.add_event(this.DefaultEvent());
		if(resultCode == 1)
		{
			assertEquals(db.get_event(1).getEvent_name(),this.DefaultEvent().getEvent_name());
		}
		else
		{
			fail("Couldnt add event.");
		}
	}
	/**
	 * Enhanced by athaboil to test Get_event, without Event registered in database
	 */
	public void testGet_event_withoutEvent() {
		
		
		assertNull(db.get_event(1));
		
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
		long resultCode = db.add_celebration(this.DefaultCelebration());
		if(resultCode == 1)
		{
			Celebration[] Celebrations = db.get_celebrations(this.DefaultCelebration().getDate());
			if(Celebrations.length > 0)
			{
				if(Celebrations[0].getName() == this.DefaultCelebration().getName())
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
	 * Enhanced by athaboil to test Get_celebrations, without Celebration registered in database
	 */
	public void testGet_celebrations_without_Celebration()
	{
		assertEquals(db.get_celebrations(this.DefaultCelebration().getDate()).length,0);
	}
	
	//PAOK
	
	/**
	 * Test method for {@link com.calerem.controllers.Database#celebration_exists(java.lang.String, java.lang.String)}.
	 */
	public void testCelebration_exists() {
		long resultCode = db.add_celebration(this.DefaultCelebration());
		if(resultCode == 1)
		{
			if(db.celebration_exists(this.DefaultCelebration().getDate(), this.DefaultCelebration().getName()) > 0)
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
		long resultCode = db.add_event(this.DefaultEvent());
		if(resultCode == 1)
		{
			Event ev1 = this.DefaultEvent();
			ev1.setEvent_type("NameDay");
			resultCode = db.add_event(ev1);
			if(resultCode == 2)
			{
				Event tempevents[] = db.get_events(0, System.currentTimeMillis()/1000);
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
	 * Enhanced by athaboil to test Get_events, without Celebration registered in database
	 */
	public void testGet_events_without_event()
	{
		assertEquals(db.get_events(0, (System.currentTimeMillis()/1000)).length,0);
	}

	/**
	 * Test method for {@link com.calerem.controllers.Database#read_configuration()}.
	 */
	public void testRead_configuration() {
		ConfigurationCalerem tempconfig = db.read_configuration();
		assertEquals(tempconfig.getDate_format(),this.DefaultConfigurationCalerem().getDate_format());
	}

	/**
	 * Test method for {@link com.calerem.controllers.Database#update_configuration(com.calerem.classes.ConfigurationCalerem)}.
	 */
	public void testUpdate_configuration() {
		this.DefaultConfigurationCalerem().setDate_format("MM-DD-YYYY");
		long resultCode = db.update_configuration(this.DefaultConfigurationCalerem());
		assertEquals(resultCode,1);
	}

	/**
	 * Test method for {@link com.calerem.controllers.Database#add_celebration(com.calerem.classes.Event)}.
	 */
	public void testAdd_celebration() {
		long resultCode = db.add_celebration(this.DefaultCelebration());
		
		assertTrue(resultCode == 1);
		
	}
	
	/**
	 * Enhanced by athaboil to test Add_celebration, with empty strings and prefix id
	 */
	public void testAdd_celebration_emptyStrings() {
		Celebration c1=this.DefaultCelebration();
		c1.setDate("");
		c1.setId(10);
		c1.setName("");
		c1.setType("");
		long resultCode = db.add_celebration(c1);
		
		assertTrue(resultCode == 1);
		
	}
	
	/**
	 * Enhanced by athaboil to test Add_celebration, with null strings and negative id
	 */
	public void testAdd_celebration_nullStrings() {
		Celebration c1=this.DefaultCelebration();
		c1.setDate(null);
		c1.setId(-10);
		c1.setName(null);
		c1.setType(null);
		long resultCode = db.add_celebration(c1);
		
		assertTrue(resultCode == 1);
		
	}
	/**
	 * Test method for {@link com.calerem.controllers.Database#update_celebration(com.calerem.classes.Event)}.
	 */
	public void testUpdate_celebration() {
		long resultCode = db.add_celebration(this.DefaultCelebration());
		if(resultCode == 1)
		{
			Celebration[] cb =db.get_celebrations(this.DefaultCelebration().getDate());
			resultCode = db.update_celebration(cb[0]);
			assertTrue(resultCode == 1);
		}
		else
		{
			fail("Couldnt add celebration.");
		}
	}

	/**
	 * Enhanced by athaboil to test Update_celebration, with null strings
	 */
	public void testUpdate_celebration_nullStrings() {
		Celebration c1 = this.DefaultCelebration();
		long resultCode = db.add_celebration(c1);
		if(resultCode == 1)
		{
			
			Celebration[] cb =db.get_celebrations(c1.getDate());
			cb[0].setDate(null);
			cb[0].setName(null);
			cb[0].setType(null);
			cb[0].setId(1);
			resultCode = db.update_celebration(cb[0]);
			Log.e("custom",""+resultCode);
			assertTrue(resultCode == 1);
		}
		else
		{
			fail("Couldnt add celebration.");
		}
	}
	/**
	 * Enhanced by athaboil to test Update_celebration, with empty strings
	 */
	public void testUpdate_celebration_emptyStrings() {
		Celebration c1 = this.DefaultCelebration();
		long resultCode = db.add_celebration(c1);
		if(resultCode == 1)
		{
			
			Celebration[] cb =db.get_celebrations(c1.getDate());
			cb[0].setDate("");
			cb[0].setName("");
			cb[0].setType("");
			cb[0].setId(1);
			resultCode = db.update_celebration(cb[0]);
			Log.e("custom",""+resultCode);
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
		long resultCode = db.add_celebration(this.DefaultCelebration());
		if(resultCode == 1)
		{
			Celebration[] cb =db.get_celebrations(this.DefaultCelebration().getDate());
			resultCode = db.delete_celebration(cb[0]);
			assertTrue(resultCode == 1);
		}
		else
		{
			fail("Couldnt add celebration.");
		}
	}
	/**
	 * Enhanced by athaboil to test Delete_celebration without registered Celebration
	 */
	public void testDelete_celebration_without_registered() {
		Celebration cb = null;
		long resultCode = db.delete_celebration(cb);
		assertTrue(resultCode == 0);
	}
	/**
	 * Test method for {@link com.calerem.controllers.Database#truncate_celebrations()}.
	 */
	public void testTruncate_celebrations() {
		long resultCode = db.add_celebration(this.DefaultCelebration());
		if(resultCode == 1)
		{
			this.DefaultEvent().setEvent_description("NameDay");
			resultCode = db.add_celebration(this.DefaultCelebration());
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
	 * Enhanced by athaboil to test Delete_celebration without registered Celebration
	 */
	public void testTruncate_celebrations_withoutCelebrations() {
		long resultCode = db.truncate_celebrations();
		assertTrue(resultCode == 0);
	}

	/**
	 * Test method for {@link com.calerem.controllers.Database#log_sync(com.calerem.classes.SyncLog)}.
	 */
	public void testLog_sync() {
		long resultCode = db.log_sync(this.DefaultSyncLog());
		assertEquals(resultCode,1);
	}
	
	/**
	 * Enhanced by athaboil to test Log_sync with empty type prefix id and date = 0
	 */
	public void testLog_sync_with_emptyStrings() {
		SyncLog s1 = this.DefaultSyncLog();
		s1.setDate(0);
		s1.setType("");
		s1.setId(10);
		long resultCode = db.log_sync(s1);
		assertEquals(resultCode,1);
	}
	/**
	 * Enhanced by athaboil to test Log_sync with null type negative id 
	 */
	public void testLog_sync_with_nullStrings() {
		SyncLog s1 = this.DefaultSyncLog();
		s1.setDate(0);
		s1.setType(null);
		s1.setId(-5);
		long resultCode = db.log_sync(s1);
		assertEquals(resultCode,1);
	}
	/**
	 * Test method for {@link com.calerem.controllers.Database#read_sync_log(int)}.
	 */
	public void testRead_sync_log() {
		long resultCode = db.log_sync(this.DefaultSyncLog());
		if(resultCode == 1)
		{
			this.DefaultSyncLog().setType("Eortologio");
			resultCode = db.log_sync(this.DefaultSyncLog());
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
	 * Enhanced by athaboil to test Log_sync with no entry
	 */
	public void testRead_sync_log_withNOentry() {
			
			
			assertEquals(db.read_sync_log(10).length,0);
			
	}
	
	/**
	 * Test method for {@link com.calerem.controllers.Database#log_messages(com.calerem.classes.MessageLog)}.
	 */
	public void testLog_messages() {
		long resultCode = db.log_messages(this.DefaultMessageLog());
		assertEquals(resultCode,1);
	}
	
	/**
	 * Enhanced by athaboil to test Log_sync with zero date,id empty strings and empty contact
	 */
	
	public void testLog_messages_withOdinOnOurSide() {
		MessageLog m1 = this.DefaultMessageLog();
		m1.setContact(null);
		m1.setDate(0);
		m1.setId(0);
		m1.setMessage("");
		m1.setType("");
		long resultCode = db.log_messages(m1);
		assertTrue(resultCode==0);
	}
	
	/**
	 * Enhanced by athaboil to test Log_sync with zero date null strings, prefix contact and negative id
	 */
	
	public void testLog_messages_withThorOnOurSide() {
		MessageLog m1 = this.DefaultMessageLog();
		m1.setDate(0);
		m1.setId(-5);
		m1.setMessage(null);
		m1.setType(null);
		long resultCode = db.log_messages(m1);
		assertTrue(resultCode==1);
	}
	
	/**
	 * Test method for {@link com.calerem.controllers.Database#read_message_log(int)}.
	 */
	public void testRead_message_log() {
		long resultCode = db.log_messages(this.DefaultMessageLog());
		if(resultCode == 1)
		{
			this.DefaultMessageLog().setMessage("Kati Allo");
			resultCode = db.log_messages(this.DefaultMessageLog());
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
	 * Enhanced by athaboil to test Read_message_log with no entry
	 */
	public void testRead_message_log_MayTheForceBeWithYou() {
			
			
			assertEquals(db.read_message_log(10).length,0);
			
	}
	

	/**
	 * Test method for {@link com.calerem.controllers.Database#add_contact(com.calerem.classes.Contact)}.
	 */
	public void testAdd_contact() {
		long resultCode = db.add_contact(this.DefaultContact());
		assertTrue(resultCode == 1);
	}
	/**
	 * Enhanced by athaboil to test Add_contact with oti na nai values
	 */
	public void testAdd_contact_YodaTrainedMe() {
		Contact c1 = this.DefaultContact();
		c1.setEmail("");
		c1.setLastname("");
		c1.setName("");
		c1.setPhone("");
		c1.setId(-5);
		long resultCode = db.add_contact(c1);
		assertTrue(resultCode == 1);
	}

	/**
	 * Enhanced by athaboil to test Add_contact with oti na nai values
	 */
	public void testAdd_contact_IamGeek() {
		Contact c1 = this.DefaultContact();
		c1.setEmail(null);
		c1.setLastname(null);
		c1.setName(null);
		c1.setPhone(null);
		c1.setId(10);
		long resultCode = db.add_contact(c1);
		assertTrue(resultCode == 1);
	}
	
	/**
	 * Test method for {@link com.calerem.controllers.Database#update_contact(com.calerem.classes.Contact)}.
	 */
	public void testUpdate_contact() {
		long resultCode = db.add_contact(this.DefaultContact());
		if(resultCode == 1)
		{
			Contact c1 =this.DefaultContact();
			c1.setLastname("Test");
			c1.setId(1);
			resultCode = db.update_contact(c1);
			assertTrue(resultCode == 1);
		}
		else
		{
			fail("Couldnt add contact");
		}
	}


	/**
	 * Enhanced by athaboil to test Update_contact with testing values
	 */
	public void testUpdate_contact_Values1() {
		long resultCode = db.add_contact(this.DefaultContact());
		if(resultCode == 1)
		{
			Contact c1 =this.DefaultContact();
			c1.setLastname("");
			c1.setEmail("");
			c1.setName(null);
			c1.setPhone(null);
			c1.setId(1);
			resultCode = db.update_contact(c1);
			assertTrue(resultCode == 1);
		}
		else
		{
			fail("Couldnt add contact");
		}
	}
	/**
	 * Enhanced by athaboil to test Update_contact without entry
	 */
	public void testUpdate_contact_NoEntry() {
			Contact c1 =this.DefaultContact();
			c1.setLastname("");
			c1.setEmail("");
			c1.setName(null);
			c1.setPhone(null);
			c1.setId(1);
	long	resultCode = db.update_contact(c1);
			assertTrue(resultCode == 0);
		
	}
	/**
	 * Test method for {@link com.calerem.controllers.Database#get_contact(int)}.
	 */
	public void testGet_contact() {
		long resultCode = db.add_contact(this.DefaultContact());
		if(resultCode == 1)
		{
		
			assertEquals(db.get_contact(1).getName(),this.DefaultContact().getName());
		}
		else
		{
			fail("Couldnt add contact");
		}
	}

	/**
	 * Enhanced by athaboil to test Get_contact without entry
	 */
	public void testGet_contact_noEntry() {
		assertNull(db.get_contact(1));
	}
	/**
	 * Test method for {@link com.calerem.controllers.Database#get_contacts()}.
	 */
	public void testGet_contacts() {
		long resultCode = db.add_contact(this.DefaultContact());
		if(resultCode == 1)
		{
			this.DefaultContact().setName("Mitsos");
			resultCode = db.add_contact(this.DefaultContact());
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
	/**
	 * Enhanced by athaboil to test Get_contacts without entry
	 */
	public void testGet_contactsNoEntry() {
		
				Contact tempcontacts[] = db.get_contacts();
				assertEquals(tempcontacts.length,0);
	}
	private Event DefaultEvent()
	 {
	  return new Event("Birthday","My Birthday",(System.currentTimeMillis()/1000),this.DefaultContact(),null,"Epic Fail Date");
	 }

	private Contact DefaultContact() {
		// TODO Auto-generated method stub
		return new Contact("my_name","my_lastname","my_phone","my_email",null);
	}
	private MessageLog DefaultMessageLog()
	{
		return new MessageLog((System.currentTimeMillis()/1000),null,"my_type","my_message",this.DefaultContact());
	}
	private ConfigurationCalerem DefaultConfigurationCalerem()
	{
		return new ConfigurationCalerem("dd-MM-yyyy","my_notification_sound","my_lang","my_skin","my_gr","my_en");
	}
	private SyncLog DefaultSyncLog()
	{
		return new SyncLog((System.currentTimeMillis()/1000),"my_type",null);
	}
	private Celebration DefaultCelebration()
	{
		return new Celebration("my_type","my_name","my_date",null);
	}

}
