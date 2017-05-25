package com.example.activitytest;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TenthActivity extends Activity implements OnClickListener{
	private Button play;
	private Button pause;
	private Button stop;
	private MediaPlayer mediaPlayer = new MediaPlayer();
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tenth_layout);
		play = (Button) findViewById(R.id.play);
		pause = (Button) findViewById(R.id.pause);
		stop = (Button) findViewById(R.id.stop);
		play.setOnClickListener(this);
		pause.setOnClickListener(this);
		stop.setOnClickListener(this);
		initMediaPlayer();//初始化MediaPlayer
	}
	
	private void initMediaPlayer(){
		try {
			File file = new File(Environment.getExternalStorageDirectory(),"music.mp3");
			mediaPlayer.setDataSource(file.getPath());//指定音频文件的路径
			mediaPlayer.prepare();//让MediaPlayer进入到准备状态
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	@Override
	public void onClick(View v){
		switch (v.getId()){
		case R.id.play:
			if(!mediaPlayer.isPlaying()){
				mediaPlayer.start();
			}
			break;
		case R.id.pause:
			if(mediaPlayer.isPlaying()){
				mediaPlayer.pause();
			}
			break;
		case R.id.stop:
			if(mediaPlayer.isPlaying()){
				mediaPlayer.reset();
				initMediaPlayer();
			}
			break;
		default:
			break;
		}
	}
	
	protected void onDestory() {
		super.onDestroy();
		if(mediaPlayer != null){
			mediaPlayer.stop();
			mediaPlayer.release();
		}
	}

}
