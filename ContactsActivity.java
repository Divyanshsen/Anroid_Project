package com.example.emergencycalls;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ContactsActivity extends Activity {
EditText name,mo;
Button save,back;
SQLiteDatabase db;
ArrayList<String> al;
ArrayAdapter<String> ad;
//String s1,s2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contacts);
		name=(EditText) findViewById(R.id.editText1);
		mo=(EditText) findViewById(R.id.editText2);
		save=(Button) findViewById(R.id.button1);
	    back=(Button) findViewById(R.id.button2);
	    //show=(Button)findViewById(R.id.button3);
	    db=openOrCreateDatabase("impcontacts", MODE_PRIVATE, null);
		
		save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String s1=name.getText().toString();
				String s2=mo.getText().toString();
				if(s2.length()==10){
					
				
				if(s1.equals("")||s2.equals("")){
					//String s1=name.getText().toString();
				 Toast.makeText(getApplicationContext(), "please fill the details", Toast.LENGTH_SHORT).show();
				}
				else 
				{
					db.execSQL("create table if not exists person(username varchar,mobileno varchar)");
					db.execSQL("insert into person values('"+s1+"','"+s2+"')");
					
					Toast.makeText(getApplicationContext(),"successfully saved",Toast.LENGTH_SHORT).show();
					finish();
					
				}	
				}	
				else
				{
					Toast.makeText(getApplicationContext(), "Enter a valid no.", Toast.LENGTH_SHORT).show();
				}
				
			}
		});
		
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent it=new Intent(ContactsActivity.this,MainActivity.class);
				startActivity(it);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_contacts, menu);
		return true;
	}

}
