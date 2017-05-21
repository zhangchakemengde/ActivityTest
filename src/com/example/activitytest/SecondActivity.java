package com.example.activitytest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SecondActivity extends Activity{
	private EditText accountEdit;
	private EditText passwordEdit;
	private Button login;
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.second_layout);
//		Intent intent = getIntent();
//		String data = intent.getStringExtra("extra_data");
//		Log.d("SecondActivity", data);
		accountEdit = (EditText) findViewById(R.id.account);
		passwordEdit = (EditText) findViewById(R.id.password);
		login = (Button) findViewById(R.id.login);
		login.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				String account = accountEdit.getText().toString();
				String password = passwordEdit.getText().toString();
				//如果账号是admin且密码是123456，就认为登陆成功
				if(account.equals("admin") && password.equals("123456")){
					Intent intent = new Intent(SecondActivity.this,SevenActivity.class);
					startActivity(intent);
					finish();
				}else{
					Toast.makeText(SecondActivity.this, "account or password is invalid", Toast.LENGTH_SHORT).show();
				}
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
