/*
     protected void onCreate(Bundle savedInstanceState);

     protected void onStart();
     
     protected void onRestart();

     protected void onResume();

     protected void onPause();

     protected void onStop();

     protected void onDestroy();
 
http://developer.android.com/reference/android/app/Activity.html#ActivityLifecycle
 */

package com.ciclovida.prciclovida;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends Activity {

	private static final String ActivityName = "CicloVida";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(ActivityName, "Eis, el programa se esta abriendo onCreate();");
        //System.out.println("Eis, el programa se esta abriendo onCreate();");
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }
    

    @Override
    protected void onDestroy() {
    	super.onDestroy();
    	Log.i(ActivityName, "Eis, el programa se esta cerrando onDestroy()");
    	//System.out.println("Eis, el programa se esta cerrando");
    }
    
    @Override
    protected void onRestart() {
    	super.onRestart();
    	Log.i(ActivityName, "Eis, el programa se reinicia onRestart()");
    	//System.out.println("Eis, el programa se reinicia onRestart()");
    }
    
    @Override
    protected void onStop() {
    	super.onStop();
    	Log.i(ActivityName, "Eis, el programa se para onStop()");
    	//System.out.println("Eis, el programa se para onStop()");
    }
    
    @Override 
    protected void onStart() {
    	super.onStart();
    	Log.i(ActivityName, "Eis, el programa comienza onStart()");
    	//System.out.println("Eis, el programa comienza onStart()");
    }
    
    @Override
    protected void onPause() {
    	super.onPause();
    	Log.i(ActivityName, "Eis, estoy pausado onPause()");
    	//System.out.println("Eis, estoy pausado onPause()");
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

}
