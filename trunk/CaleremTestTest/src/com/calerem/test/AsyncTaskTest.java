package com.calerem.test;

import com.calerem.ui.ShowContactsHavingNameday;
import android.test.ActivityUnitTestCase;
import com.calerem.ui.ShowContactsHavingNameday.XmlToDoc;
import com.calerem.R;
import android.content.Intent;
import android.widget.TextView;

public class AsyncTaskTest extends ActivityUnitTestCase<ShowContactsHavingNameday> {
	XmlToDoc _atask;
	private Intent _startIntent;
	private  TextView tvNamedaysToday;
	
	public AsyncTaskTest() {
		super(ShowContactsHavingNameday.class);
		
	}
	
	protected void setUp() throws Exception {
	    super.setUp();
	    _startIntent = new Intent(Intent.ACTION_MAIN);
	}
	
	protected void tearDown() throws Exception {
	    super.tearDown();
	}
	
	public final void testExecute() throws Throwable{
		
		startActivity(_startIntent, null, null);
		
	    runTestOnUiThread(new Runnable(){
	    	@Override
	    	public void run() {
	    		tvNamedaysToday = (TextView)getActivity().findViewById(R.id.tvNamedaysToday);
	    		
	    	}
	    });
	    assertNotNull(getActivity());
	    assertNotNull(tvNamedaysToday);
	    
	    
		}
	  
	
}
