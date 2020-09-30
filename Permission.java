package com.example.emergencycalls;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Permission extends Activity {
	Button bt;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_permission);
		bt=(Button) findViewById(R.id.button1);
		bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				try {
			        Intent intent = new Intent();
			        String manufacturer = android.os.Build.MANUFACTURER;
			        if ("xiaomi".equalsIgnoreCase(manufacturer)) {
			            intent.setComponent(new ComponentName("com.miui.securitycenter", "com.miui.permcenter.autostart.AutoStartManagementActivity"));
			        } else if ("oppo".equalsIgnoreCase(manufacturer)) {
			            intent.setComponent(new ComponentName("com.coloros.safecenter", "com.coloros.safecenter.permission.startup.StartupAppListActivity"));
			        } else if ("vivo".equalsIgnoreCase(manufacturer)) {
			            intent.setComponent(new ComponentName("com.vivo.permissionmanager", "com.vivo.permissionmanager.activity.BgStartUpManagerActivity"));
			        } else if ("Letv".equalsIgnoreCase(manufacturer)) {
			            intent.setComponent(new ComponentName("com.letv.android.letvsafe", "com.letv.android.letvsafe.AutobootManageActivity"));
			        } else if ("Honor".equalsIgnoreCase(manufacturer)) {
			            intent.setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.optimize.process.ProtectActivity"));
			        }else{
			        	Toast.makeText(getApplicationContext()," Tere M Nhi Chalega",Toast.LENGTH_SHORT).show();
			        }

			        List<ResolveInfo> list = getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
			        if  (list.size() > 0) {
			            startActivity(intent);
			        }
			    } catch (Exception e) {
			        Log.e("exc" , String.valueOf(e));
			    }
			}
				
		});
		
		
		
	
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_intermediate, menu);
		return true;
	}

}
