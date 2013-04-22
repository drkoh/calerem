package com.calerem.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import com.calerem.ui.SendEmail;
import android.view.View;
import android.app.Activity;

public class TestSendEmail extends ActivityInstrumentationTestCase2<SendEmail> {
	
	private Button button;
	private String email, message, subject, bText;
	private SendEmail mActivity;
	private TextView tEmail, tMessage, tSubject;
	private EditText mEmail, mMessage, mSubject;
	private Activity nextActivity;
	
	public TestSendEmail() {
		super(SendEmail.class);	
	}
	
	protected void setUp() throws Exception {
		super.setUp();
		mActivity = this.getActivity();
		email = mActivity.getString(com.calerem.R.string.SendEmail_Email_Address);
		message = mActivity.getString(com.calerem.R.string.SendEmail_Text_Message);
		subject = mActivity.getString(com.calerem.R.string.SendEmail_Subject);
		mEmail = (EditText) mActivity.findViewById(com.calerem.R.id.etEmail);
		mMessage = (EditText) mActivity.findViewById(com.calerem.R.id.etMessage);
		mSubject = (EditText) mActivity.findViewById(com.calerem.R.id.etSubject);
		bText = mActivity.getString(com.calerem.R.string.SendEmail_Send_Email);
	/*	
	 *  tEmail = (TextView) mActivity.findViewById(R.id.);
	 *	tMessage = (TextView) mActivity.findViewById(R.id.);
	 *	tSubject = (TextView) mActivity.findViewById(R.id.);
	*/	
		
	}
	
	public void testPreconditions() {
	/*	
	 *  assertNotNull(tEmail);
     * 	assertNotNull(tMessage);
	 *	assertNotNull(tSubject); 
	 */ 
		assertNotNull(mActivity);
		assertNotNull(mEmail);
		assertNotNull(mMessage);
		assertNotNull(mSubject);
		assertNotNull(button);
	}
	
	
	public void testInitialization(){
		assertEquals(mEmail.getText(),"");   
		assertEquals(mMessage.getText(),"");
		assertEquals(mSubject.getText(),"");
		assertEquals(button.getText(), bText);
	/*
	 * 	assertEquals(email,(String) tEmail.getText());
	 *	assertEquals(message,(String) tMessage.getText());
	 *	assertEquals(subject,(String) tSubject.getText());
	*/
	}

	public void testButtonNextActivity(){
		mActivity.runOnUiThread(
				new Runnable() {
					public void run() {
						mEmail.setText("email@email.com");
						mMessage.setText("blah blah blah");
						mSubject.setText("blah");
	    				button.performClick();
	                    nextActivity =  getActivity();
	                    }
	            }
	    );
//      assertEquals(nextActivity, );
	}
	
	public void testButtonActivityFinish(){
		mActivity.runOnUiThread(
				new Runnable() {
					public void run() {
	    				button.performClick();
	    			}
				}
		);
		assertTrue(mActivity.isFinishing());
	}
	

	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
