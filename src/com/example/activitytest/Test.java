package com.example.activitytest;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View.OnClickListener;
import android.content.DialogInterface;


public class Test extends Activity {
	private Button loginBtn;
	private Button cancleBtn;
	private EditText userNumber;
	private EditText userPwd;

	String userNumberString;
	String userPwdString;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_layout);

		loginBtn = (Button) findViewById(R.id.loginButton);
		cancleBtn = (Button) findViewById(R.id.cancleButton);
		userNumber = (EditText) findViewById(R.id.userNumber);
		userPwd = (EditText) findViewById(R.id.pwd);

		loginBtn.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				userNumberString = userNumber.getText().toString();
				userPwdString = userPwd.getText().toString();

				// Toast.makeText(Login.this,
				// "用户名："+userNumberString+"\n密码："+userPwdString,
				// Toast.LENGTH_LONG).show();
				if (userNumberString.equals("") || userPwdString.equals("")) {
					showDialog("请检查员工号和密码是否为空！\n");

				}

				else {
					Toast.makeText(
							Test.this,"用户名：" + userNumberString + "\n密码：" + userPwdString,
							Toast.LENGTH_LONG).show();
					String urlStr = "http://119.23.217.206:8080/WirelessOrder/Login?username="+ userNumberString + "&password=" + userPwdString;
					try {
						URL url = new URL(urlStr);
						// 获得连接
						HttpURLConnection conn = (HttpURLConnection) url.openConnection();
						if (true) {
							// 获得输入流
							InputStreamReader in = new InputStreamReader(conn.getInputStream());
							BufferedReader bufferReader = new BufferedReader(in);
							String msg="";
							String readLine = null;
							while((readLine=bufferReader.readLine()) !=null){
								msg += readLine;
							}
							in.close();
							conn.disconnect();
							// 创建一个缓冲字节数
//							byte[] buffer = new byte[in.available()];
							// 在输入流中读取数据并存放到缓冲字节数组中
//							in.read(buffer);
							// 将字节转换成字符串
//							String msg = new String(buffer);
							// Toast.makeText(HttpInputStreamActivity.this,msg,
							// Toast.LENGTH_SHORT).show();

							// Toast.makeText(Login.this, msg,
							// Toast.LENGTH_LONG).show();
							if (msg.equals("success")) {
								Intent intent = new Intent(Test.this,
										EightActivity.class);
								startActivity(intent);
							}
							else if(msg.equals("fail")){
								showDialog("用户名或者密码错误，请检查后重新登录！\n");
								userNumber.setText("");
								 userPwd.setText("");
							}
							// System.out.println(msg);

							Toast.makeText(Test.this, msg, Toast.LENGTH_SHORT)
									.show();

							//in.close();// 关闭数据流

						} else {

							// 否则就关闭连接

						}
					} catch (Exception e) {
						Toast.makeText(Test.this, e.getMessage(),
								Toast.LENGTH_SHORT).show();

					}

				}

			}
		});

	}

	private void showDialog(String msg) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(msg).setCancelable(false)
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {

					}
				});
		AlertDialog alert = builder.create();
		alert.show();
	}

}
