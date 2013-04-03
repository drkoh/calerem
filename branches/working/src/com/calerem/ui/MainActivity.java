package com.calerem.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.calerem.R;
import com.calerem.interfaces.i_MainActivity;

public class MainActivity extends Activity implements i_MainActivity {
	
	Integer check = 0;
	Button bt1;
	Button bt2;
	Button bt3;
	
	//hack
    /* (non-Javadoc)
	 * @see com.calerem.ui.i_MainActivity#onActivityResult(int, int, android.content.Intent)
	 */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
     new ui_controller(this).onActivityResult(requestCode, resultCode, data);
    }

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main_dummy);
		
		
		bt1 = (Button) findViewById (R.id.bt1);
		bt2 = (Button) findViewById (R.id.bt2);
		bt3 = (Button) findViewById (R.id.bt3);
	
		bt1.setOnClickListener(new OnClickListener() {
	       
	        
	        public void onClick(View v) {
	        	check = 1;
	        	checkVar();
	        	
	        }
	    });
		bt2.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) 
			{
				check = 2;
				checkVar();
			}
		});
		bt3.setOnClickListener(new OnClickListener(){
			
		

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				check = 3;
				checkVar();
			}
			});
		
	}

	private void checkVar() {
		// TODO Auto-generated method stub
		if(check==1)
		{
			new ui_controller(this).f_new_NewEvent();
		}
		else if(check==2)
		{
			new ui_controller(this).f_new_ViewEvent(1);
			
		}
		else if(check==3)
		{
			new ui_controller(this).f_new_SendEmail((Integer)null,"","");
		}
	}

	
	
   
    }


