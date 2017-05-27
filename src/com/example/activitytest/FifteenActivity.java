package com.example.activitytest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class FifteenActivity extends Activity implements OnClickListener{
	public static final int SHOW_RESPONSE = 0;
	
	private Button sendRequest;
	private TextView responseText;
	private Button sendPost;
	private Handler handler = new Handler(){
		public void handleMessage(Message msg){
			switch(msg.what){
			case SHOW_RESPONSE:
				String response = (String) msg.obj;
				//在这里进行UI操作，将结果显示到界面上
				responseText.setText(response);
			}
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fifteen_layout);
		sendRequest = (Button) findViewById(R.id.send_request);
		responseText = (TextView) findViewById(R.id.response);
		sendPost = (Button) findViewById(R.id.send_post);
		sendRequest.setOnClickListener(this);
		sendPost.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v){
		if(v.getId() == R.id.send_request){
			sendRequestWithHttpURLConnection();
		}else if(v.getId() == R.id.send_post){
			sendPostWithHttpClient();
		}
	}
	private void sendRequestWithHttpURLConnection(){
		//开启线程开发起网络请求
		new Thread(new Runnable(){
			@Override
			public void run(){
				
					HttpURLConnection connection = null;
				try {
					URL url = new URL("http://www.baidu.com");
					connection = (HttpURLConnection) url.openConnection();
					connection.setRequestMethod("GET");
					connection.setConnectTimeout(8000);
					connection.setReadTimeout(8000);
					InputStream in = connection.getInputStream();
					//线面对获取到的输入流进行读取
					BufferedReader reader = new BufferedReader(new InputStreamReader(in));
					StringBuilder response = new StringBuilder();
					String line;
					while((line = reader.readLine()) != null){
						response.append(line);
					}
					Message message = new Message();
					message.what = SHOW_RESPONSE;
					//将服务器返回的结果存放到Message中
					message.obj = response.toString();
					handler.sendMessage(message);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally{
					if(connection != null){
						connection.disconnect();
					}
				}
			}
		}).start();
	}
	
	private void sendPostWithHttpClient(){
		new Thread(new Runnable(){
			@Override
			public void run(){
				try {
					HttpClient httpClient = new DefaultHttpClient();
					HttpGet httpGet = new HttpGet("http://119.23.217.206:8080/WirelessOrder/get_data.xml");
					HttpResponse httpResponse = httpClient.execute(httpGet);
					if(httpResponse.getStatusLine().getStatusCode() == 200){
						//请求和响应都成功了
						HttpEntity entity = httpResponse.getEntity();
						String response = EntityUtils.toString(entity,"utf-8");
						
						parseXMLWithPull(response);
//						Message message = new Message();
//						message.what = SHOW_RESPONSE;
//						//强服务器返回的结果存放到Message中
//						message.obj = response.toString();
//						handler.sendMessage(message);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		}).start();
	}
	private void parseXMLWithPull(String xmlData){
		try {
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			XmlPullParser xmlPullParser = factory.newPullParser();
			xmlPullParser.setInput(new StringReader(xmlData));
			int eventType = xmlPullParser.getEventType();
			String id = "";
			String name = "";
			String version = "";
			while(eventType != XmlPullParser.END_DOCUMENT){
				String nodeName = xmlPullParser.getName();
				switch (eventType){
				//开始解析某个结点
				case XmlPullParser.START_TAG: {
					if("id".equals(nodeName)){
						id = xmlPullParser.nextText();
					}else if("name".equals(nodeName)){
						name = xmlPullParser.nextText();
					}else if("version".equals(nodeName)){
						version = xmlPullParser.nextText();
					}
					break;
				}
				//完成解析某个结点
				case XmlPullParser.END_TAG:{
					if("app".equals(nodeName)){
						Log.d("FifteenActivity", "id is "+id);
						Log.d("FifteenActivity", "name is "+name);
						Log.d("FifteenActivity", "version is "+version);
					}
					break;
				}
				default:
					break;
				}
				eventType = xmlPullParser.next();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
