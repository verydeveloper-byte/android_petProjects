package alumnos.edu.fichero;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class ProyectoMenuFicheroActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
    	getMenuInflater().inflate(R.menu.menuprincipal, menu);
    	return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
    	switch(item.getItemId())
    	{
    		case R.id.menuGrabar:
    			        Intent lanzar=new Intent(this, CGrabar.class);
    			        startActivity(lanzar);
    					return true;
    		case R.id.menuListar:
    					Intent lanzar2=new Intent(this,ListadoActivity.class);
    					startActivity(lanzar2);
    					return true;
    		case R.id.menuSalir:
    					finish();
    					return true;
    		default:
    			return onOptionsItemSelected(item);
    	}
    }
}