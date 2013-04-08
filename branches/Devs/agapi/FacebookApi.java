package com.example.calerem;

import java.util.List;

import android.app.Activity;

import android.os.Bundle;
import android.util.Log;

import com.facebook.LoggingBehavior;
import com.facebook.Request;
import com.facebook.Request.GraphUserListCallback;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.Settings;
import com.facebook.model.GraphUser;



public class FacebookApi extends Activity {
	
	static final String applicationId = "488108911245147";
	private Session.StatusCallback statusCallback = new SessionStatusCallback();
	private Session session;

	
	public FacebookApi(String username, String password)
	{	
		loginActivity();
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	}
	
	private void loginActivity () {
		
		session = createSession();
		
		Settings.addLoggingBehavior(LoggingBehavior.INCLUDE_ACCESS_TOKENS);
		
		if (session.getState().equals(SessionState.CREATED_TOKEN_LOADED)) {
			session.openForRead(new Session.OpenRequest(this).setCallback(statusCallback));
		}
		//if user has successfully logged in do :
		//getFriends();
		//else to check if user has successfully logged in do : 
		getUserData(session);
	}
	
	private void getUserData(final Session session) {				  
		 		Request request = Request.newMeRequest(session, new Request.GraphUserCallback() {
         					
		 		@Override
		 		public void onCompleted(GraphUser user, Response response) {
		 			if (user!= null && session == Session.getActiveSession()){
		 				getFriends();
		 			}
		  			if(response.getError() != null) {
		 			
		 			}
		 		}
		 	 });
		 request.executeAsync();
	}
	
	private void getFriends(){

		Session activeSession = Session.getActiveSession();
		if(activeSession.getState().isOpened()) {
			Request friendRequest = Request.newMyFriendsRequest(activeSession,new GraphUserListCallback() {
				
				@Override
				public void onCompleted(List<GraphUser> users, Response response) {
					Log.i("INFO",response.toString());
				}
			});
			Bundle params = new Bundle();
			params.putString("fields", "id,name,friends");
			friendRequest.setParameters(params);
			friendRequest.executeAsync();
		}
	}
	
	private class SessionStatusCallback implements Session.StatusCallback {
		@Override
		public void call(Session session, SessionState state, Exception exception) {
			//testing if the session is opened if not creates her
			if (exception != null) {
                session = createSession();
            }
		}
	}
	
	private Session createSession() {
		Session activeSession = Session.getActiveSession();
        if (activeSession == null || activeSession.getState().isClosed()) {
            activeSession = new Session.Builder(this).setApplicationId(applicationId).build();
            Session.setActiveSession(activeSession);
        }
        return activeSession;
	}
}

