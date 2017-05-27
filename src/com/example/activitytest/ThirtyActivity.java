package com.example.activitytest;

import com.example.Servie.LongRunningService;
import com.example.Servie.MyIntentService;
import com.example.Servie.MyService;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ThirtyActivity extends Activity implements OnClickListener{
	
	private Button startService;
	
	private Button stopService;
	
	private Button bindService;
	
	private Button unbindService;
	
	private Button startIntentService;
	
	private Button goto_FourteenActivity;
	
	private Button goto_SixteenActivity;
	
	private MyService.DownloadBinder downloadBinder;
	
	private Button goto_FifteenActivity;
	
	private ServiceConnection connection = new ServiceConnection(){
		
		@Override
		public void onServiceDisconnected(ComponentName name){
			
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service){
			downloadBinder = (MyService.DownloadBinder) service;
			downloadBinder.startDownload();
			downloadBinder.getProgress();
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.thirty_layout);
		startService = (Button) findViewById(R.id.start_service);
		stopService = (Button) findViewById(R.id.stop_service);
		startIntentService = (Button) findViewById(R.id.start_intent_service);
		goto_FourteenActivity = (Button) findViewById(R.id.Goto_FourteenActivity);
		goto_FifteenActivity = (Button) findViewById(R.id.Goto_FifteenActivity);
		goto_SixteenActivity = (Button) findViewById(R.id.Goto_SixteenActivity);
		Intent intent = new Intent(this,LongRunningService.class);
		startService(intent);
		startService.setOnClickListener(this);
		stopService.setOnClickListener(this);
		startIntentService.setOnClickListener(this);
		goto_FourteenActivity.setOnClickListener(this);
		goto_FifteenActivity.setOnClickListener(this);
		goto_SixteenActivity.setOnClickListener(this);
 	}
	
	@Override
	public void onClick(View v){
		switch(v.getId()){
		case R.id.start_service:
			Intent startIntent = new Intent(this,MyService.class);
			startService(startIntent);
			break;
		case R.id.stop_service:
			Intent stopIntent = new Intent(this,MyService.class);
			stopService(stopIntent);
			break;
		case R.id.bind_service:
			Intent bindIntent = new Intent(this,MyService.class);
			bindService(bindIntent, connection, BIND_AUTO_CREATE);
			break;
		case R.id.unbind_service:
			unbindService(connection);
			break;
		case R.id.start_intent_service:
			//打印主线程的id
			Log.d("ThirtyActivity", "Thread id is"+Thread.currentThread().getId());
			Intent intentService = new Intent(this,MyIntentService.class);
			startService(intentService);
			break;
		case R.id.Goto_FourteenActivity:
			Intent goto_Fourteen = new Intent(ThirtyActivity.this,FourteenActivity.class);
			startActivity(goto_Fourteen);
			finish();
			break;
		case R.id.Goto_FifteenActivity:
			Intent goto_FifteenActivity = new Intent(ThirtyActivity.this,FifteenActivity.class);
			startActivity(goto_FifteenActivity);
			finish();
		case R.id.Goto_SixteenActivity:
			Intent goto_SixteenActivity = new Intent(ThirtyActivity.this,SixteenActivity.class);
			startActivity(goto_SixteenActivity);
			finish();
		default:
			break;
		}
	}

}
