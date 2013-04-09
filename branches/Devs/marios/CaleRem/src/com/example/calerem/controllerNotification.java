package com.example.test;



import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;
import android.widget.Spinner;

public class controllerNotification extends Activity {
	Spinner spn_date_format;
	Spinner spn_notification_sound;
	Spinner spn_language;
	Spinner spn_skin;
	Spinner spn_eortologio_xml;

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.configurationlayout);
		 
		spn_date_format = (Spinner) findViewById(R.id.spnDateFormat);
		spn_notification_sound = (Spinner) findViewById(R.id.spnNotificationsSound);
		spn_language = (Spinner) findViewById(R.id.spnLanguage);
		spn_skin = (Spinner) findViewById(R.id.spnSkin);
		spn_eortologio_xml = (Spinner) findViewById(R.id.spnEortologioXml);
		System.out.println("gkukkgkgkgh");
	
		

		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	public void clkSave(View v){
		
		

		
		
		
	}
	
	
	
	public void clkReset(View v){

		new AlertDialog.Builder( this )
			.setTitle( "Επιβεβαίωση ενημέρωσης configuration" )
			.setMessage( "Πρόκειτε να αλλάξετε το cinfiguration σας. Είστε βέβαιος ότι θέλετε να προχωρησετε?")

			.setPositiveButton( "Ναι", new DialogInterface.OnClickListener() {
 			public void onClick(DialogInterface dialog, int which) {
 				
 
 				}
			})
 		.setNegativeButton( "Οχι", new DialogInterface.OnClickListener() {
 		public void onClick(DialogInterface dialog, int which) {
 			
 			}
 		} )
 	.show();
		
		
		
	}
	

}
