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
				view.loadUrl(url);//根据传入的参数再去加载新的网页
				return true;//表示当前的WebView可以处理打开新的网页的请求，不用借助系统浏览器
			}
		});
		webView.loadUrl("http://www.baidu.com");
	}

}
