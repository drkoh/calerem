package com.calerem.api;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.app.Activity;
import android.os.Environment;

import com.calerem.classes.Celebration;

/**
 * Enables the application to read eortologio.gr
 * @author DarkParadise
 */
public class EortologioAPI extends Activity{

	/**
	 * Creates the HTTP connection.
	 * @param String myURL
	 * @return InputStream
	 * @throws IOException
	 */
	private InputStream openConnection(String myURL) throws IOException
	{
		URL url = new URL(myURL);
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		urlConnection.setRequestMethod("GET");
		urlConnection.connect();
		return urlConnection.getInputStream();
	}

	/**
	 * Creates the file system to save the file from the web.
	 * @return File
	 * @throws IOException
	 */
	private File createFileSystem(String language) throws IOException
	{
		File SDCardCalerem = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Calerem/eortologio/");
		if(!SDCardCalerem.exists())
		{
			SDCardCalerem.mkdirs();
		}

		File file = new File(SDCardCalerem + "/eortologio_" + language + ".xml");
		if(!file.exists())
		{
			file.createNewFile();
		}
		return file;
	}

	/**
	 * Retrieves the 2 xml files from the internet.
	 * @param String grURL
	 * @param String enURL
	 * @throws IOException
	 */
	public void getFile(String grURL, String enURL) throws IOException
	{
		FileOutputStream fileOutput = new FileOutputStream(this.createFileSystem("gr"));
		InputStream inputStream = this.openConnection(grURL);
		byte[] buffer = new byte[1024];
		int bufferLength = 0;
		while ( (bufferLength = inputStream.read(buffer)) > 0 ) 
		{
			fileOutput.write(buffer, 0, bufferLength);
		}
		fileOutput.close();

		fileOutput = new FileOutputStream(this.createFileSystem("en"));
		inputStream = this.openConnection(enURL);
		buffer = new byte[1024];
		bufferLength = 0;
		while ( (bufferLength = inputStream.read(buffer)) > 0 ) 
		{
			fileOutput.write(buffer, 0, bufferLength);
		}
		fileOutput.close();		
	}

	/**
	 * Parse the XML File.
	 * @return ArrayList<String>
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	private ArrayList<String> parseXML(String language) throws SAXException, IOException, ParserConfigurationException
	{
		File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Calerem/eortologio/eortologio_" + language + ".xml");
		ArrayList<String> XMLCelebrations = new ArrayList<String>();
		if(file.exists())
		{
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(file);
			doc.getDocumentElement().normalize();
			NodeList nodes = doc.getElementsByTagName("item");
			for(int i=0;i<nodes.getLength();i++)
			{
				XMLCelebrations.add(nodes.item(i).getChildNodes().item(0).getFirstChild().getNodeValue());
			}
		}
		return XMLCelebrations;
	}

	/**
	 * Splits the string returned from XML to actual names.
	 * @param ArrayList<String> XMLCelebrations
	 * @return ArrayList<String>
	 */
	private ArrayList<String> splitNamesString(String XMLCelebrations)
	{
		ArrayList<String> Celebrations = new ArrayList<String>();
		String tempStrings[] = XMLCelebrations.substring(XMLCelebrations.indexOf(":")+2).replace(" (πηγή : www.eortologio.gr)", "").replace(" (source : www.namedays.gr)", "").split(", ");
		for(int j=0;j<tempStrings.length;j++)
		{
			Celebrations.add(tempStrings[j].replace(" ", ""));	
		}
		return Celebrations;
	}

	/**
	 * Clears the strings and prepares the objects.
	 * @param XMLCelebrations
	 * @return Celebration[]
	 */
	private Celebration[] prepareCelebrations(ArrayList<String> XMLCelebrations)
	{
		ArrayList<Celebration> tempCelebrations = new ArrayList<Celebration>();
		for(int i=0;i<XMLCelebrations.size();i++)
		{
			ArrayList<String> CelebrationStrings = this.splitNamesString(XMLCelebrations.get(i));
			for(int j=0;j<CelebrationStrings.size();j++)
			{
				SimpleDateFormat epoch_parser  = new SimpleDateFormat("dd-MM",Locale.ENGLISH);
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(new Date());
				calendar.add(Calendar.DATE, + i);
				if(!(CelebrationStrings.get(j).contains("δενυπάρχει") || CelebrationStrings.get(j).contains("nowidely")))
				{
					tempCelebrations.add(new Celebration("Nameday",CelebrationStrings.get(j),epoch_parser.format(calendar.getTimeInMillis())));	
				}
			}
		}
		Celebration celebrations[] = new Celebration[tempCelebrations.size()];
		for(int i=0;i<tempCelebrations.size();i++)
		{
			celebrations[i] = tempCelebrations.get(i);
		}
		return celebrations;
	}

	/**
	 * Takes the 2 arrays and combines them into one.
	 * @param Celebration[] grcelebrations
	 * @param Celebration[] encelebrations
	 * @return Celebration[]
	 */
	private Celebration[] combineArrays(Celebration[] grcelebrations,Celebration[] encelebrations)
	{
		Celebration celebrations[] = new Celebration[grcelebrations.length + encelebrations.length];
		System.arraycopy(grcelebrations, 0, celebrations, 0, grcelebrations.length);
		System.arraycopy(encelebrations, 0, celebrations, grcelebrations.length, encelebrations.length);
		return celebrations;
	}

	/**
	 * Does all steps to get the array from the 2 xml files.
	 * @return Celebration[]
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	public Celebration[] readXML() throws SAXException, IOException, ParserConfigurationException
	{
		ArrayList<String> XMLCelebrations = this.parseXML("gr");
		Celebration grcelebrations[] = this.prepareCelebrations(XMLCelebrations);
		XMLCelebrations = this.parseXML("en");
		Celebration encelebrations[] = this.prepareCelebrations(XMLCelebrations); 
		Celebration celebrations[] = this.combineArrays(grcelebrations, encelebrations);
		return celebrations;
	}
}
