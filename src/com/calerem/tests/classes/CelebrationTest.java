package com.calerem.tests.classes;

import org.junit.Test;

import com.calerem.classes.Celebration;

import junit.framework.TestCase;

public class CelebrationTest extends TestCase {
	
	Celebration emptyCeleb = new Celebration ("","","",0), savedCeleb = new Celebration("Birthday","Mary","11.11.1991",1);
	
	@Test 
	public void testGetTypeEmpty() {
		String type = emptyCeleb.getType();
		assertEquals(type,"");
	}
	
	@Test 
	public void testGetTypeSaved() {
		String type = savedCeleb.getType();
		assertEquals(type,"Birthday");
	}
	
	@Test
	public void testGetNameEmpty () {
		String name = emptyCeleb.getName();
		assertEquals(name,"");
	}
	
	@Test
	public void testGetNameSaved () {
		String name = savedCeleb.getName();
		assertEquals(name,"Mary");
	}
	
	@Test
	public void testGetDateEmpty () {
		String date = emptyCeleb.getDate();
		assertEquals(date,"");
	}
	
	@Test 
	public void testGetDateSaved () {
		String date = savedCeleb.getDate();
		assertEquals(date,"11.11.1991");
	}
	
	@Test
	public void testGetIdEmpty () {
		int id = emptyCeleb.getId();
		assertEquals(id,0);
	}
	
	@Test
	public void testGetIdSaved () {
		int id = savedCeleb.getId();
		assertEquals(id,1);
	}
	
	@Test 
	public void testSetTypeEmpty () {
		String type = "Birthday"; 
		emptyCeleb.setType(type);
		assertEquals(emptyCeleb.getType(),type);
	}
	
	@Test 
	public void testSetTypeSaved () {
		String type = "Nameday"; 
		savedCeleb.setType(type);
		assertEquals(savedCeleb.getType(),type);
	}
	
	@Test
	public void testSetNameEmpty () {
		String name = "Mary"; 
		emptyCeleb.setName(name);
		assertEquals(emptyCeleb.getName(),name);
	}
	
	@Test
	public void testSetNameSaved () {
		String name = "Nasia"; 
		savedCeleb.setName(name);
		assertEquals(savedCeleb.getName(),name);
	}
	
	@Test
	public void testSetDateEmpty () {
		String date = "11.11.1991"; 
		emptyCeleb.setDate(date);
		assertEquals(emptyCeleb.getDate(),date);
	}
	
	@Test 
	public void testSetDateSaved () {
		String date = "1.1.1991"; 
		savedCeleb.setDate(date);
		assertEquals(savedCeleb.getDate(),date);
	}
	
	@Test
	public void testSetIdEmpty () {
		int id = 1,exampleId;
		emptyCeleb.setId(id);
		exampleId = emptyCeleb.getId();
		assertEquals(exampleId,1);
	}
	
	@Test
	public void testSetIdSaved () {
		int id = 11,exampleId;
		savedCeleb.setId(id);
		exampleId = savedCeleb.getId();
		assertEquals(exampleId,11);
	}
}
