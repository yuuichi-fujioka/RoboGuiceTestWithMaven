package com.example.roboguice_with_maven;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.google.inject.Inject;

public class HelloAndroidActivity extends RoboActivity implements
		OnClickListener {

	@InjectView(R.id.button1)
	Button button1;
	@InjectView(R.id.button2)
	Button button2;
	@InjectResource(R.string.message)
	String message;
	@InjectResource(R.string.notify_message)
	String notifyMessage;
	@Inject
	NotificationManager notificationManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		button1.setOnClickListener(this);
		button2.setOnClickListener(this);
		notificationManager.cancel(0);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1:
			Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
			break;
		case R.id.button2:

			Intent intent = new Intent(getApplicationContext(),
					HelloAndroidActivity.class);
			// intentの設定
			PendingIntent contentIntent = PendingIntent.getActivity(
					getApplicationContext(), 0, intent, 0);

			Notification notify = new NotificationCompat.Builder(this)
					.setSmallIcon(R.drawable.ic_launcher)
					.setContentText(notifyMessage).setTicker(notifyMessage)
					.setContentTitle(notifyMessage)
					.setContentIntent(contentIntent).getNotification();
			notificationManager.notify(0, notify);
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(com.example.roboguice_with_maven.R.menu.main,
				menu);
		return true;
	}

}
