package com.calerem.api;

import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;
/*import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;*/

/**
 * 
 * @author Agapi
 * 
 *         EortologioApi is a class that connects to www.eortologio.gr and
 *         provides 2 XML files witch contain the data needed to figure out the
 *         name days of the present day
 * 
 */

public class EortologioApi {

	final private String XmlStrEn = "http://www.eortologio.gr/rss/si_en.xml";
	final private String XmlStrGr = "http://www.eortologio.gr/rss/si_el.xml";

	static Document docGr;
	static Document docEn;
	static ArrayList<String> eortologioArrayList;

	public ArrayList<String> returnGreekVal() {
		ArrayList<String> result = new ArrayList<String>();
		docEn = getXml(XmlStrEn);
		result = getNames(docGr);
		result = validateResult(result);
		return result;
	}
	
	public ArrayList<String> returnVal() {
		try {
			ArrayList<String> result = new ArrayList<String>();
			docEn = getXml(XmlStrEn);
			docGr = getXml(XmlStrGr);
			result = getNames(docGr);
			result.addAll(getNames(docEn));

			result = validateResult(result);

			return result;

		} catch (Exception ex) {
			ArrayList<String> result = new ArrayList<String>();
			ex.printStackTrace();
			return result;
		}

	}

	public static ArrayList<String> getNames(Document doc) {
		String eortologioStr;
		ArrayList<String> result = new ArrayList<String>();
		String[] resultstr = null;

		doc.getDocumentElement().normalize();
		NodeList nodeList = doc.getElementsByTagName("item");

		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);

			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element elementEl = (Element) node;
				eortologioStr = getValue("title", elementEl);

				resultstr = createStringArray(eortologioStr);

				resultstr[0] = resultstr[0].toString().substring(resultstr[0].indexOf(":") + 1, resultstr[0].length());

				result = convertStringToArraylist(resultstr);
			}

		}
		return result;
	}

	public static String getValue(String tag, Element element) {
		NodeList nodes = element.getElementsByTagName(tag).item(0).getChildNodes();
		Node node = (Node) nodes.item(0);
		return node.getNodeValue();
	}
	
	public static String[] createStringArray(String str) {
		String[] string = null;
		String splitter = ",";
		int resCount;
		string = str.split(splitter);

		resCount = string.length - 1;
		string[0] = string[0].toString().substring(string[0].indexOf(":") + 1, string[0].length());
		string[resCount] = string[resCount].toString().substring(0, string[resCount].indexOf("("));

		return string;
	}

	/**
	 * Converts String [] to an ArrayList of String.
	 * 
	 * @param str
	 * @return
	 */

	public static ArrayList<String> convertStringToArraylist(String[] str) {
		ArrayList<String> charList = new ArrayList<String>();
		for (int i = 0; i < str.length; i++) {
			charList.add(str[i]);
		}
		return charList;
	}

	/**
	 * Validates if the ArrayList not containing name but trash. if it contains
	 * trash the ArrayList will be cleared.
	 * 
	 * @param arrayList
	 * @return
	 */

	public static ArrayList<String> validateResult(ArrayList<String> arrayList) {
		String noNames = "no widely known nameday ";
		if (arrayList.toString().matches(".*" + noNames + ".*"))
			arrayList.clear();
		return arrayList;
	}

	/**
	 * Generates the XML code from the string given as parameter.
	 * 
	 * @param str
	 * @return
	 */

	public static Document getXml(final String str) {
		try {
			URL url = new URL(str);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(url.openStream());
			return doc;
		} catch (Exception ex) {
			Document doc = null;
			ex.printStackTrace();
			return doc;
		}
	}
}
