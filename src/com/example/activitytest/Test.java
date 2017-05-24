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
				// "�û�����"+userNumberString+"\n���룺"+userPwdString,
				// Toast.LENGTH_LONG).show();
				if (userNumberString.equals("") || userPwdString.equals("")) {
					showDialog("����Ա���ź������Ƿ�Ϊ�գ�\n");

				}

				else {
					Toast.makeText(
							Test.this,"�û�����" + userNumberString + "\n���룺" + userPwdString,
							Toast.LENGTH_LONG).show();
					String urlStr = "http://119.23.217.206:8080/WirelessOrder/Login?username="+ userNumberString + "&password=" + userPwdString;
					try {
						URL url = new URL(urlStr);
						// �������
						HttpURLConnection conn = (HttpURLConnection) url.openConnection();
						if (true) {
							// ���������
							InputStreamReader in = new InputStreamReader(conn.getInputStream());
							BufferedReader bufferReader = new BufferedReader(in);
							String msg="";
							String readLine = null;
							while((readLine=bufferReader.readLine()) !=null){
								msg += readLine;
							}
							in.close();
							conn.disconnect();
							// ����һ�������ֽ���
//							byte[] buffer = new byte[in.available()];
							// ���������ж�ȡ���ݲ���ŵ������ֽ�������
//							in.read(buffer);
							// ���ֽ�ת�����ַ���
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
								showDialog("�û����������������������µ�¼��\n");
								userNumber.setText("");
								 userPwd.setText("");
							}
							// System.out.println(msg);

							Toast.makeText(Test.this, msg, Toast.LENGTH_SHORT)
									.show();

							//in.close();// �ر�������

						} else {

							// ����͹ر�����

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
				.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {

					}
				});
		AlertDialog alert = builder.create();
		alert.show();
	}

}
