package com.example.calerem;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
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



public class c_Facebook_API extends Activity {
	
	private Session.StatusCallback statusCallback = new SessionStatusCallback();
	private Session session;
	private Bundle saved_Instance_State;
	
	public c_Facebook_API(String username, String password)
	{	
		loginActivity();
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		saved_Instance_State = savedInstanceState;
	}
	
	private void loginActivity () {
		Intent intent = new Intent(c_Facebook_API.this, c_Facebook_API.class);
        startActivity(intent);
		
		Settings.addLoggingBehavior(LoggingBehavior.INCLUDE_ACCESS_TOKENS);
		
		session = Session.getActiveSession();
		if(session == null) {
			if(saved_Instance_State != null) {
				session = Session.restoreSession(this, null, statusCallback, saved_Instance_State);
			}
			if (session == null) {
				session = new Session(this);
			}
			Session.setActiveSession(session);
			if (session.getState().equals(SessionState.CREATED_TOKEN_LOADED)) {
				session.openForRead(new Session.OpenRequest(this).setCallback(statusCallback));
			}
		}	
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

		//String fqlQuery = "select uid, name, pic_square, is_app_user from user where uid in (select uid2 from friend where uid1 = me())";
		//Bundle params = new Bundle();
		//params.putString("q", fqlQuery);
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
			
		}
	}
}

