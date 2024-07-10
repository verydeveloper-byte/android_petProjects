package com.android.prnotificaciones;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends Activity {
	
	private final int NOTIF_ALERTA_ID = 0;
	private Button btNotif;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btNotif = (Button) findViewById(R.id.btNotif);
		btNotif.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				NotificationCompat.Builder mBuilder =
						new NotificationCompat.Builder(getApplicationContext())
				.setSmallIcon(android.R.drawable.stat_sys_warning)
				.setLargeIcon(((BitmapDrawable)getResources()
						.getDrawable(R.drawable.ic_launcher)).getBitmap())
						// notificacion programada
						//.setWhen(when)
						.setContentTitle("Alerta Houdston")
						.setContentText("Ejemplo de notificacion")
						// texto que sale junto al icono pequeño
						.setContentInfo("4")
						// texto que aparece temporalmente en la barra de estado superior
						.setTicker("Alerta!");
				
				Intent activityIntent = new Intent(MainActivity.this, MainActivity.class);
				PendingIntent pendingActivity = PendingIntent.getActivity(
						MainActivity.this, 0, activityIntent, 0);
				mBuilder.setContentIntent(pendingActivity);
				
				NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
				mNotificationManager.notify(NOTIF_ALERTA_ID, mBuilder.build());
			}
		});
		
	}
}
