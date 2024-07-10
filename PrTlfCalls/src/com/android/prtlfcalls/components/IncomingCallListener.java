/*
 * Clase listener que detecta llamadas entrantes usando la
 * clase base PhoneStateListener.
 * En esta clase sobreescribimos los metodos para los que deseamos
 * recibir informacion. Luego pasamos un objeto de esta clase
 * a TelephonyManager.listen() junto con los eventos que queremos
 * manejar.
 * 
 */
package com.android.prtlfcalls.components;

import android.content.Context;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class IncomingCallListener extends PhoneStateListener {

	Context context;

	public IncomingCallListener(Context context) {
		this.context = context;
	}

	/*
	 * callback que se ejecuta cuando hay un cambio
	 * en el estado de la llamada en el movil.
	 * CALL_STATE_IDLE -> normal, sin llamada.
	 * CALL_STATE_RINGING -> hay una llamada, el tlf suena
	 * CALL_STATE_OFFHOOK -> llamada saliente.
	 */
	@Override
	public void onCallStateChanged(int state, String incomingNumber) {
		super.onCallStateChanged(state, incomingNumber);

		switch (state) {
		case TelephonyManager.CALL_STATE_RINGING:
			Toast.makeText(context, 
					"Llamada entrante: " + incomingNumber, Toast.LENGTH_LONG).
					show();
			break;
		}
	}

}
