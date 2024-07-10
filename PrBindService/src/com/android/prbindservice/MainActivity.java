/*
 * http://www.edu4android.com/es/guide/topics/fundamentals/bound-services.html
 * http://androideity.com/2011/11/08/creando-un-servicio-propio-en-android/
 * https://developer.android.com/reference/android/app/Service.html
 * http://www.vogella.com/tutorials/AndroidServices/article.html
 * http://androcode.es/2012/10/android-services-14/
 */
package com.android.prbindservice;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class MainActivity extends ListActivity {
	private LocalWordService s;
	private ArrayAdapter<String> adapter;
	private List<String> wordList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		wordList = new ArrayList<String>();
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1,
				wordList);

		setListAdapter(adapter);
		//startService(new Intent(this, LocalWordService.class));
		bindService(new Intent(this, LocalWordService.class), mConnection,
				Context.BIND_AUTO_CREATE);
	}

	@Override
	protected void onResume() {
		super.onResume();
		Intent intent= new Intent(this, LocalWordService.class);
		bindService(intent, mConnection,
				Context.BIND_AUTO_CREATE);
	}

	@Override
	protected void onPause() {
		super.onPause();
		unbindService(mConnection);
	}

	private ServiceConnection mConnection = new ServiceConnection() {

		public void onServiceConnected(ComponentName className, 
				IBinder binder) {
			/*
			 * Casteamos el IBinder que recibimos del servicio del callback onBind()
			 * y obtencion de la instancia de LocalWordService
			 */
			LocalWordService.MyBinder b = (LocalWordService.MyBinder) binder;
			s = b.getService();
			Toast.makeText(MainActivity.this, "Connected", Toast.LENGTH_SHORT)
			.show();
		}

		public void onServiceDisconnected(ComponentName className) {
			s = null;
		}
	};

	public void onClick(View view) {
		if (s != null) {
			Toast.makeText(this, "Number of elements " + s.getWordList().size(),
					Toast.LENGTH_SHORT).show();
			wordList.clear();
			wordList.addAll(s.getWordList());
			adapter.notifyDataSetChanged();
		}
	}
} 
