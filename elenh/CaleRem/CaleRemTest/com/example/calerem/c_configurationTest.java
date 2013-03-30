package com.example.calerem;

import static org.junit.Assert.*;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class c_configurationTest {

c_configuration c_config = new c_configuration(null, null, null, null, null);

	
	@Test
	public void testF_set_date_format() {
		String date = "DD-MM-YYYY";
		c_config.f_set_date_format(date);
		assertEquals(date,c_config.v_date_format);
		date = "MM-DD-YYYY";
		c_config.f_set_date_format(date);
		assertEquals(date,c_config.v_date_format);
	}

	@Test
	public void testF_set_notification_sound() {
		String sound = "my notification.mp3";
		c_config.f_set_notification_sound(sound);
		assertEquals(sound,c_config.v_notification_sound);
	}

	@Test
	public void testF_set_language() {
		String lang = "Greek";
		c_config.f_set_language(lang);
		assertEquals(lang,c_config.v_language);
	}

	@Test
	public void testF_set_skin() {
		String skin = "Black";
		c_config.f_set_skin(skin);
		assertEquals(skin,c_config.v_skin);
	}

}