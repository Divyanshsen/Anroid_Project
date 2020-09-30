package com.example.emergencycalls;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class EditContact extends Activity {
	static String y;
Button call,msg,send;
TextView tv1,tv2,tv3;
String s2;
Spinner spinner;
String data[]={"General","Silent","Vibrate"};
View vw,vw1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_contact);
		call=(Button) findViewById(R.id.button1);
		msg=(Button) findViewById(R.id.button2);
		tv1=(TextView) findViewById(R.id.textView1);
		tv2=(TextView) findViewById(R.id.textView2);
		tv3=(TextView) findViewById(R.id.textView3);
		send=(Button) findViewById(R.id.button3);
		vw=(View)findViewById(R.id.spinner1);
		vw1=(View)findViewById(R.id.button3);
		spinner=(Spinner) findViewById(R.id.spinner1);
		ArrayAdapter<String>arrayAdapter=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line,data);
		spinner.setAdapter(arrayAdapter);
		vw.setVisibility(View.GONE);
		vw1.setVisibility(View.GONE);

		
		Intent it=getIntent();
		String s1=it.getStringExtra("key1");
		//String s2=it.getStringExtra("key2");
		tv1.setText((CharSequence) s1);
		//tv2.setText((CharSequence)s2);
		
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		 s1=tv1.getText().toString();
		int a=s1.length();
		s2=s1.substring(a-10);
	
		
	    call.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				/*String s1=tv1.getText().toString();
				int a=s1.length();
				s2=s1.substring(a-10);
				Toast.makeText(getApplicationContext(), "before: "+s2, 100).;
			
			*/Intent it=new  Intent(Intent.ACTION_CALL,Uri.parse("tel:"+s2));
			startActivity(it);
		
			}
		});
	    send.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				//Toast.makeText(getApplicationContext(),"Message:"+y, Toast.LENGTH_LONG).show();
				
				try {
					
					//Toast.makeText(getApplicationContext(), "STRING IS::"+s2, Toast.LENGTH_LONG).show();
					
					SmsManager sm=SmsManager.getDefault();
					sm.sendTextMessage(s2,null,y,null, null);	
					Toast.makeText(getApplicationContext(), "Message Sent",Toast.LENGTH_LONG).show();
					
				} catch (Exception e) {
					// TODO: handle exception
					Toast.makeText(getApplicationContext(), " "+e.toString(), 100).show();
	
				}
				
				
			}
		});
	    msg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				vw.setVisibility(View.VISIBLE);
				vw1.setVisibility(View.VISIBLE);
				
				
			}
		});
	    
	    spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
			
				y=data[arg2]+"";
				Toast.makeText(getApplicationContext(),data[arg2],100).show();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_edit_contact, menu);
		return true;
	}

}
