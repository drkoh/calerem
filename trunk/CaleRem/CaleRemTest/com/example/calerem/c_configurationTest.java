package com.example.calerem;

import static org.junit.Assert.*;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class c_configurationTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

     
	@Test
	public void testF_set_date_format() {
		String v1 = "DD-MM-YYYY";
		String v2 = "MM-DD-YYYY";
		String v_date_format=null;
        c_configuration tester1= new c_configuration();
        c_configuration tester2= new c_configuration();
        
        tester1.f_set_date_format(v1);
        tester2.f_set_date_format(v2);
        assertEquals(v1,v_date_format);
        assertEquals(v2,v_date_format);
        
     
		//fail("Not yet implemented");
	}

//	@Test
	//public void testF_set_notification_sound() {
		//fail("Not yet implemented");
	//}

	//@Test
	//public void testF_set_language() {
		//fail("Not yet implemented");
	//}

	//@Test
	//public void testF_set_skin() {
		//fail("Not yet implemented");
	//}

}
