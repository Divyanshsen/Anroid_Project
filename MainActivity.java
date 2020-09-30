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
import android.widget.Toast;

public class MainActivity extends Activity {
Button bt,bt1,per;
SQLiteDatabase db;
ArrayList<String> al;
ArrayAdapter<String> ad;
//static String phone[] ;
int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt=(Button) findViewById(R.id.button1);
        bt1=(Button) findViewById(R.id.button2);
        per=(Button) findViewById(R.id.button3);
        db=openOrCreateDatabase("impcontacts", MODE_PRIVATE, null);
        
        bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent it=new Intent(MainActivity.this,ContactsActivity.class);
				startActivity(it);
			}
		});
        bt1.setOnClickListener(new OnClickListener() {
			String str1,str2;
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				try{
				Intent it=new Intent(MainActivity.this,SavedContacts.class);
				Cursor c=db.rawQuery("select * from person",null);
				c.moveToFirst();
				al=new ArrayList<String>();
				if(c!=null)
				{
					do {
						i=1;
						int a=c.getColumnIndex("username");
						int b=c.getColumnIndex("mobileno");
						String s11=c.getString(a);
						String s22=c.getString(b);
						str1=s11+"\n"+s22;
						al.add(str1);
						
					}while(c.moveToNext());
					it.putStringArrayListExtra("key", al);
				
				startActivity(it);
				}
				
			}catch(Exception e){
				Toast.makeText(getApplicationContext(),"List Is Empty", 300).show();
			}
			}
		});
        
        
        per.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			

				Intent obj=new Intent(MainActivity.this,Permission.class);
					startActivity(obj);
				
				
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
}
