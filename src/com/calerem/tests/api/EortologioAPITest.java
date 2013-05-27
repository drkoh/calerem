/**
 * 
 */
package com.calerem.tests.api;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import android.content.Context;
import android.test.InstrumentationTestCase;
import com.calerem.api.EortologioAPI;
import com.calerem.classes.Celebration;

/**
 * Tests for EortologioAPI.
 * @author DarkParadise
 */
public class EortologioAPITest extends InstrumentationTestCase {

	private EortologioAPI api;
	private static final String grURL = "http://www.eortologio.gr/rss/si_av_me_el.xml",enURL = "http://www.eortologio.gr/rss/si_av_me_en.xml";
	private Context basecontext;
	/**
	 * Setup basic values for tests to run.
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		this.basecontext = getInstrumentation().getContext();
		this.api = new EortologioAPI(this.basecontext);
	}
	/**
	 * Tests normal operation.
	 * Test method for {@link com.calerem.api.EortologioAPI#getFile(java.lang.String, java.lang.String)}.
	 * @throws IOException 
	 */
	public void testGetFile() throws IOException 
	{
		this.api.getFile(grURL, enURL);
		File GrFile = new File(basecontext.getApplicationContext().getCacheDir().getAbsolutePath()+"/Calerem/eortologio/eortologio_gr.xml");
		File EnFile = new File(basecontext.getApplicationContext().getCacheDir().getAbsolutePath()+"/Calerem/eortologio/eortologio_en.xml");
		if(GrFile.exists() && EnFile.exists())
		{
			assertTrue(true);
		}
		else
		{
			fail("Couldnt find the files.");
		}
		GrFile.delete();
		EnFile.delete();
	}

	/**
	 * Test method for {@link com.calerem.api.EortologioAPI#readXML()}.
	 * @throws IOException 
	 * @throws ParserConfigurationException 
	 * @throws SAXException 
	 */
	public void testReadXML() throws IOException, SAXException, ParserConfigurationException 
	{
		this.api.getFile(grURL, enURL);
		Celebration[] celebrations = this.api.readXML();
		if(celebrations.length > 0)
		{
			assertTrue(true);
		}
		else
		{
			fail("Celebrations array seems empty.");
		}
	}

}
