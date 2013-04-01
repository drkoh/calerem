package com.calerem.test;

import junit.framework.TestCase;
import com.calerem.classes.c_configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class t_configuration extends TestCase{

	
	@Before
	public void setUp() throws Exception {
		
		

	}

	@After
	public void tearDown() throws Exception {
	}

	@SuppressWarnings("static-access")
	@Test
	public final void testC_configurationStringStringStringStringString() {
		String date="DD-MM-YYYY";
		String sound="mp3File";
		String lang="English";
		String skin="Skins";
		String eort="xmlFile";
		c_configuration tester = new c_configuration(date,sound,lang,skin,eort);
		assertEquals("DD-MM-YYYY",tester.v_date_format);
		assertEquals("mp3File",tester.v_notification_sound);
		assertEquals("English",tester.v_language);
		assertEquals("Skins",tester.v_skin);
		assertEquals("xmlFile",tester.v_eortologio_xml);
	}

	@Test
	public final void testC_configuration() {
		c_configuration tester = new c_configuration();
		tester.equals(null);
		
	}


	@Test
	public final void testF_set_date_format1() {
		String date="MM-DD-YYYY";
		c_configuration.f_set_date_format(date);
		assertEquals("MM-DD-YYYY",c_configuration.v_date_format);
	}
	
	@Test
	public final void testF_set_date_format2() {
		String date="DD-MM-YYYY";
		c_configuration.f_set_date_format(date);
		assertEquals("DD-MM-YYYY",c_configuration.v_date_format);
	}
	
	@Test
	public final void testF_set_date_format3() {
		String date="CaleRem";
		c_configuration.f_set_date_format(date);
		assertEquals("CaleRem",c_configuration.v_date_format);
	}

	@Test
	public final void testF_set_notification_sound() {
		fail("Not yet implemented");
	}

	@Test
	public final void testF_set_language() {
		fail("Not yet implemented");
	}

	@Test
	public final void testF_set_skin() {
		fail("Not yet implemented");
	}

}
