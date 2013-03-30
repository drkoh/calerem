package com.example.calerem;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;

import android.app.Activity;

public class c_eortologio_API extends Activity{

	public String v_file_url;
	public String v_file_path;
	
	public String f_get_file;
	{
		URL url = new URL("http://www.eortologio.gr/rss/si_av_me_el.xml");

		//dimiourgia neas sindesis

		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

		urlConnection.setRequestMethod("GET");

		urlConnection.setDoOutput(true);

		//prosthiki periexomenou

		urlConnection.connect();

		//to path pou apothikeuete to xml

		File SDCardRoot = new File("/sdcard/"+"Calerem/");

		//dimiourgia neou arxiou kai dieukrinisi tou onomatos tou

		File file = new File(SDCardRoot,"eortologio.xml");

		FileOutputStream fileOutput = new FileOutputStream(file);

		InputStream inputStream = urlConnection.getInputStream();

		//oliko megethos arxiou

		int totalSize = urlConnection.getContentLength();

		//metavliti pou apothikeuonte ta lifthenta arxia

		int downloadedSize = 0;

		byte[] buffer = new byte[1024];

		int bufferLength = 0; //apothikeusi prosirinou megethous tou buffer

		//diavazei apo t bufer k apothikeuei st arxio

		while ( (bufferLength = inputStream.read(buffer)) > 0 ) 

		{

		fileOutput.write(buffer, 0, bufferLength);

		//prosthetei ta megethei gia to sinoliko

		downloadedSize += bufferLength;

		int progress=(int)(downloadedSize*100/totalSize);

		//edw th mpei kati gia t poodo tou, kati tetio px

		//updateProgress(downloadedSize, totalSize);

		}

		fileOutput.close();
	}
	
	public void f_parse_file(){
		
	}

}
