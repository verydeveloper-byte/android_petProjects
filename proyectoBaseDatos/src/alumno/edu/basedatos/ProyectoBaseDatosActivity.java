package alumno.edu.basedatos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class ProyectoBaseDatosActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
		getMenuInflater().inflate(R.menu.menu_principal, menu);
    	return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem elemento)
    {
    	switch (elemento.getItemId())
    	{
    		case R.id.menu_operaciones:
    			    lanzar();
    			    break;
    		case R.id.menu_listar:
    			    break;
    		case R.id.menu_pintar:
    			    pintar();
    			    break;
    		case R.id.menu_salir:
    				break;
    		default: 
    				break; 
    	}
    	return true;
    }
    private void lanzar()
    {
    	Intent lanzador=new Intent(this, OperacionesActivity.class);
    	startActivity(lanzador);
    }
    private void pintar()
    {
        Intent lanzarPintar=new Intent(this,PintarActivity.class);
        startActivity(lanzarPintar);
    }
    
}