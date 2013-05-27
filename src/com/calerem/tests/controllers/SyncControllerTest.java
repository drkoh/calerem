/**
 * 
 */
package com.calerem.tests.controllers;

import java.text.SimpleDateFormat;
import java.util.Locale;

import com.calerem.classes.Celebration;
import com.calerem.controllers.Database;
import com.calerem.controllers.SyncController;

import android.content.Context;
import android.test.InstrumentationTestCase;

/**
 * @author DarkParadise
 *
 */
public class SyncControllerTest extends InstrumentationTestCase {

	private Context basecontext;
	private SyncController controller;
	private Database db;
	/**
	 * Set up basic test variables.
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception 
	{
		super.setUp();
		this.basecontext = getInstrumentation().getContext();
		this.controller = new SyncController(this.basecontext);
		this.db = new Database(this.basecontext);
	}

	/**
	 * Test method for {@link com.calerem.controllers.SyncController#SyncEortologio()}.
	 */
	public void testSyncEortologio() 
	{
		this.controller.SyncEortologio();
		Celebration[] celebrations = this.db.get_celebrations(new SimpleDateFormat("dd-MM",Locale.ENGLISH).format(System.currentTimeMillis()));
		if(celebrations.length > 0)
		{
			assertTrue(true);
		}
		else
		{
			fail("Couldnt retrieve from database.");
		}
	}
	/**
	 * Return database to defaults and close it.
	 * @see android.test.InstrumentationTestCase#tearDown()
	 */
	protected void tearDown() throws Exception 
	{
		this.db.factory_reset();
		this.db.close();
	}

}
