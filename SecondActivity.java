package com.example.emergencycalls;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.media.AudioManager;
import android.os.Bundle;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Button;
import android.widget.Toast;

public class SecondActivity extends BroadcastReceiver{
	private AudioManager am;
	LocationManager lm;
	Button b;
	Geocoder geocoder;
	List<Address> addresses;
	String location;
	Location l;
	String lng;
	String lat;
	int mode=0;


	@Override
	public void onReceive(Context arg0, Intent it) {
		// TODO Auto-generated method stub
		am=(AudioManager) arg0.getSystemService(Context.AUDIO_SERVICE);
		lm=(LocationManager) arg0.getSystemService(Context.LOCATION_SERVICE);
		geocoder = new Geocoder(arg0.getApplicationContext(), Locale.getDefault());
		String message;
		
			Bundle bundle=it.getExtras();
					if(bundle!=null){
				
				Object[] pdusobj=(Object[]) bundle.get("pdus");
				
				for(int i=0;i<pdusobj.length;i++){
					SmsMessage currentMessage=SmsMessage.createFromPdu((byte[]) pdusobj[i]);
					String number=currentMessage.getOriginatingAddress();
					 message=currentMessage.getDisplayMessageBody();
					 
					 
					//Toast.makeText(arg0,"Changing Mode", Toast.LENGTH_SHORT).show();
					
					message=message.trim().toUpperCase();
			

					if(message.equals("SILENT")){
				
						System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
						am.setRingerMode(AudioManager.RINGER_MODE_SILENT);
						Toast.makeText(arg0.getApplicationContext(), "Silent Mode On", Toast.LENGTH_SHORT).show();
						System.out.println("##################################");
						mode=1;
					}
					else if(message.equals("VIBRATE")){
						System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
						am.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
						Toast.makeText(arg0.getApplicationContext(), "Vibrate Mode On", Toast.LENGTH_SHORT).show();
						System.out.println("##################################");
						mode=1;
						
					}else if(message.equals("GENERAL")){
						am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
						Toast.makeText(arg0.getApplicationContext(), "Genral Mode On", Toast.LENGTH_SHORT).show();
						mode=1;
					}else{
						
						//Toast.makeText(arg0, "You Have Given Wrong Msg",Toast.LENGTH_SHORT).show();
					}
					
						
					if(mode==1){
					
				try{	
				 l=getLastBestLocation();
				lng=l.getLongitude()+"";
				lat=l.getLatitude()+"";		
					
					/*if(l!=null){	
						//Toast.makeText(getApplicationContext(), l.toString(),Toast.LENGTH_SHORT).show();
					//	Toast.makeText(arg0.getApplicationContext(), lng+","+lat, Toast.LENGTH_SHORT).show();
					}*/
					try {
						addresses = geocoder.getFromLocation(Double.parseDouble(lat), Double.parseDouble(lng), 1);
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
					//Toast.makeText(arg0.getApplicationContext(), "adresss ~~~~~"+e.toString(), 100).show();
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					//	Toast.makeText(arg0.getApplicationContext(), "adresss  IOException~~~~~"+e.toString(), 100).show();
					} // Here 1 represent max location result to returned, by documents it recommended 1 to 5

					String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
					String city = addresses.get(0).getLocality();
					String state = addresses.get(0).getAdminArea();
					String country = addresses.get(0).getCountryName();
					String postalCode = addresses.get(0).getPostalCode();
					String knownName = addresses.get(0).getFeatureName();
					
					location="Address:"+address+"City :"+city+"State :"+state+"Country :"+country+"Postal Code"+postalCode+"Known Name"+knownName;
					
					//Toast.makeText(arg0.getApplicationContext(), "Address:"+address+"City :"+city+"State :"+state+"Country :"+country+"Postal Code"+postalCode+"Known Name"+knownName, Toast.LENGTH_LONG).show();
					

					SmsManager sms=SmsManager.getDefault();
					sms.sendTextMessage(number, null, location, null, null);	
					
				}catch(Exception e){
					e.printStackTrace();
					Toast.makeText(arg0.getApplicationContext(), "LOcation "+e.toString(), 4000).show();
				}//catch
			}		}
		}
			
	}
			
			public Location getLastBestLocation(){
				Location locationGPS=lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
				Location locationNet=lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
				
				long GPSLocationTime=0;
				
				if(null!=locationGPS)
					GPSLocationTime=locationGPS.getTime();
				
				long NetLocationTime=0;
				if(null!=locationNet)
					NetLocationTime=locationNet.getTime();
				
				if(0< GPSLocationTime - NetLocationTime)
					return locationGPS;
				else
					return locationNet;
	}


					
				
			}
