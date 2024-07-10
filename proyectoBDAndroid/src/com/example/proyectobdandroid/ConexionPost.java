package com.example.proyectobdandroid;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

public class ConexionPost {
	private InputStream is=null;
	private String respuesta="";
	
	public JSONArray getServerData(ArrayList parametros,String url)
	{
		JSONArray salida=null;
		conectarPost(parametros,url);
		if (is!=null)
		{
			getRespuestaPos();
		}
		if (respuesta!=null)
		{
			salida=getJSonArray();
		}
		return salida;
	}

	private JSONArray getJSonArray() {
		JSONArray jArray=null;
		try{
			JSONObject jsonObjeto=new JSONObject(respuesta);
			jArray=jsonObjeto.getJSONArray("entrada");
		}
		catch(Exception ex)
		{
			
		}
		return jArray;
	}

	private void getRespuestaPos() {
		try{
			BufferedReader lector=new BufferedReader(new InputStreamReader(is,"utf-8"),8);
			StringBuilder sb=new StringBuilder();
			String linea=null;
			while((linea=lector.readLine())!=null)
			{
				sb.append(linea+"\n");
			}
			is.close();
			respuesta=sb.toString();
		}
		catch(Exception e)
		{
			Log.e("log_tag","Error de convertir string "+e.toString());
		}
	}

	private void conectarPost(ArrayList parametros, String url) {
		ArrayList parNombreValor;
		try{
			HttpClient httpcliente=new DefaultHttpClient();
			HttpPost httpost=new HttpPost(url);
			parNombreValor=new ArrayList();
			
			if (parametros!=null)
			{
				for (int i=0;i<parametros.size()-1;i+=2)
				{
					parNombreValor.add(new BasicNameValuePair((String)parametros.get(i), (String)parametros.get(i+1)));
				}
				httpost.setEntity(new UrlEncodedFormEntity(parNombreValor));	
			}
			HttpResponse respuesta=httpcliente.execute(httpost);
			HttpEntity entidad=respuesta.getEntity();
			is=entidad.getContent();
		}
		catch(Exception ex)
		{
			Log.e("log_tag","error en conexion http"+ex.toString());
		}
	}
}
