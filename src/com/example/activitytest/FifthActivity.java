package com.example.activitytest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FifthActivity extends Activity{
	
	private String[] data = {"Apple","Banana","Orange","Watermalon","Pear","Grape","Pineapple","Strawberry","Cherry","Mango","cup"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fifth_layout);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				FifthActivity.this,android.R.layout.simple_list_item_1,data);
		ListView listview = (ListView) findViewById(R.id.list_view);
		listview.setAdapter(adapter);
	}

}
