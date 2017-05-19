package com.example.activitytest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class SecondActivity extends Activity{
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.second_layout);
//		Intent intent = getIntent();
//		String data = intent.getStringExtra("extra_data");
//		Log.d("SecondActivity", data);
		Button button2 = (Button)findViewById(R.id.login);
		button2.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				Intent intent = new Intent();
				intent.putExtra("data_return", "Hello FirstActivity");
				setResult(RESULT_OK,intent);
				finish();
			}
		});
		Button button3 = (Button)findViewById(R.id.loginToThird);
		button3.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				Intent intent2 = new Intent(SecondActivity.this,ThirdActivity.class);
				startActivity(intent2);
				finish();
			}
		});
	}
	
}
