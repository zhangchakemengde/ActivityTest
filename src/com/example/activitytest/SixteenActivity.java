package com.example.activitytest;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class SixteenActivity extends Activity{
	
	private TextView positionTextView;
	
	private LocationManager locationManager;
	
	private String provider;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sixteen_layout);
		positionTextView = (TextView) findViewById(R.id.position_text_view);
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		//��ȡ���п��õ�λ���ṩ��
		List<String> providerList = locationManager.getProviders(true);
		if(providerList.contains(LocationManager.GPS_PROVIDER)){
			provider = LocationManager.GPS_PROVIDER;
		}else if(providerList.contains(LocationManager.NETWORK_PROVIDER)){
			provider = LocationManager.NETWORK_PROVIDER;
		}else{
			//��û�п��õ�λ���ṩ��ʱ������Toast��ʾ�û�
			Toast.makeText(this, "No location provier to use", Toast.LENGTH_SHORT).show();
			return;
		}
		Location location = locationManager.getLastKnownLocation(provider);
		if(location != null){
			//��ʾ��ǰ�豸��λ����Ϣ
			showLocation(location);
		}
		locationManager.requestLocationUpdates(provider, 5000, 1, locationListener);
		
	}
	
	protected void onDestory(){
		super.onDestroy();
		if(locationManager != null){
			//�رճ���ʱ���������Ƴ�
			locationManager.removeUpdates(locationListener);
		}
	}
	
	LocationListener locationListener = new LocationListener(){
		@Override
		public void onStatusChanged(String provier,int status,Bundle extras){
			
		}
		
		@Override
		public void onProviderEnabled(String provider){
			
		}
		@Override
		public void onProviderDisabled(String provider){
			
		}
		@Override
		public void onLocationChanged(Location location){
			//���µ�ǰ�豸��λ����Ϣ
			showLocation(location);
		}
	};
	
	private void showLocation(Location location){
		String currentPosition = "latitude is "+location.getLatitude()+"\n"+"longitude is "+location.getLongitude();
		Log.d("SixteenActivity", "latitude is "+location.getLatitude());
		Log.d("SixteenActivity", "logitude is "+location.getLongitude());
		positionTextView.setText(currentPosition);
	}

}
