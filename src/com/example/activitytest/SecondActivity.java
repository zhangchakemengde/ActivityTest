package com.example.activitytest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class SecondActivity extends Activity{
	private EditText accountEdit;
	private EditText passwordEdit;
	private Button login;
	private CheckBox rememberPass;
	private SharedPreferences pref;
	private SharedPreferences.Editor editor;
	String account;
	String password;
	private Button Goto_TenthActivity;
	private Button Goto_ElevenActivity;
	private Button Goto_ThirtyActivity;
	Handler hmessage=new Handler()
	{
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch(msg.what){
				case	1:
					showDialog("«ÎºÏ≤È’À∫≈√‹¬Î «∑ÒŒ™ø’£°");
					break;
				case	2:
					showDialog("’À∫≈√‹¬Î¥ÌŒÛ£°");
					accountEdit.setText("");
					passwordEdit.setText("");
					break;
				default:
					break;	
			}
			super.handleMessage(msg);
		}
	};
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.second_layout);
		pref = PreferenceManager.getDefaultSharedPreferences(this);
		accountEdit = (EditText) findViewById(R.id.account);
		passwordEdit = (EditText) findViewById(R.id.password);
		rememberPass = (CheckBox) findViewById(R.id.remember_pass);
		login = (Button) findViewById(R.id.login);
		boolean isRemember = pref.getBoolean("remember_password", false);
		if(isRemember){
			String account = pref.getString("account", "");
			String password = pref.getString("password", "");
			accountEdit.setText(account);
			passwordEdit.setText(password);
			rememberPass.setChecked(true);
		}
		login.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				account = accountEdit.getText().toString();
				password = passwordEdit.getText().toString();
				new Thread(){
					@Override
					public void run(){
						Looper.prepare();
						if(account.equals("")||password.equals("")){
							hmessage.sendEmptyMessage(1);
						}else{
							String urlStr = "http://119.23.217.206:8080/WirelessOrder/Login?username="+ account + "&password=" + password;
							try {
								URL url = new URL(urlStr);
								HttpURLConnection conn = (HttpURLConnection) url.openConnection();
								if(true){
									InputStreamReader in = new InputStreamReader(conn.getInputStream());
									BufferedReader bufferReader = new BufferedReader(in);
									String msg = "";
									String readLine = null;
									while((readLine=bufferReader.readLine()) != null){
										msg += readLine;
									}
									in.close();
									conn.disconnect();
									if(msg.equals("success")){
										editor = pref.edit();
										if(rememberPass.isChecked()){//ºÏ≤È∏¥—°øÚ «∑Ò—°÷–
											editor.putBoolean("remember_password", true);
											editor.putString("account", account);
											editor.putString("password", password);
										}else{
											editor.clear();
										}
										editor.commit();
										Intent intent = new Intent(SecondActivity.this,EightActivity.class);
										startActivity(intent);
										finish();
									}else if(msg.equals("fail")){
										hmessage.sendEmptyMessage(2);
										}
								}
							} catch (MalformedURLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								System.out.println(e.toString());
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								System.out.println(e.toString());
							}
						}
						
					}
				}.start();
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
		Button button4 = (Button)findViewById(R.id.button_5);
		button4.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				Intent intent3 = new Intent(SecondActivity.this,EightActivity.class);
				startActivity(intent3);
				finish();
			}
		});
		Goto_TenthActivity = (Button) findViewById(R.id.button_12);
		Goto_TenthActivity.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				Intent intent = new Intent(SecondActivity.this,TenthActivity.class);
				startActivity(intent);
				finish();
			}
		});
		Goto_ElevenActivity = (Button) findViewById(R.id.button_13);
		Goto_ElevenActivity.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				Intent intent = new Intent(SecondActivity.this,TwelveActivity.class);
				startActivity(intent);
				finish();
			}
		});
		Goto_ThirtyActivity = (Button) findViewById(R.id.button_14);
		Goto_ThirtyActivity.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				Intent intent = new Intent(SecondActivity.this,ThirtyActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}
	private void showDialog(String msg) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(msg).setCancelable(false)
				.setPositiveButton("»∑∂®", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
					}
				});
		AlertDialog alert = builder.create();
		alert.show();
	}
	
}
