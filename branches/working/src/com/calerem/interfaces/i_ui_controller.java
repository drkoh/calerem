package com.calerem.interfaces;

import android.content.Intent;

public interface i_ui_controller {

	public void f_new_SendEmail(Integer contact_id, String subject, String text);

	public void f_new_ViewEvent(int v_event_id);

	public void f_new_NewEvent();

	public void onActivityResult(int requestCode, int resultCode, Intent data);

}