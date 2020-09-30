package com.example.emergencycalls;

import android.media.AudioManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MessageActivity extends BroadcastReceiver{
	private AudioManager am;
	Button send;
	EditText num,msg;
	@Override
	public void onReceive(Context arg0, Intent it) {
		// TODO Auto-generated method stub
		am=(AudioManager) arg0.getSystemService(Context.AUDIO_SERVICE);
		String message;
		Bundle bundle=it.getExtras();
			
			
					if(bundle!=null){
				
				Object[] pdusobj=(Object[]) bundle.get("pdus");
				
				for(int i=0;i<pdusobj.length;i++){
					SmsMessage currentMessage=SmsMessage.createFromPdu((byte[]) pdusobj[i]);
					 message=currentMessage.getDisplayMessageBody();
					Toast.makeText(arg0,"Changing Mode", Toast.LENGTH_SHORT).show();
					
					message=message.trim().toUpperCase();
			
	

					if(message.equals("SILENT")){
						System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
						am.setRingerMode(AudioManager.RINGER_MODE_SILENT);
						Toast.makeText(arg0.getApplicationContext(), "Silent Mode On", Toast.LENGTH_SHORT).show();
						System.out.println("##################################");
					}
					else if(message.equals("VIBRATE")){
						System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
						am.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
						Toast.makeText(arg0.getApplicationContext(), "Vibrate Mode On", Toast.LENGTH_SHORT).show();
						System.out.println("##################################");
						
					}else if(message.equals("GENERAL")){
						am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
						Toast.makeText(arg0.getApplicationContext(), "Genral Mode On", Toast.LENGTH_SHORT).show();
						
					}else{
						
						Toast.makeText(arg0, "You Have Given Wrong Msg",Toast.LENGTH_SHORT).show();
					}

					
				}
			}

		
	}

	

}
