package com.example.calerem;

import static org.junit.Assert.*;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class c_contactTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

     
	@Test
	public void testc_contact() {
		String v1 = v_name;
		String v2 = v_lastname;
		int v3 = v_phone;
		String v4 = v_email;
		int v5 = v_id;
		
		String v_name=null;
		String v_lastname=null;
		int v_phone=null;
		String v_email=null;
		int v_id=null;
        
		c_contact tester1= new c_contact();
        c_contact tester2= new c_contact();
        c_contact tester3= new c_contact();
        c_contact tester4= new c_contact();
        c_contact tester5= new c_contact();
        
        tester1.c_contact(v1);
        tester2.c_contact(v2);
        tester3.c_contact(v3);
        tester4.c_contact(v4);
        tester5.c_contact(v5);
        
        assertEquals(v1,v_name);
        assertEquals(v2,v_lastname);
        assertEquals(v3,v_phone);
        assertEquals(v4,v_email);
        assertEquals(v5,v_id);
        
     
		//fail("Not yet implemented");
	}
