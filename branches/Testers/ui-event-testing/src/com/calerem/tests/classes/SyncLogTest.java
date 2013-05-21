package com.calerem.tests.classes;

import com.calerem.classes.SyncLog;

import junit.framework.TestCase;

public class SyncLogTest extends TestCase {

	public void testSyncLog() {
		SyncLog l1 = new SyncLog(123456, "asd", 2);
		assertEquals(l1.getType(),"asd");
		assertEquals(l1.getDate(),123456);
	
		
	}

	public void testGetDate() 
	{
		SyncLog l1 = new SyncLog(123456, "asd", null);
		assertEquals(String.valueOf(l1.getDate()),"123456");
	}

	public void testSetDate() 
	{
		SyncLog l1 = new SyncLog(123456, "asd", null);
		l1.setDate(12345);
		assertEquals(String.valueOf(l1.getDate()),"12345");
	}

	public void testGetType() 
	{
		SyncLog l1 = new SyncLog(123456, "asd", 2);
		assertEquals(l1.getType(),"asd");
	}

	public void testSetType() {
		SyncLog l1 = new SyncLog(123456, "asd", 2);
		l1.setType("asdasd");
		assertEquals(l1.getType(),"asdasd");
	}

	public void testGetId() {
	//	fail("Not yet implemented");
		SyncLog l1 = new SyncLog(123456, "asd", 2);
		assertEquals(String.valueOf(l1.getId()), "2");
	}

	public void testSetId() {
	//	Sto asserEquals evala 2 anti gia 5 gia na apotuxei
		SyncLog l1 = new SyncLog(123456, "asd", 2);
		l1.setId(5);
		assertEquals(String.valueOf(l1.getId()), "2");
	}

}
