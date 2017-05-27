package com.example.Http;

public interface HttpCallbackListener {
	void onFinish(String response);
	void onError(Exception e);

}
