package com.example.activitytest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SevenActivity extends BaseActivity{
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.seven_layout);
		Button forceOffline = (Button) findViewById(R.id.force_offline);
		forceOffline.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				Intent intent = new Intent("com.example.broadcastbestpratice.FORCE_OFFLINE");
				sendBroadcast(intent);
			}
		});
	}

}
