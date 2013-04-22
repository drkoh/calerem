package com.calerem.tests;

import static org.junit.Assert.*;

import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;

import com.calerem.api.EortologioApi;

/**
 * Database Tests in Junit 4.
 * @author agapi
 */

public class EortologioApiTest {


	private String xmlStrEn;
	private String xmlStrEl;
	private Document documentEn;
	private Document documentEl;
	
	@Before
	public void initVars() {
		xmlStrEn = "http://www.eortologio.gr/rss/si_en.xml";
		xmlStrEl = "http://www.eortologio.gr/rss/si_el.xml";
		documentEn = generateXml(xmlStrEn);
		documentEl = generateXml(xmlStrEl);
	}
	
	private static Document generateXml (String str) {
		try {
			URL url = new URL(str);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(url.openStream());
			return doc;
		} catch (Exception ex) {
			Document doc = null;
			ex.printStackTrace();
			return doc;
		}
	}

	@Test
	public void getXmlEnTest() {
		new EortologioApi();
		/*
		 * Generate English Namedays end compare XML
		 */
		Document doc = EortologioApi.getXml(xmlStrEn);
		assertEquals(doc.toString(),documentEn.toString());
	}
	
	@Test
	public void getXmlElTest() {
		/*
		 * Generate Greek Namedays end compare XML
		 */
		Document doc = EortologioApi.getXml(xmlStrEn);
		assertEquals(doc.toString(),documentEl.toString());
	}


}
