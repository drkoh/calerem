package com.calerem.api;

import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class EortologioApi {

			//final static File eortologio = new File ("eortologio.xml");
			//final static File eortologioEn = new File ("eortologioEn.xml");
			
			final static String XmlStrEn = "http://www.eortologio.gr/rss/si_en.xml";
			final static String XmlStrEl = "http://www.eortologio.gr/rss/si_el.xml";	
			
			
			public static ArrayList<String> parseVals(){
				EortologioApi ea = new EortologioApi();
				ArrayList<String> values =new ArrayList<String> ();
				values = ea.returnVal();
				return values;
			}
		
			public ArrayList<String> returnVal() {
				try {
					ArrayList<String> result = new ArrayList<String> () ;			
					//Document docEl = dBuilder.parse(eortologio);
					//Document docEn = dBuilder.parse(eortologioEn);
					Document docEl = getXml(XmlStrEn);
					Document docEn =  getXml(XmlStrEl);
					
					result = getNames(docEl);
					result.addAll(getNames(docEn));
					
					result = validateResult(result);
					
					return result;	
					
				} catch (Exception ex) {
					ArrayList<String> result = new ArrayList<String> () ;
					ex.printStackTrace();
					return result;
				}
				
			}
			
			public static String getValue (String tag, Element element) {
				NodeList nodes = element.getElementsByTagName(tag).item(0).getChildNodes();
				Node node = (Node) nodes.item(0);
				return node.getNodeValue();
			}
			
			public static ArrayList<String> getNames (Document doc) {
				String eortologioStr;
				ArrayList<String> result = new ArrayList<String> () ;
				String [] resultstr = null;
				
				doc.getDocumentElement().normalize();
				NodeList nodeList = doc.getElementsByTagName("item");
				
				for (int i = 0; i<nodeList.getLength(); i++) {
					Node node = nodeList.item(i);
					
					if (node.getNodeType()== Node.ELEMENT_NODE) {
						Element elementEl = (Element) node;
						eortologioStr =  getValue("title",elementEl);
											
						resultstr = createStringArray (eortologioStr);
											
						resultstr[0]=resultstr[0].toString().substring(resultstr[0].indexOf(":")+1,resultstr[0].length());
						
						result = convertStringToArraylist(resultstr);
					}
										
				}
				return result;
			}
			
			
			public static String [] createStringArray (String str) {
				String [] string = null;
				String splitter=",";
				int resCount;
				string = str.split(splitter);
				
				resCount = string.length-1;
				string[0]=string[0].toString().substring(string[0].indexOf(":")+1,string[0].length());
				string[resCount]=string[resCount].toString().substring(0,string[resCount].indexOf("("));
				
				return string;
			}
			
			public static ArrayList<String> convertStringToArraylist(String [] str) {
			    ArrayList<String> charList = new ArrayList<String>();      
			    for(int i = 0; i<str.length;i++){
			        charList.add(str[i]);
			    }
			    return charList;
			}
			
			public static ArrayList<String> validateResult(ArrayList<String> arrayList) {
				String noNames = "no widely known nameday ";
				if (arrayList.toString().matches(".*"+noNames+".*"))
					arrayList.clear();
				return arrayList;
			}
			
			public Document getXml(String str) {
				try {
					URL url = new URL(str);
					DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
					Document doc = dBuilder.parse(url.openStream());
					return doc;
				} catch (Exception ex) {
					Document doc = null;
					ex.printStackTrace();
					return doc ;
				}
			}
}
