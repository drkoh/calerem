package com.calerem.tests.classes;

import com.calerem.classes.Contact;
import com.calerem.classes.Event;

import junit.framework.TestCase;

public class EventTest extends TestCase
{
	
	Contact c1 = new Contact("Nikos","Papadolias","6970001111","nikos@hotlol.com",001);
	Contact c2 = new Contact("","","","",0);
	Event eve1 = new Event ("Birthday", "Party", 1111111111, c1, 01, "Best Party Ever!!!");
	Event eve2 = new Event ("", "", 0, c2, 0, "");
	
	public void testEvent()
	{
		
		
		assertEquals(eve1.getEvent_type(), "Birthday");
		assertEquals(eve1.getEvent_name(),"Party");
		assertEquals(eve1.getEvent_date(),1111111111);
		assertEquals(eve1.getEvent_contact(),c1);
	//	assertEquals(eve1.getEvent_id(),"01");
		assertEquals(eve1.getEvent_description(),"Best Party Ever!!!");

		
	}

	public void testGetEvent_type()
	{
		String type=eve1.getEvent_type();
		assertEquals(type,"Birthday");
	}

	public void testSetEvent_type()
	{
		String type="Birthday";
		eve2.setEvent_type(type);
		assertEquals(eve2.getEvent_type(),type);
	}

	public void testGetEvent_name()
	{
		String name=eve1.getEvent_name();
		assertEquals(name,"Party");
	}

	public void testSetEvent_name()
	{
		String name="Party";
		eve2.setEvent_name(name);
		assertEquals(eve2.getEvent_name(),name);
	}

	public void testGetEvent_date()
	{
		assertEquals(eve1.getEvent_date(),1111111111);
	}

	public void testSetEvent_date()
	{
		//eve2.setEvent_date(1111111111);
		assertEquals(String.valueOf(eve1.getEvent_date()),"1111111111");
	}

	public void testGetEvent_contact()
	{
		Contact con = eve1.getEvent_contact();
		assertEquals(con,c1);
	}

	public void testSetEvent_contact()
	{
		Contact con = c1;
		eve2.setEvent_contact(con);
		assertEquals(eve2.getEvent_contact(),con);
	}

	public void testGetEvent_id()
	{
		int id= eve1.getEvent_id();
		assertEquals(id, 01);
	}

	public void testSetEvent_id()
	{
		int id = 01;
		eve2.setEvent_id(id);
		assertEquals(String.valueOf(eve2.getEvent_id()),"01");
	}

	public void testGetEvent_description()
	{
		String desc = eve1.getEvent_description();
		assertEquals(desc,"Best Party Ever!!!");
	}

	public void testSetEvent_description()
	{
		String desc = "Best Party Ever!!!";
		eve2.setEvent_description(desc);
		assertEquals(eve2.getEvent_description(),desc);
	}

}
