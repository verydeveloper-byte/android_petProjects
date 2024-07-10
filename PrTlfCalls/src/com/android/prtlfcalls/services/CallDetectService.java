package com.android.prtlfcalls.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

import com.android.prtlfcalls.components.IncomingCallListener;
import com.android.prtlfcalls.components.OutgoingCallReceiver;

public class CallDetectService extends Service {

	TelephonyManager tm;
	IncomingCallListener incomingListener;

	/*
	 * punto de entrada del servicio, el intent proviene de
	 * startService()
	 */
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		super.onStartCommand(intent, flags, startId);

		/*
		 * Llamadas entrantes.
		 * Instanciamos un objeto TelephonyManager que nos permite
		 * registrar un listener personalizado PhoneStateListener 
		 * (IncomingCallListener) que va a responder al evento LISTEN_CALL_STATE.
		 */
		tm = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
		incomingListener = new IncomingCallListener(this.getApplicationContext());
		tm.listen(incomingListener, PhoneStateListener.LISTEN_CALL_STATE);

		/*
		 * Llamadas salientes.
		 */
		IntentFilter recibeLlamada = new IntentFilter(Intent.ACTION_NEW_OUTGOING_CALL);
		this.getApplicationContext().registerReceiver(new OutgoingCallReceiver(this.getApplicationContext()), recibeLlamada);

		// reiniciar el servicio si el sistema lo para.
		return Service.START_STICKY;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		// unregisterReceiver() y PhoneStateListener.NONE
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

}
