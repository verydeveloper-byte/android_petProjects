package com.android.prsharedprefs;

import java.util.Set;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		getFragmentManager().beginTransaction()
		.add(R.id.container, new FragmentSettings()).commit();

		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		Log.d("SHAREDPREFS", Boolean.toString(prefs.getBoolean("opcion1", false)));
		Log.d("SHAREDPREFS", prefs.getString("opcion2", "opcion2vacio"));
		Log.d("SHAREDPREFS", prefs.getString("opcion3", "NA"));
		
		// acceder a un MultiSelectlistPreference
		Set<String> multiList;
		multiList = prefs.getStringSet("opcion4", null);
		// leer los elementos #1
		String [] arrayMultiList = multiList.toArray(new String[multiList.size()]);
		for (String a : arrayMultiList)
			Log.d("SHAREDPREFS", "elemento multi-seleccionado #1 " + a);
		// leer los elementos #2
		for (String a : multiList)
			Log.d("SHAREDPREFS", "elemento multi-seleccionado #2 " + a);
	}

}
