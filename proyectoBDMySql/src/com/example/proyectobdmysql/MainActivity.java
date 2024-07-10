package com.example.proyectobdmysql;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Button btBuscar;
	private EditText etBuscar;
	private ArrayList<Usuarios> usuarios;
	private ListView lvUsuarios;
	
	private void buscar()
	{
		 ArrayList parametros=null; 
		 if (!etBuscar.getText().toString().equals(""))
	     {
			 parametros=new ArrayList();
			 parametros.add("login");
			 parametros.add(etBuscar.getText().toString());
	     }   
	     // Llamada a Servidor Web PHP
	     try {
	       ConexionPost post = new ConexionPost();
	       JSONArray datos = post.getServerData(parametros, 
	        		   "http://192.168.1.128/paginaandroid/login.php");
	       
	        
	       // No se puede poner localhost, carga la consola de Windows
	       // y escribe ipconfig/all para ver tu IP
	       usuarios.clear();
	       for(int i=0;datos != null && i<datos.length();i++) {
	           JSONObject json_data = datos.getJSONObject(i);
	       	   if (json_data.get("login")!=null)
	           {
	        	   Toast.makeText(getBaseContext(),"Usuario correcto. ", Toast.LENGTH_SHORT).show();
	           }
	       	  
	       	   Usuarios u=new Usuarios();
	       	   u.setLogin(json_data.getString("login"));
	       	   u.setPass(json_data.getString("pass"));
	       	   usuarios.add(u);
	       	   UsuarioAdapter usuarioadap=new UsuarioAdapter(this, usuarios);
	       	   lvUsuarios.setAdapter(usuarioadap);
	       	} 
	        if (datos==null || datos.length()==0){
	        	Toast.makeText(getBaseContext(),"Usuario incorrecto. ", Toast.LENGTH_SHORT).show();
	       	}
	       	
	     } catch (Exception e) {
	           Toast.makeText(getBaseContext(),"Error al conectar con el servidor. ",Toast.LENGTH_SHORT).show();
	     }
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        etBuscar=(EditText)findViewById(R.id.etBuscar);
        btBuscar=(Button)findViewById(R.id.btBuscar);
        btBuscar.setOnClickListener(new View.OnClickListener() {
		
        	public void onClick(View arg0) {
			  buscar();
        	}
        });
        lvUsuarios=(ListView)findViewById(R.id.lv_usuarios);
        usuarios=new ArrayList<Usuarios>();
        
        /*HttpGet httpGet=new HttpGet("http://192.168.1.128/paginaandroid/login.php");
        HttpClient httpClient=new DefaultHttpClient();
        HttpResponse response;
		try {
			response = (HttpResponse)httpClient.execute(httpGet);
			HttpEntity entidad=response.getEntity();
		    BufferedHttpEntity buffer=new BufferedHttpEntity(entidad);
		    InputStream iStream=buffer.getContent();
		    
		    String aux="";
		    BufferedReader r=new BufferedReader(new InputStreamReader(iStream));
		    StringBuilder total=new StringBuilder();
		    String linea;
		    while ((linea = r.readLine())!=null)
		    {
		    	aux+=linea;
		    }
		    JSONObject jsonObject=new JSONObject(aux);
		   
		    JSONArray usu_json=jsonObject.getJSONArray("entrada");
		    for (int i=0;i<usu_json.length();i++)
		    {
		    	JSONObject usua=usu_json.getJSONObject(i);
		    	Usuarios u=new Usuarios();
		    	u.setLogin(usua.getString("login"));
		    	u.setPass(usua.getString("pass"));
		    	usuarios.add(u);
		    }
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		//UsuarioAdapter usuarioadap=new UsuarioAdapter(this, usuarios);
		//lvUsuarios.setAdapter(usuarioadap);
    }
}

