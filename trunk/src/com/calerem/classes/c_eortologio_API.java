package com.calerem.classes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.os.Environment;

public class c_eortologio_API extends Activity{
	
	public void f_get_file(String v_URL) throws Exception
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
	
	public void f_parse_file(){
		
	}

}
