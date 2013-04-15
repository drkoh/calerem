package com.calerem.api;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.os.Environment;

/**
 * Enables the application to read eortologio.gr
 * @author DarkParadise
 */
public class Eortologio_API extends Activity{
	
	/**
	 * Retrieves the xml file to /sdcard/Calerem/eortologio/eortologio.xml
	 * @param String v_URL
	 * @throws Exception
	 */
	public void getFile(String v_URL) throws Exception
	{
		URL url = new URL(v_URL);

		//dimiourgia neas sindesis
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		urlConnection.setRequestMethod("GET");
		urlConnection.connect();
		
		//dimiourgia fakelou calerem/eortologio
		File SDCardCalerem = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Calerem/eortologio/");
		if(!SDCardCalerem.exists())
		{
			SDCardCalerem.mkdirs();
		}
		
		//dimiourgia neou arxiou kai dieukrinisi tou onomatos tou
		File file = new File(SDCardCalerem + "/eortologio.xml");
		if(!file.exists())
		{
			file.createNewFile();
		}
		FileOutputStream fileOutput = new FileOutputStream(file);
		InputStream inputStream = urlConnection.getInputStream();

		byte[] buffer = new byte[1024];
		int bufferLength = 0; //apothikeusi prosirinou megethous tou buffer

		//diavazei apo to internet kai apothikeuei sto arxeio mas.
		while ( (bufferLength = inputStream.read(buffer)) > 0 ) 

		{
			fileOutput.write(buffer, 0, bufferLength);
		}

		fileOutput.close();
	}
}
