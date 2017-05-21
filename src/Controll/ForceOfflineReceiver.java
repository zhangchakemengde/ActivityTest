package Controll;

import com.example.activitytest.ActivityCollector;
import com.example.activitytest.SecondActivity;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.WindowManager;

public class ForceOfflineReceiver extends BroadcastReceiver{
	@Override
	public void onReceive(final Context context,Intent intent){
		Builder dialogBuilder = new Builder(context);
		dialogBuilder.setTitle("Warning");
		dialogBuilder.setMessage("You are forced to be offLine.please try to login again.");
		dialogBuilder.setCancelable(false);
		dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				ActivityCollector.finishAll();//�������л
				Intent intent = new Intent(context,SecondActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(intent);//��������SencondActivity
			}
		});
		AlertDialog alertDialog = dialogBuilder.create();
		//��Ҫ����AlertDialog�����ͣ���֤�ڹ㲥�������п�����������
		alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		alertDialog.show();
	}

}
