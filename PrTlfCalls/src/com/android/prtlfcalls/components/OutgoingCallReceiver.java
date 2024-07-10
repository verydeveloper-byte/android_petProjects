package com.android.prtlfcalls.components;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class OutgoingCallReceiver extends BroadcastReceiver {

	Context context;

	public OutgoingCallReceiver(Context context) {
		this.context = context;
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		String numero = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
		Log.d("BAZURA", numero);
		Toast.makeText(context, 
				"estas llamando a: " + numero, 
				Toast.LENGTH_LONG).show();
	}

}
