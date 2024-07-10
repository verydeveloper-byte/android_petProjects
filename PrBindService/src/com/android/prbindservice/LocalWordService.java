package com.android.prbindservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class LocalWordService extends Service {
	// Binder dado a los clientes
	private final IBinder mBinder = new MyBinder();
	private ArrayList<String> list = new ArrayList<String>();

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		Random random = new Random();
		if (random.nextBoolean()) {
			list.add("Linux");
		}
		if (random.nextBoolean()) {
			list.add("Android");
		}
		if (random.nextBoolean()) {
			list.add("iPhone");
		}
		if (random.nextBoolean()) {
			list.add("Windows7");
		}
		if (list.size() >= 20) {
			list.remove(0);
		}
		return Service.START_NOT_STICKY;
	}

	@Override
	public IBinder onBind(Intent arg0) {
		return mBinder;
	}

	public class MyBinder extends Binder {
		LocalWordService getService() {
			// devolver esta instancia de LocalWordService para que los
			// clientes puedan llamar a metodos publicos
			return LocalWordService.this;
		}
	}

	// metodo para los clientes
	public List<String> getWordList() {
		return list;
	}

} 
