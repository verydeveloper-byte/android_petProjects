package com.example.proyectobdandroid;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Button btEntrar,btGrabar;
	private EditText tbLogin,tbPass;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        tbLogin=(EditText)findViewById(R.id.etLogin);
        tbPass=(EditText)findViewById(R.id.etPass);
        btEntrar=(Button)findViewById(R.id.btEntrar);
        btEntrar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				buscar();
				
			}
		});
        btGrabar=(Button)findViewById(R.id.btGrabar);
        btGrabar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			   grabar();
				
			}
		});
    }
    private void grabar()
    {
    	ArrayList parametros=null;
    	if(!tbLogin.getText().toString().equals(""))
    	{
    		parametros=new ArrayList();
    		parametros.add("operacion");
    		parametros.add("insertar");
    		parametros.add("login");
    		parametros.add(tbLogin.getText().toString());
    		parametros.add("pass");
    		parametros.add(tbPass.getText().toString());
    		
    	}
    	ConexionPost post=new ConexionPost();
    	JSONArray datos=post.getServerData(parametros, "http://172.16.100.5/paginaempresa/login.php");
    	try{
    		
    		if (datos!=null && datos.length()>0)
    		{
    			//Toast.makeText(getBaseContext(),"Usuarios Correcto", Toast.LENGTH_SHORT).show();
    			JSONObject fila=datos.getJSONObject(0);
    			if (fila.get("mensaje")!=null)
    			{
    				Toast.makeText(getBaseContext(),fila.getString("mensaje"), Toast.LENGTH_SHORT).show();
    			}
    		}else{
				Toast.makeText(getBaseContext(),"Error de grabación", Toast.LENGTH_SHORT).show();
			}
    	}
    	catch(Exception ex)
    	{
    		Toast.makeText(getBaseContext(),"Error de conexion", Toast.LENGTH_SHORT).show();
    	}
    }
    private void buscar()
    {
    	ArrayList parametros=null;
    	if(!tbLogin.getText().toString().equals(""))
    	{
    		parametros=new ArrayList();
    		parametros.add("login");
    		parametros.add(tbLogin.getText().toString());
    		parametros.add("pass");
    		parametros.add(tbPass.getText().toString());
    	}
    	ConexionPost post=new ConexionPost();
    	JSONArray datos=post.getServerData(parametros, "http://172.16.100.5/paginaempresa/login.php");
    	try{
    		
    		if (datos!=null && datos.length()>0)
    		{
    			//Toast.makeText(getBaseContext(),"Usuarios Correcto", Toast.LENGTH_SHORT).show();
    			JSONObject fila=datos.getJSONObject(0);
    			if (fila.get("login")!=null)
    			{
    				Toast.makeText(getBaseContext(),"Usuarios Correcto", Toast.LENGTH_SHORT).show();
    			}
    			
    		}else{
				Toast.makeText(getBaseContext(),"Usuarios Incorrecto", Toast.LENGTH_SHORT).show();
			}
    	}
    	catch(Exception ex)
    	{
    		Toast.makeText(getBaseContext(),"Error de conexion", Toast.LENGTH_SHORT).show();
    	}
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
