package com.example.activitytest;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.VideoView;

public class ElevenActivity extends Activity implements OnClickListener{
	private VideoView videoView;
	private Button play;
	private Button pause;
	private Button replay;
	private Button gotoTwelveActivity;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.eleven_layout);
		play = (Button) findViewById(R.id.play_vedio);
		pause = (Button) findViewById(R.id.vedio_pause);
		replay = (Button) findViewById(R.id.vedio_replay);
		videoView = (VideoView) findViewById(R.id.vedio_view);
		play.setOnClickListener(this);
		pause.setOnClickListener(this);
		replay.setOnClickListener(this);
		gotoTwelveActivity.setOnClickListener(this);
		initVideoPath();
	}
	private void initVideoPath(){
		File file = new File(Environment.getExternalStorageDirectory(),"movie.3gp");
		videoView.setVideoPath(file.getPath());
	}
	
	@Override
	public void onClick(View v){
		switch(v.getId()){
		case R.id.play_vedio:
			if(!videoView.isPlaying()){
				videoView.start();
			}
			break;
		case R.id.vedio_pause:
			if(videoView.isPlaying()){
				videoView.pause();
			}
			break;
		case R.id.vedio_replay:
			if(videoView.isPlaying()){
				videoView.resume();
			}
			break;
		case R.id.Goto_TwelveActivity:
			Intent intent = new Intent(ElevenActivity.this,TwelveActivity.class);
			startActivity(intent);
			finish();
			break;
		}
	}
	protected void onDestory() {
		super.onDestroy();
		if(videoView != null){
			videoView.suspend();
		}
	}

}
