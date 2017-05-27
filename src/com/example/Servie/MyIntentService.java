package com.example.Servie;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class MyIntentService extends IntentService{
	
	public MyIntentService(){
		super("MyIntentService");//调用父类的有参数构造函数
	}
	
	@Override
	protected void onHandleIntent(Intent intent){
		//打印当前的线程id
		Log.d("MyIntentService", "Thread id is"+Thread.currentThread().getId());
	}
	
	public void onDestory(){
		super.onDestroy();Log.d("MyIntentService", "onDestory executed");
	}

}
