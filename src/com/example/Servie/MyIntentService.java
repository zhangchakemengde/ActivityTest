package com.example.Servie;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class MyIntentService extends IntentService{
	
	public MyIntentService(){
		super("MyIntentService");//���ø�����в������캯��
	}
	
	@Override
	protected void onHandleIntent(Intent intent){
		//��ӡ��ǰ���߳�id
		Log.d("MyIntentService", "Thread id is"+Thread.currentThread().getId());
	}
	
	public void onDestory(){
		super.onDestroy();Log.d("MyIntentService", "onDestory executed");
	}

}
