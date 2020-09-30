package com.example.emergencycalls;

import java.util.ArrayList;

import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class SavedContacts extends Activity {
ListView lv;
ArrayList<String> al;
ArrayAdapter<String> ad;
SQLiteDatabase db;
String val,val1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_saved_contacts);
		lv = (ListView) findViewById(R.id.listView1);
		Intent obj=getIntent();
		al=obj.getStringArrayListExtra("key");
		if(al!=null){
		ad=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,al);
		lv.setAdapter(ad);}
		db=openOrCreateDatabase("impcontacts", MODE_PRIVATE, null);
		registerForContextMenu(lv);
				lv.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				//Intent it=getIntent();
				Cursor c=db.rawQuery("select * from person", null);
				c.moveToFirst();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}

		});
				
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				
			String s=(String) arg0.getItemAtPosition(arg2);
				// TODO Auto-generated method stub
          //  String s=MainActivity.phone[arg2];
			 Intent it=new Intent(SavedContacts.this,EditContact.class);
             
				it.putExtra("key1", s);
				//it.putExtra("key2", s);
				
                startActivity(it);
             
			}
		} )  ;
lv.setOnItemLongClickListener(new OnItemLongClickListener() {

	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		val=(String) arg0.getItemAtPosition(arg2);
		int x=val.length();
		val1=val.substring(0,x-11);
Toast.makeText(getApplicationContext(), ""+val1, Toast.LENGTH_LONG).show();		
		
		return false;
	}
});
	}
	 	
	 public void onCreateContextMenu(ContextMenu menu,View v,ContextMenuInfo menuInfo){
		 super.onCreateContextMenu(menu, v, menuInfo);
		 @SuppressWarnings("unused")
		 MenuInflater inflater=getMenuInflater();
		 getMenuInflater().inflate(R.menu.main_context_menu, menu);
		  }
		  @Override
		 	public boolean onContextItemSelected(MenuItem item) {
		 		// TODO Auto-generated method stub
		 	AdapterContextMenuInfo info=(AdapterContextMenuInfo) item.getMenuInfo();	
		 	switch(item.getItemId()){
		 	case R.id.delete_id:
		 		String str =String.valueOf(R.id.delete_id);
				
		 		try {
		 			db.execSQL("DELETE FROM person WHERE username='"+val1+"'");
		 			Toast.makeText(getApplicationContext(),"Deleted", Toast.LENGTH_LONG).show();

				} catch (Exception e) {
					// TODO: handle exception
					Toast.makeText(getApplicationContext(),"dEL2"+e.toString(), Toast.LENGTH_LONG).show();
				}
		 		
		 		al.remove(info.position);
		 	ad.notifyDataSetChanged();
		 	break;
		 		default:
		 		return super.onContextItemSelected(item);
		          
		 	} 
		 	return false;
		 	}

	
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_saved_contacts, menu);
		return true;
	}
	
	

}
