package com.example.activitytest;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class FourteenActivity extends Activity{
	
	private WebView webView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fourteen_layout);
		webView = (WebView) findViewById(R.id.web_view);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.setWebViewClient(new WebViewClient(){
			@Override
			public boolean shouldOverrideUrlLoading(WebView view,String url){
				view.loadUrl(url);//���ݴ���Ĳ�����ȥ�����µ���ҳ
				return true;//��ʾ��ǰ��WebView���Դ�����µ���ҳ�����󣬲��ý���ϵͳ�����
			}
		});
		webView.loadUrl("http://www.baidu.com");
	}

}
