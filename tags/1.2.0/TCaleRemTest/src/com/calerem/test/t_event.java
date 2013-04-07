package com.calerem.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.calerem.classes.c_contact;
import com.calerem.classes.c_event;


public class t_event {
	c_contact test1 = new c_contact("a","b",5,"c",9);
	c_event tester1 = new c_event("a","b",1,test1,2,"c");
	
	@Test
	public void testC_event() {
		
		c_contact test2 = new c_contact("a","b",5,"c",9);
		c_event tester2 = new c_event("a","b",1,test2,2,"c");
		assertEquals("a",tester2.v_event_type);
		
	}

	@Test
	public void testF_change_event_type() {
		String new_type = "d";
		tester1.f_change_event_type(new_type);
		assertEquals(new_type,tester1.v_event_type);
	}

	@Test
	public void testF_change_event_name() {
		tester1.f_change_event_name("e");
		assertEquals("e",tester1.v_event_name);
	}

	@Test
	public void testF_change_event_date() {
		tester1.f_change_event_date(4);
		assertEquals("4",tester1.v_event_date.toString());
	}

	@Test
	public void testF_change_event_contact() {
		c_contact test3 = new c_contact("z","x",10,"v",18);
		tester1.f_change_event_contact(test3);
		assertEquals(test3.v_name,tester1.v_event_contact.v_name);
		assertEquals(test3.v_lastname,tester1.v_event_contact.v_lastname);
		assertEquals(test3.v_id.toString(),tester1.v_event_contact.v_id.toString());
		assertEquals(test3.v_email,tester1.v_event_contact.v_email);
		assertEquals(test3.v_phone.toString(),tester1.v_event_contact.v_phone.toString());
	}

	@Test
	public void testF_change_event_description() {
		tester1.f_change_event_description("nameday");
		assertEquals("nameday",tester1.v_event_description);
		
	}

}
