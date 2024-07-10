/*
 * Hay dos maneras de controlar las llamadas entrantes;
 * la primera es usando TelephonyManager y una clase
 * PhoneStateListener.
 * La segunda es usando un broadcast receiver para 
 * TelephonyManager.ACTION_PHONE_STATE_CHANGED.
 * Ambas son equivalentes.
 */
package com.android.prtlfcalls;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.android.prtlfcalls.services.CallDetectService;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Intent callServiceIntent = new Intent(this.getApplicationContext(), CallDetectService.class);
		this.startService(callServiceIntent);
	}

}
